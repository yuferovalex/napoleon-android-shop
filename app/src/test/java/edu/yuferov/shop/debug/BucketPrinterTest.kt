package edu.yuferov.shop.debug

import edu.yuferov.shop.domain.bucket.Bucket
import edu.yuferov.shop.domain.common.Percent
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity
import edu.yuferov.shop.domain.product.WeightQuantity
import org.junit.jupiter.api.Test

internal class BucketPrinterTest {
    @Test
    fun emptyBucket() {
        val bucket = Bucket()

        BucketPrinter(System.out).print(bucket)
    }

    @Test
    fun bucketWithItems() {
        val banana = Product("Banana", Price(52.0))
        val milk = Product("Milk 3.2%", Price(64.0))

        val bucket = Bucket()
        bucket.addBucketItem(banana, Quantity.weight(1.23, WeightQuantity.Measure.KILOGRAM))
        bucket.addBucketItem(milk, Quantity.pieces(2), Percent(5.0))

        BucketPrinter(System.out).print(bucket)
    }
}