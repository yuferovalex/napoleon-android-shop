package edu.yuferov.shop.debug

import edu.yuferov.shop.domain.bucket.Bucket
import edu.yuferov.shop.domain.bucket.BucketItem
import java.io.OutputStream
import java.io.PrintStream

class BucketPrinter(val os: OutputStream) {

    fun print(bucket: Bucket) {
        val os = PrintStream(this.os)

        os.println("Bucket:")

        if (bucket.items.isEmpty()) {
            os.println("No items")
        }

        os.println(
            bucket.items
                .mapIndexed { index, item ->
                    formatBucketItem(index, item)
                }
                .joinToString("\r\n")
        )

        os.println("---")
        os.println("Total discount: ${bucket.totalDiscount.value}")
        os.println("Total price   : ${bucket.totalPrice.value}")
    }

    private fun formatBucketItem(index: Int, item: BucketItem): String {
        var result = "${index + 1}) ${item.product.description}\r\n" +
                     "\t\t${item.quantity.value} ${item.quantity.suffix} x ${item.product.price.value}"

        if (item.discount > 0.0) {
            result += " - ${item.discount.value}% [ ${item.discountValue.value} ]"
        }
        result += " = ${item.price.value}"

        return result
    }

}