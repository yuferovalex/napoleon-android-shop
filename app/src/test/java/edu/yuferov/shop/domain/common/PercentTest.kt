package edu.yuferov.shop.domain.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PercentTest {

    @Test
    fun getFraction() {
        val actual = Percent(5.0).fraction

        assertEquals(0.05, actual, 0.001)
    }

    @Test
    fun plus() {
        val actual = 100.0 + Percent(5.0)

        assertEquals(105.0, actual, 0.1)
    }

    @Test
    fun minus() {
        val actual = 100.0 - Percent(5.0)

        assertEquals(95.0, actual, 0.1)
    }

    @Test
    fun times() {
        val actual = 100.0 * Percent(5.0)

        assertEquals(5.0, actual, 0.1)
    }

    @Test
    fun div() {
        val actual = 5.0 / Percent(5.0)

        assertEquals(100.0, actual, 0.1)
    }
}
