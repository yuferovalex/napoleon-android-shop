package edu.yuferov.shop.domain.common

import edu.yuferov.shop.domain.product.Quantity

class Price(
    /**
     * Price in rubles.
     */
    val value: Double
) {
    init {
        assert(value >= 0.0) { "value must be greater or equal zero" }
    }

    operator fun times(quantity: Quantity): Price {
        return Price(this.value * quantity.value)
    }

    operator fun times(percent: Percent): Price {
        return Price(this.value * percent)
    }

    operator fun plus(percent: Percent): Price {
        return Price(this.value + percent)
    }

    operator fun plus(price: Price): Price {
        return Price(this.value + price.value)
    }

    operator fun minus(percent: Percent): Price {
        return Price(this.value - percent)
    }
}