package edu.yuferov.shop.domain.product

class WeightQuantity(value: Double, measure: Measure) : Quantity {
    enum class Measure(val coefficient: Double) {
        GRAM(1.0),
        KILOGRAM(1000.0)
    }

    /**
     * Weight in kilos.
     */
    override val value: Double
    override val suffix = "кг."

    init {
        assert(value > 0.0) { "value must be greater than zero" }
        this.value = value * measure.coefficient / Measure.KILOGRAM.coefficient
    }
}
