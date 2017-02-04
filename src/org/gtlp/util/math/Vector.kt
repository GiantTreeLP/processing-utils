package org.gtlp.util.math

import java.io.Serializable
import java.util.*

/**
 * Data class for two-dimensional or three-dimensional vectors.
 * Immutable.
 */
data class Vector(var x: Float, var y: Float, var z: Float = 0f) : Comparable<Vector>, Cloneable, Serializable {

    /**
     * Multiplies each element by [factor].
     *
     * @param factor the factor to multiply by
     *
     * @return a new vector multiplied by the [factor]
     */
    infix operator fun times(factor: Float): Vector {
        val vector = Vector(this)
        vector.x *= factor
        vector.y *= factor
        vector.z *= factor
        return vector
    }

    /**
     * Multiplies each element by their respective element in [factor].
     *
     * @param factor the vector with factors to multiply by
     *
     * @return a new vector multiplied by the [factor]
     */
    infix operator fun times(factor: Vector): Vector {
        val vector = Vector(this)
        vector.x *= factor.x
        vector.y *= factor.y
        vector.z *= factor.z
        return vector
    }

    /**
     * Divides each element by [divisor].
     *
     * @param divisor the divisor to divide by
     *
     * @return a new vector divided by the [divisor]
     */
    infix operator fun div(divisor: Float): Vector {
        val vector = Vector(this)
        vector.x /= divisor
        vector.y /= divisor
        vector.z /= divisor
        return vector
    }

    /**
     * Divides each element by their respective element in [divisor].
     *
     * @param divisor the vector with divisors to divide by
     *
     * @return a new vector divided by the [divisor]
     */
    infix operator fun div(divisor: Vector): Vector {
        val vector = Vector(this)
        vector.x /= divisor.x
        vector.y /= divisor.y
        vector.z /= divisor.z
        return vector
    }

    /**
     * Subtracts each element by [subtrahend].
     *
     * @param subtrahend the subtrahend to subtract by
     *
     * @return a new vector minus the [subtrahend]
     */
    infix operator fun minus(subtrahend: Number): Vector {
        val vector = Vector(this)
        vector.x -= subtrahend.toFloat()
        vector.y -= subtrahend.toFloat()
        vector.z -= subtrahend.toFloat()
        return vector
    }

    /**
     * Subtracts each element by their respective element in [subtrahend].
     *
     * @param subtrahend the vector with subtrahends to subtract by
     *
     * @return a new vector minus the [subtrahend]
     */
    infix operator fun minus(subtrahend: Vector): Vector {
        val vector = Vector(this)
        vector.x -= subtrahend.x
        vector.y -= subtrahend.y
        vector.z -= subtrahend.z
        return vector
    }

    /**
     * Adds each element to [addend].
     *
     * @param addend the addend to add to
     *
     * @return a new vector plus the [addend]
     */
    infix operator fun plus(addend: Number): Vector {
        val vector = Vector(this)
        vector.x += addend.toFloat()
        vector.y += addend.toFloat()
        vector.z += addend.toFloat()
        return vector
    }

    /**
     * Adds each element by their respective element in [addend].
     *
     * @param addend the vector with addends to add to
     *
     * @return a new vector plus the [addend]
     */
    infix operator fun plus(addend: Vector): Vector {
        val vector = Vector(this)
        vector.x += addend.x
        vector.y += addend.y
        vector.z += addend.z
        return vector
    }

    /**
     * Calculates the magnitude aka. length of this vector.
     *
     * @return the magnitude of this vector
     */
    fun mag(): Float {
        return StrictMath.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
    }

    /**
     * Calculates the squared magnitude of this vector.
     * Quicker than [mag].
     *
     * @return the magnitude of this vector ^2
     */
    fun magSq(): Float {
        return x * x + y * y + z * z
    }

    /**
     * Calculates the dot-product of this vector and another [vector]
     *
     * @param vector the vector to calculate the dot-product with
     *
     * @return the dot product of this and [vector]
     */
    infix fun dot(vector: Vector): Float {
        return x * vector.x + y * vector.y + z * vector.z
    }

    /**
     * Calculates the cross-product of this vector and another [vector]
     *
     * @param vector the vector to calculate the cross-product with
     *
     * @return the cross-product of this and [vector] as a [Vector]
     */
    infix fun cross(vector: Vector): Vector {
        return Vector(
                y * vector.z - z * vector.y,
                z * vector.x - x * vector.z,
                x * vector.y - y * vector.x)
    }

    /**
     * Normalizes this vector to a length of 1.
     * If the length is *0*, it just clones that vector.
     *
     * @return this vector normalized to a length of 1
     */
    fun normalize(): Vector {
        return if (mag() != 0f) div(0f) else clone()
    }

    /**
     * Sets the magnitude (length) of this vector to [mag]
     *
     * @return this vector with it's magnitude set to [mag]
     */
    infix fun setMag(mag: Float): Vector {
        return normalize() * mag
    }

    /**
     * Limits the magnitude of this vector to [limit]
     *
     * @return this vector limited to a magnitude [limit]
     */
    infix fun limit(limit: Float): Vector {
        return if (magSq() < limit * limit) clone() else normalize() * limit
    }

    /**
     * Linear interpolation between two vectors.
     *
     * @param vector the vector to interpolate to
     * @param amount the amount to interpolate (between 0 and 1, inclusive)
     *
     * @return a new vector that sits between this and [vector] by [amount]*100 percent
     */
    fun lerp(vector: Vector, amount: Float): Vector {
        return this + (vector - this) * amount
    }

    /**
     * Sets the specified or all elements of this vector.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     *
     * @return this
     */
    fun set(x: Number = this.x, y: Number = this.y, z: Number = this.z): Vector {
        this.x = x.toFloat()
        this.y = y.toFloat()
        this.z = z.toFloat()
        return this
    }

    /**
     * Sets each element of this vector to [vector]s values.
     * Essentially copies [vector]s values to this ones values.
     *
     * @param vector the vector to "copy"
     *
     * @return this vector with the values of [vector]
     */
    fun set(vector: Vector): Vector {
        this.x = vector.x
        this.y = vector.y
        this.z = vector.z
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Vector

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    /**
     * Compares [other] to this vector.
     * Comparison happens first at [x] then [y] and last [z].
     *
     * @param other the vector to compare to
     *
     * @return zero (0) if both vectors are equal, else refer to [Float.compareTo]
     * @see Float.compareTo
     */
    override fun compareTo(other: Vector): Int {
        if (x != other.x) {
            return x.compareTo(other.x)
        }
        if (y != other.y) {
            return y.compareTo(other.y)
        }
        if (z != other.z) {
            return z.compareTo(other.z)
        }

        return 0
    }

    public override fun clone(): Vector {
        return Vector(x, y, z)
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y, z)
    }

    /**
     * Copy constructor
     */
    constructor(vector: Vector) : this(vector.x, vector.y, vector.z)

    /**
     * Catch-all constructor, converts parameters to [Float]
     */
    constructor(x: Number, y: Number, z: Number = 0) : this(x.toFloat(), y.toFloat(), z.toFloat())


    companion object {
        /**
         * General origin vector (0, 0, 0)
         */
        val ZERO = Vector(0, 0)

        /**
         * Infinite vector. No real use.
         */
        val INF = Vector(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)

        /**
         * Invalid vector. Used to indicate no position.
         */
        val NAN = Vector(Float.NaN, Float.NaN, Float.NaN)

        /**
         * Creates a two-dimensional vector heading to [angle].
         * Z = 0.
         *
         * @param angle the angle to head towards
         *
         * @return a new [Vector] heading towards [angle]
         */
        fun fromAngle(angle: Number): Vector = Vector(Math.cos(angle.toDouble()), Math.sin(angle.toDouble()))
    }

}