package org.gtlp.util.ranges

import java.lang.Math.abs

/**
 * Progression of [Float]s from [first] to [last] with a variable [step]
 */
open class FloatProgression internal constructor(
        start: Float,
        endInclusive: Float,
        /**
         * The step of the progression.
         */
        var step: Float = 1f) : Iterable<Float> {


    init {
        if (step == 0f) throw IllegalArgumentException("Step must be non-zero")
        this.step = step
    }

    /**
     * The first element in the progression.
     */
    val first: Float = start


    /**
     * The last element of this progression.
     */
    var last: Float = endInclusive

    /**
     * Creates a new [FloatProgression] with a specified [step].
     *
     * @param step the step of this progression
     *
     * @return a new [FloatProgression] with the specified [step]
     */
    infix fun step(step: Float): FloatProgression {
        return FloatProgression.fromClosedRange(first, last, if (this.step > 0) step else -step)
    }

    override fun iterator(): FloatIterator = FloatProgressionIterator(first, last, step)

    /** Checks if the progression is empty. */
    open fun isEmpty(): Boolean = if (step > 0) first > last else first < last

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as FloatProgression

        if (step != other.step) return false
        if (first != other.first) return false
        if (last != other.last) return false

        return true
    }

    override fun hashCode(): Int =
            if (isEmpty()) -1 else (31f * (31f * first + last) + step).toInt()

    override fun toString(): String = if (step > 0) "$first..$last step $step" else "$first downTo $last step ${-step}"

    companion object {
        /**
         * Creates a [FloatProgression] within the specified bounds of a closed range.

         * The progression starts with the [rangeStart] value and goes toward the [rangeEnd] value not excluding it, with the specified [step].
         * In order to go backwards the [step] must be negative.
         */
        fun fromClosedRange(rangeStart: Float, rangeEnd: Float, step: Float): FloatProgression = FloatProgression(rangeStart, rangeEnd, step)
    }

    /**
     * A [FloatIterator] over a [FloatProgression].
     * Non-constant length because of variable [step] (allows dynamically increasing precision).
     */
    class FloatProgressionIterator(first: Float, last: Float, var step: Float) : FloatIterator() {
        private var next = first
        private val finalElement = last
        private var hasNext: Boolean = if (step > 0) first <= last else first >= last


        override fun hasNext(): Boolean = hasNext

        override fun nextFloat(): Float {
            val value = next
            if (abs(value) + abs(step) > abs(finalElement)) {
                hasNext = false
            } else {
                next += step
            }
            return value
        }
    }
}