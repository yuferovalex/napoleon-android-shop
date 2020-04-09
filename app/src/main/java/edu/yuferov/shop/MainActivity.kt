package edu.yuferov.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.yuferov.shop.debug.DebugBucketPresenter
import edu.yuferov.shop.debug.RandomDiscountCalc
import edu.yuferov.shop.domain.bucket.Bucket
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity
import edu.yuferov.shop.domain.product.WeightQuantity
import edu.yuferov.shop.infrastructure.impl.SingleThreadEventPipe

class MainActivity : AppCompatActivity() {

    private val eventPipe = SingleThreadEventPipe()
    private val debugBucketPresenter = DebugBucketPresenter()
    private val discountCalculator = RandomDiscountCalc()
    private val bucket: Bucket

    init {
        eventPipe.subscribe(debugBucketPresenter)
        bucket = Bucket(eventPipe, discountCalculator)

        bucket.addBucketItem(
            Product(
                description = "Молоко 3.2%",
                price = Price(62.20)
            ),
            Quantity.pieces(2)
        )

        bucket.addBucketItem(
            Product(
                description = "Горбулка",
                price = Price(15.90)
            ),
            Quantity.pieces(1)
        )


        bucket.addBucketItem(
            Product(
                description = "Корень имбиря",
                price = Price(10000.00)
            ),
            Quantity.weight(10.0, WeightQuantity.Measure.GRAM)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
