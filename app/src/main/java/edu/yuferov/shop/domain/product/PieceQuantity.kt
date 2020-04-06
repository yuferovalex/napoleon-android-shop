package edu.yuferov.shop.domain.product

class PieceQuantity(override val value: Double) : Quantity {
    init {
        assert(value > 0.0) { "value must be greater than zero" }
    }

    override val suffix: String = "шт."
}