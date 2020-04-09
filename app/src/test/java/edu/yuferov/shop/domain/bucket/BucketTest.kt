package edu.yuferov.shop.domain.bucket

import edu.yuferov.shop.domain.common.Percent
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.events.BucketItemAddedEvent
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity
import edu.yuferov.shop.domain.product.WeightQuantity
import edu.yuferov.shop.infrastructure.EventPublisher
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class BucketTest {

    @Test
    fun addBucketItemTest() {
        val banana = Product("Banana", Price(52.0))
        val eventPublisher = mockEventPublisher()
        val discountCalculator = mockDiscountCalculator()

        val bucket = Bucket(eventPublisher, discountCalculator)
        bucket.addBucketItem(banana, Quantity.weight(1.23, WeightQuantity.Measure.KILOGRAM))

        assertEquals(1, bucket.items.size)
        assertEquals(52.0 * 1.23, bucket.totalPrice.value)
        verify {
            discountCalculator.calc(any())
            eventPublisher.publish(match { it is BucketItemAddedEvent })
        }
    }

    @Test
    fun removeBucketItemTest() {
        val banana = Product("Banana", Price(52.0))
        val eventPublisher = mockEventPublisher()
        val discountCalculator = mockDiscountCalculator()
        val bucket = Bucket(eventPublisher, discountCalculator)
        bucket.addBucketItem(banana, Quantity.weight(1.23, WeightQuantity.Measure.KILOGRAM))

        bucket.removeBucketItem(bucket.items[0])

        assertNull(bucket.items.find { it.product == banana })
    }

    private fun mockDiscountCalculator(): DiscountCalculator {
        val discountCalculator = mockk<DiscountCalculator>()

        every { discountCalculator.calc(any()) } answers {
            arg<List<BucketItem>>(0).map { Percent(0.0) }
        }

        return discountCalculator
    }

    private fun mockEventPublisher(): EventPublisher {
        val eventPublisher = mockk<EventPublisher>()

        every { eventPublisher.publish(any()) } just Runs

        return eventPublisher
    }

}