package edu.yuferov.shop.domain.bucket

import edu.yuferov.shop.debug.BucketPrinter
import edu.yuferov.shop.domain.common.Percent
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity
import edu.yuferov.shop.domain.product.WeightQuantity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BucketTest {

    @Test
    fun addBucketItemTest() {
        val banana = Product("Banana", Price(52.0))
        val milk = Product("Milk 3.2%", Price(64.0))

        val bucket = Bucket()
        bucket.addBucketItem(banana, Quantity.weight(1.23, WeightQuantity.Measure.KILOGRAM))
        bucket.addBucketItem(milk, Quantity.pieces(2), Percent(5.0))

        assertEquals(2, bucket.items.size)
        assertEquals(52.0 * 1.23 + 2 * 64 * 0.95, bucket.totalPrice.value)
    }

    @Test
    fun removeBucketItemTest() {
        val banana = Product("Banana", Price(52.0))
        val milk = Product("Milk 3.2%", Price(64.0))
        val bucket = Bucket()
        bucket.addBucketItem(banana, Quantity.weight(1.23, WeightQuantity.Measure.KILOGRAM))
        bucket.addBucketItem(milk, Quantity.pieces(2), Percent(5.0))

        bucket.removeBucketItem(bucket.items[0])

        assertNull(bucket.items.find { it.product == banana })
    }

}