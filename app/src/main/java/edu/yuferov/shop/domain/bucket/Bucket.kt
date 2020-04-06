package edu.yuferov.shop.domain.bucket

import edu.yuferov.shop.domain.common.Percent
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity

class Bucket {
    private val _items: MutableList<BucketItem> = mutableListOf()

    val items: List<BucketItem> = _items

    val totalPrice: Price
        get() {
            return items
                .map { it.price }
                .fold(Price(0.0)) { acc, curr -> acc + curr }
        }

    val totalDiscount: Price
        get() {
            return items
                .map { it.discountValue }
                .fold(Price(0.0)) { acc, curr -> acc + curr }
        }

    fun addBucketItem(product: Product, quantity: Quantity, discount: Percent = Percent(0.0)) {
        _items.add(BucketItem(product, quantity, discount))
    }

    fun removeBucketItem(item: BucketItem) {
        _items.remove(item)
    }
}
