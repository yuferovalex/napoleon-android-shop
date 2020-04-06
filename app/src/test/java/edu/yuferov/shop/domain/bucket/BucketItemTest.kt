package edu.yuferov.shop.domain.bucket

import edu.yuferov.shop.domain.common.Percent
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BucketItemTest {
    @Test
    fun pricePropertyTest() {
        val item = BucketItem(
            product = Product(
                description = "Ketchup",
                price = Price(123.0)
            ),
            quantity = Quantity.pieces(2),
            discount = Percent(5.0)
        )

        assertEquals(123 * 2 * 0.95, item.price.value)
    }
}