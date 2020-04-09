package edu.yuferov.shop.domain.events

import edu.yuferov.shop.domain.bucket.Bucket
import edu.yuferov.shop.domain.bucket.BucketItem
import edu.yuferov.shop.infrastructure.Event

class DiscountChangedEvent(override val sender: Bucket,
                           val newItem: BucketItem,
                           val oldItem: BucketItem) : Event(sender)