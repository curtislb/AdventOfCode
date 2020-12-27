package com.curtislb.adventofcode.year2020.day23.cups

/**
 * TODO
 */
class Cup(val label: Int, var next: Cup? = null) {
    override fun toString(): String = "$label(->${next?.label})"
}
