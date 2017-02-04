package org.gtlp.util.ranges

/**
 * A range of [Float]s from [start] to [endInclusive] and a step of [step].
 */
class FloatRange(override val start: Float, override val endInclusive: Float, step: Float = 1f) : FloatProgression(start, endInclusive, step), ClosedRange<Float> {

    override fun contains(value: Float): Boolean = first <= value && value <= last

    override fun isEmpty(): Boolean = first > last

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as FloatRange

        if (start != other.start) return false
        if (endInclusive != other.endInclusive) return false

        return true
    }

    override fun hashCode(): Int =
            if (isEmpty()) -1 else (31f * first + last).toInt()

    override fun toString(): String = "$first..$last"


    companion object {
        /** An empty range. */
        val EMPTY: FloatRange = FloatRange(1f, 0f)
    }

    /**
     * Convenience constructor to use any type of [Number].
     * Converts all parameters to [Float].
     */
    constructor(start: Number, endInclusive: Number, step: Number) : this(start.toFloat(), endInclusive.toFloat(), step.toFloat())
}

/**
 * Creates a range from this [Comparable] value to the specified [that] value. This value
 * needs to be smaller than [that] value, otherwise the returned range will be empty.
 */
operator fun Float.rangeTo(that: Float): FloatRange = FloatRange(this, that)