package com.curtislb.adventofcode.year2023.day19.workflow

import com.curtislb.adventofcode.common.range.size

/**
 * A set of constraints for the category ratings of a [MachinePart].
 *
 * @property x The accepted range of [Category.EXTREME] rating values.
 * @property m The accepted range of [Category.MUSICAL] rating values.
 * @property a The accepted range of [Category.AERO] rating values.
 * @property s The accepted range of [Category.SHINY] rating values.
 *
 * @constructor Creates a new instance of [PartConstraints] with the given [x], [m], [a], and [s]
 *  rating constraints.
 */
data class PartConstraints(val x: IntRange, val m: IntRange, val a: IntRange, val s: IntRange) {
    /**
     * Returns a copy of the constraints with a new [minValue] constraint for the given [category].
     */
    fun withMinConstraint(category: Category, minValue: Int): PartConstraints = when (category) {
        Category.EXTREME -> PartConstraints(minValue..x.last, m, a, s)
        Category.MUSICAL -> PartConstraints(x, minValue..m.last, a, s)
        Category.AERO -> PartConstraints(x, m, minValue..a.last, s)
        Category.SHINY -> PartConstraints(x, m, a, minValue..s.last)
    }

    /**
     * Returns a copy of the constraints with a new [maxValue] constraint for the given [category].
     */
    fun withMaxConstraint(category: Category, maxValue: Int): PartConstraints = when (category) {
        Category.EXTREME -> PartConstraints(x.first..maxValue, m, a, s)
        Category.MUSICAL -> PartConstraints(x, m.first..maxValue, a, s)
        Category.AERO -> PartConstraints(x, m, a.first..maxValue, s)
        Category.SHINY -> PartConstraints(x, m, a, s.first..maxValue)
    }

    /**
     * Returns the number of distinct [MachinePart]s that satisfy the constraints.
     */
    fun countParts(): Long =
        x.size().toLong() * m.size().toLong() * a.size().toLong() * s.size().toLong()

    override fun toString(): String = "{x=$x,m=$m,a=$a,s=$s}"
}
