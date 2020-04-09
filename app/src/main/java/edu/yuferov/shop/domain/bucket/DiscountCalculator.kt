package edu.yuferov.shop.domain.bucket

import edu.yuferov.shop.domain.common.Percent

interface DiscountCalculator {
    fun calc(bucket: List<BucketItem>): List<Percent>
}