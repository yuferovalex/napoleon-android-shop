package edu.yuferov.shop.domain.bucket

import edu.yuferov.shop.domain.common.Percent
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity

class BucketItem(val product: Product,
                 val quantity: Quantity,
                 val discount: Percent) {

    val price = product.price * quantity - discount

    val discountValue: Price = price * quantity * discount
}
