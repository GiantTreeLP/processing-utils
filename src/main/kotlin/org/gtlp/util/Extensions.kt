package org.gtlp.util

import processing.core.PApplet

/**
 * Extensions to various Processing classes
 */

/**
 * Converts all parameters to [Float]. For convenience only.
 *
 * @see PApplet.map
 */
fun map(value: Number, start1: Number, stop1: Number, start2: Number, stop2: Number): Float {
    return PApplet.map(value.toFloat(), start1.toFloat(), stop1.toFloat(), start2.toFloat(), stop2.toFloat())
}