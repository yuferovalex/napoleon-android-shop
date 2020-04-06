package edu.yuferov.shop.domain.product

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WeightQuantityTest {
    @Test
    fun gram() {
        val actual = WeightQuantity(1000.0, WeightQuantity.Measure.GRAM).value

        assertEquals(1.0, actual, 0.0001)
    }

    @Test
    fun kilogram() {
        val actual = WeightQuantity(1.0, WeightQuantity.Measure.KILOGRAM).value

        assertEquals(1.0, actual, 0.0001)
    }
}