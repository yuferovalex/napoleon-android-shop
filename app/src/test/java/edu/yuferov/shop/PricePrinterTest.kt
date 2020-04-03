package edu.yuferov.shop

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.*

abstract class BasePricePrinterTest(val pricePrinterFactory: (os: OutputStream) -> PricePrinter) {
    @ParameterizedTest
    @MethodSource("parameters")
    fun `should properly format given price`(price: Double, expected: String) {
        val actual = ByteArrayOutputStream().use { stream ->
            val pricePrinter = pricePrinterFactory(stream)

            pricePrinter.print(price)

            stream.toString("UTF-8")
        }

        assertThat(actual, `is`(expected))
    }
}

class PrefixPricePrinterTest : BasePricePrinterTest({ os -> PrefixPricePrinter(os) }) {
    companion object Companion {
        @JvmStatic
        fun parameters() = arrayOf(
            arrayOf(123.0, "$123.00"),
            arrayOf(123.4, "$123.40"),
            arrayOf(123.45, "$123.45")
        )
    }
}

class SuffixPricePrinterTest : BasePricePrinterTest({ os -> SuffixPricePrinter(os) }) {
    companion object Companion {
        @JvmStatic
        fun parameters() = arrayOf(
            arrayOf(123.0, "123.00 USD"),
            arrayOf(123.4, "123.40 USD"),
            arrayOf(123.45, "123.45 USD")
        )
    }
}

class PrefixPricePrinter(val os: OutputStream = System.out) : PricePrinter {
    override fun print(price: Money) {
        PrintStream(os).print(String.format(Locale.US, "$%.2f", price))
    }
}

class SuffixPricePrinter(val os: OutputStream = System.out) : PricePrinter {
    override fun print(price: Money) {
        PrintStream(os).print(String.format(Locale.US,"%.2f USD", price))
    }
}

typealias Money = Double

interface PricePrinter {

    /**
     * Outputs price in <PRICE> format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Money)
}
