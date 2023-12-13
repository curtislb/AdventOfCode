package com.curtislb.adventofcode.common.number

import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Returns both real-number roots of the quadratic equation [a]x^2 + [b]x + [c] = 0.
 *
 * If the equation has no real-number roots, this function instead returns `Pair(NaN, NaN)`. If the
 * equation has exactly one real-number root `x0`, this function returns `Pair(x0, x0)`.
 *
 * The roots `(r1, r2)` returned by this function are ordered, such that `r1 <= r2`.
 */
fun quadraticRoots(a: Double, b: Double, c: Double): Pair<Double, Double> {
    val discriminant = b * b - 4.0 * a * c
    return if (discriminant < 0.0) {
        Pair(Double.NaN, Double.NaN)
    } else {
        val twoA = 2.0 * a
        val x0 = -b / twoA
        val deltaX = abs(sqrt(discriminant) / twoA)
        return Pair(x0 - deltaX, x0 + deltaX)
    }
}
