package edu.yuferov.shop.domain.events

import edu.yuferov.shop.domain.bucket.Bucket
import edu.yuferov.shop.domain.bucket.BucketItem
import edu.yuferov.shop.infrastructure.Event

class BucketItemAddedEvent(override val sender: Bucket,
                           val item: BucketItem) : Event(sender)
