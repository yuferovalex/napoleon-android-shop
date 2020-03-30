package edu.yuferov.shop

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class MathTest {
    @Test
    fun subTest() {
        assertThat(-1, `is`(2 - 3))
    }

    @Test
    fun incrementTest() {
        var x = 3;
        assertThat(4, `is`(++x))
    }

    @Test
    fun divideTest() {
        assertThat(2, `is`(4 / 2))
    }

    @Test
    fun multiplicationTest() {
        assertThat(10, `is`(2 * 5))
    }
}