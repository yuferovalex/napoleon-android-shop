package edu.yuferov.shop

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class MathTest {
    @Test
    fun someTest() {
        assertThat(5, `is`(2 + 3))
    }

    @Test
    fun incrementTest() {
        var x = 3;
        assertThat(4, `is`(++x))
    }
}