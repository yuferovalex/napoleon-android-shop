package edu.yuferov.shop.debug

import android.util.Log
import edu.yuferov.shop.domain.bucket.Bucket
import edu.yuferov.shop.domain.bucket.BucketItem
import edu.yuferov.shop.domain.events.BucketItemAddedEvent
import edu.yuferov.shop.domain.events.BucketItemRemovedEvent
import edu.yuferov.shop.domain.product.PieceQuantity
import edu.yuferov.shop.domain.product.WeightQuantity
import edu.yuferov.shop.infrastructure.EventHandler

class DebugBucketPresenter : EventHandler {
    override fun handleApplicationEvent(event: Any) {
        when (event) {
            is BucketItemAddedEvent -> print("New item added", event.sender)
            is BucketItemRemovedEvent -> print("Item removed", event.sender)
        }
    }

    private fun print(comment: String, bucket: Bucket) {
        val items = if (bucket.items.isEmpty()) {
            "No items"
        } else {
            bucket.items
                .mapIndexed { index, item ->
                    formatBucketItem(index, item)
                }
                .joinToString("\r\n")
        }

        val totalDiscount = String.format("%.2f", bucket.totalDiscount.value)
        val totalDiscountValue = String.format("%.2f", bucket.totalDiscountValue.value)
        val totalPrice = String.format("%.2f", bucket.totalPrice.value)

        Log.d("bucket-debug", "$comment:\r\n" +
                "$items\r\n" +
                "---\r\n" +
                "Скидка: $totalDiscount% [ $totalDiscountValue руб. ]\r\n" +
                "  Итог: $totalPrice руб.\r\n"
        )
    }

    private fun formatBucketItem(index: Int, item: BucketItem): String {
        var result = "${index + 1}) ${item.product.description}\r\n\t\t"

        result += when (item.quantity) {
            is PieceQuantity -> String.format("%d", item.quantity.value.toInt())
            is WeightQuantity -> String.format("%.3f", item.quantity.value)
            else -> throw NotImplementedError("unknown quantity implementation")
        }
        result += " ${item.quantity.suffix} x "
        result += String.format("%.2f руб.", item.product.price.value)

        if (item.discount > 0.0) {
            result += String.format(" - %.2f%% [ %.2f руб. ]",
                item.discount.value, item.discountValue.value)
        }
        result += String.format(" = %.2f руб.", item.price.value)

        return result
    }
}