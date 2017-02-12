package org.gtlp.util.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.StrictMath.sqrt
import kotlin.test.assertTrue

/**
 * Tests for [Vector].
 */
internal class VectorTest {
    @Test
    fun times() {
        assertEquals(Vector(1, 2, 3) * 10, Vector(10, 20, 30))
    }

    @Test
    fun times1() {
        assertEquals(Vector(1, 2, 3) * Vector(3, 2, 1), Vector(3, 4, 3))
    }

    @Test
    fun div() {
        assertEquals(Vector(10, 20, 30) / 10, Vector(1, 2, 3))
    }

    @Test
    fun div1() {
        assertEquals(Vector(10, 20, 30) / Vector(2, 4, 6), Vector(5, 5, 5))
    }

    @Test
    fun minus() {
        assertEquals(Vector(10, 20, 30) - 10, Vector(0, 10, 20))
    }

    @Test
    fun minus1() {
        assertEquals(Vector(10, 20, 30) - Vector(2, 4, 6), Vector(8, 16, 24))
    }

    @Test
    fun plus() {
        assertEquals(Vector(10, 20, 30) + 2, Vector(12, 22, 32))
    }

    @Test
    fun plus1() {
        assertEquals(Vector(10, 20, 30) + Vector(2, 4, 6), Vector(12, 24, 36))
    }

    @Test
    fun mag() {
        assertEquals(Vector(2, 2, 2).mag(), sqrt((2 * 2 + 2 * 2 + 2 * 2).toDouble()))
    }

    @Test
    fun magSq() {
        assertEquals(Vector(2, 2, 2).magSq(), (2 * 2 + 2 * 2 + 2 * 2).toFloat())
    }

    @Test
    fun dot() {
        assertEquals(Vector(2, 7, 4) dot Vector(-7, 5, 4), 37f)
    }

    @Test
    fun cross() {
        assertEquals(Vector(2, 7, 4) cross Vector(-7, 5, 4), Vector(8, -36, 59))
    }

    @Test
    fun normalize() {
        assertEquals(Vector(8, -36, 59).normalize(), Vector(8 / sqrt(4841.toDouble()), -36 / sqrt(4841.toDouble()), 59 / sqrt(4841.toDouble())))
    }

    @Test
    fun setMag() {
        assertEquals(Vector(1, 1, 1).setMag(1f), Vector(1 / sqrt(3.toDouble()), 1 / sqrt(3.toDouble()), 1 / sqrt(3.toDouble())))
    }

    @Test
    fun limit() {
        assertEquals(Vector(1, 2, 3).limit(2), Vector(2 / sqrt(14.toDouble()), sqrt(2 / 7.toDouble()) * 2, 6 / sqrt(14.toDouble())))
    }

    @Test
    fun lerp() {
        assertEquals(Vector(1, 1, 1).lerp(Vector(3, 3, 3), 0.5), Vector(2, 2, 2))
    }

    @Test
    fun set() {
        assertEquals(Vector(0, 0, 0).set(Vector(1, 2, 3)), Vector(1, 2, 3))
    }

    @Test
    fun set1() {
        assertEquals(Vector(0, 0, 0).set(1, 2, 3), Vector(1, 2, 3))
    }

    @Test
    fun equals() {
        assertTrue(Vector(3, 2, 1) == Vector(3.0, 2.0, 1.0))
    }

    @Test
    fun compareTo() {
        assertTrue(Vector(0, 1, 1) < Vector(1, 2, 3))
    }

    @Test
    fun clone() {
        assertEquals(Vector(2, 3, 4).clone(), Vector(2, 3, 4))
    }

    @Test
    fun getX() {
        assertEquals(Vector(2, 3, 5).x, 2f)
    }

    @Test
    fun setX() {
        assertEquals(Vector(2, 3, 5).apply {
            x = 0f
        }, Vector(0, 3, 5))
    }

    @Test
    fun getY() {
        assertEquals(Vector(2, 3, 5).y, 3f)
    }

    @Test
    fun setY() {
        assertEquals(Vector(2, 3, 5).apply {
            y = 0f
        }, Vector(2, 0, 5))
    }

    @Test
    fun getZ() {
        assertEquals(Vector(2, 3, 5).z, 5f)
    }

    @Test
    fun setZ() {
        assertEquals(Vector(2, 3, 5).apply {
            z = 0f
        }, Vector(2, 3, 0))
    }
}