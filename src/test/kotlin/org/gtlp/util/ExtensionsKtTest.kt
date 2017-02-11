package org.gtlp.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Test for Extensions.kt
 */
internal class ExtensionsKtTest {
    @Test
    fun map() {
        assertEquals(map(25, 0, 100, 0, 1), 0.25f)
        assertEquals(map(0.1, 0, 1, 0, 100), 10f)
    }

}