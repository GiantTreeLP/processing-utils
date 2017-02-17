package org.gtlp.util.path

import org.gtlp.util.math.Vector
import java.util.*

/**
 * Path defined by [Vector]s at a specified progress.
 * Variable [length] for ease of use (speed of objects following this path, for instance).
 *
 * Make sure to add at least two points.
 *
 * Linear interpolation between two [Vector]s.
 */
data class Path(val length: Float) {

    private var pointsMap = mutableMapOf<Float, Vector>()

    /**
     * Returns a [Vector] of position for the given progress.
     * Requires at least two points to be present in [pointsMap]
     *
     * @param progress between 0 and [length]
     *
     * @return A [Vector] defining the position at the given progress
     */
    @Throws(InconsistentDataException::class)
    operator fun get(progress: Float): Vector {
        val left = getNearestBefore(progress)
        val right = getNearestAfter(progress)
        if (left == null || right == null) {
            throw InconsistentDataException("No suitable points found")
        }
        if (left.value == right.value) {
            return left.value
        }
        val interval = right.key - left.key
        val offset = progress - left.key
        return left.value.lerp(right.value, offset / interval)
    }

    /**
     * Get all points that create this path
     *
     * @return a collection of [Vector]s
     */
    fun get(): Collection<Vector> {
        return pointsMap.values.toSet()
    }

    /**
     * Sets the position at a certain progress and sorts the map.
     *
     * @param progress the progress as a [Float] between 0 and [length] (not checked).
     * @param position the position at [progress]
     */
    operator fun set(progress: Float, position: Vector) {
        pointsMap[progress] = position
        pointsMap.toSortedMap()
    }

    private fun getNearestBefore(progress: Float): MutableMap.MutableEntry<Float, Vector>? {
        var candidate: MutableMap.MutableEntry<Float, Vector>? = null
        var candidateDistance: Float = Float.MAX_VALUE
        pointsMap.entries.forEach {
            if (it.key > progress) return@forEach
            val distance = progress - it.key
            if (distance >= 0 && distance <= candidateDistance) {
                candidate = it
                candidateDistance = distance
            }
        }
        return candidate
    }

    private fun getNearestAfter(progress: Float): MutableMap.MutableEntry<Float, Vector>? {
        var candidate: MutableMap.MutableEntry<Float, Vector>? = null
        var candidateDistance: Float = Float.MAX_VALUE
        pointsMap.entries.reversed().forEach {
            if (it.key < progress) return@forEach
            val distance = it.key - progress
            if (distance >= 0 && distance <= candidateDistance) {
                candidate = it
                candidateDistance = distance
            }
        }
        return candidate
    }

    /**
     * Convenience method to set any [Number] as progress.
     *
     * @param progress a number defining the progress, will be converted to [Float]
     * @param position the position at [progress]
     *
     * @see [set]
     */
    operator fun set(progress: Number, position: Vector) {
        set(progress.toFloat(), position)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Path

        if (length != other.length) return false
        if (pointsMap != other.pointsMap) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(length, pointsMap)
    }
}