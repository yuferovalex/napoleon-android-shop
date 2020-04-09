package edu.yuferov.shop.debug

import edu.yuferov.shop.domain.bucket.BucketItem
import edu.yuferov.shop.domain.bucket.DiscountCalculator
import edu.yuferov.shop.domain.common.Percent

class RandomDiscountCalc : DiscountCalculator {
    override fun calc(bucket: List<BucketItem>): List<Percent> {
        return bucket.map {
            Percent(Math.random() * 5.0)
        }
    }
}
