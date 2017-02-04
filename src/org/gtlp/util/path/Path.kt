package org.gtlp.util.path

import org.gtlp.util.math.Vector
import processing.core.PApplet
import java.awt.Color
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
     * Sets the position at a certain progress.
     *
     * @param progress the progress as a [Float] between 0 and [length] (not checked).
     * @param position the position at [progress]
     */
    operator fun set(progress: Float, position: Vector) {
        pointsMap[progress] = position
    }

    private fun getNearestBefore(progress: Float): MutableMap.MutableEntry<Float, Vector>? {
        var candidate: MutableMap.MutableEntry<Float, Vector>? = null
        var candidateDistance: Float = Float.MAX_VALUE
        pointsMap.entries.forEach {
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
        pointsMap.entries.forEach {
            val distance = it.key - progress
            if (distance >= 0 && distance <= candidateDistance) {
                candidate = it
                candidateDistance = distance
            }
        }
        return candidate
    }

    /**
     * Draws this path to the surface of a [PApplet].
     *
     * @param applet the [PApplet] to draw at.
     * @param strokeColor the color for this path, default to [Color.WHITE]
     * @param strokeWeight the width of the path, default to **4f**
     */
    fun draw(applet: PApplet, strokeColor: Color = Color.WHITE, strokeWeight: Float = 4f) {
        applet.apply {
            pushMatrix()
            pushStyle()
            beginShape()
            noFill()
            stroke(strokeColor.rgb)
            strokeWeight(strokeWeight)
            pointsMap.forEach { vertex(it.value.x, it.value.y) }
            endShape()
            popStyle()
            popMatrix()
        }
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