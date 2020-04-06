package edu.yuferov.shop.domain.product

interface Quantity {
    val value: Double
    val suffix: String

    companion object Companion {
        fun pieces(value: Int) = PieceQuantity(value.toDouble())
        fun weight(value: Double, measure: WeightQuantity.Measure) = WeightQuantity(value, measure)
    }
}

