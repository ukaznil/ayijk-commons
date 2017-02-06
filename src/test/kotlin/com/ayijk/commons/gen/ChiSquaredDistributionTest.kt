package com.ayijk.commons.gen

import com.ayijk.commons.math.AssertUtils.assertEquals
import com.ayijk.commons.math.Maths
import com.ayijk.commons.math.gen.ChiSquaredDistribution
import com.ayijk.commons.math.gen.UniformDistribution
import com.ayijk.commons.stats.meanAndVariance
import org.junit.Test

/**
 * Created by ayijk on 2017/02/05.
 */
class ChiSquaredDistributionTest {
  @Test
  fun generateTest() {
    val uni = UniformDistribution()
    val k = uni.generate(100)

    val dist1 = org.apache.commons.math3.distribution.ChiSquaredDistribution(k)
    val dist2 = ChiSquaredDistribution(k)
    val results = (0 until 1000000).map {
      dist1.sample() to dist2.generate()
    }

    val mv1 = meanAndVariance(results.map { it.first })
    val mv2 = meanAndVariance(results.map { it.second })
    assertEquals(0, Math.abs(mv1.mean - mv2.mean) / mv1.mean, Maths.pow(10, -2))
    assertEquals(0, Math.abs(mv1.std - mv2.std) / mv1.std, Maths.pow(10, -2))
  }
}