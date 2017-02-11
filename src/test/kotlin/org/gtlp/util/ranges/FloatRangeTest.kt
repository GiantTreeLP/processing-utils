package org.gtlp.util.ranges

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests for [FloatRange]
 */
internal class FloatRangeTest {
    @Test
    fun rangeTo() {
        var expected = 0f
        (0f..10f step 0.25f).forEach {
            assertEquals(it, expected)
            expected += 0.25f
        }
    }

}