package edu.yuferov.shop.domain.bucket

import edu.yuferov.shop.domain.common.Percent
import edu.yuferov.shop.domain.common.Price
import edu.yuferov.shop.domain.events.BucketItemAddedEvent
import edu.yuferov.shop.domain.events.BucketItemRemovedEvent
import edu.yuferov.shop.domain.events.DiscountChangedEvent
import edu.yuferov.shop.domain.product.Product
import edu.yuferov.shop.domain.product.Quantity
import edu.yuferov.shop.infrastructure.EventPublisher

class Bucket(
    private val eventPublisher: EventPublisher,
    private val discountCalculator: DiscountCalculator
) {
    private var _items: MutableList<BucketItem> = mutableListOf()

    val items: List<BucketItem>
        get() = _items

    val totalPrice: Price
        get() {
            return items
                .map { it.price }
                .fold(Price(0.0)) { acc, curr -> acc + curr }
        }

    val totalDiscount: Percent
        get() {
            return Percent( totalDiscountValue.value / totalPrice.value * 100.00)
        }

    val totalDiscountValue: Price
        get() {
            return items
                .map { it.discountValue }
                .fold(Price(0.0)) { acc, curr -> acc + curr }
        }

    fun addBucketItem(product: Product, quantity: Quantity) {
        val item = BucketItem(product, quantity)
        _items.add(item)
        val events = recalculateDiscounts()

        events
            .filter { it.oldItem != item }
            .forEach(eventPublisher::publish)

        val itemWithDiscount = events.find { it.oldItem == item } ?. newItem ?: item

        eventPublisher.publish(BucketItemAddedEvent(this, itemWithDiscount))
    }

    fun removeBucketItem(item: BucketItem) {
        _items.remove(item)
        val events = recalculateDiscounts()
        eventPublisher.publish(BucketItemRemovedEvent(this, item))
        events.forEach(eventPublisher::publish)
    }

    private fun recalculateDiscounts(): List<DiscountChangedEvent> {
        val discounts = discountCalculator.calc(items)

        // recalculate
        val newItems = (items zip discounts)
            .map { (item, discount) ->
                if (item.discount == discount)
                    item
                else
                    BucketItem(item.product, item.quantity, discount)
            }
            .toMutableList()

        // create events
        val events = (newItems zip items)
            .filter { (newItem, oldItem) -> newItem != oldItem }
            .map { (newItem, oldItem) -> DiscountChangedEvent(this, newItem, oldItem) }

        // replace with new item array
        this._items = newItems

        return events
    }
}
