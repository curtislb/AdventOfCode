package com.curtislb.adventofcode.year2023.day19.workflow

/**
 * A machine part with ratings in several categories.
 *
 * @property x The [Category.EXTREME] rating for the part.
 * @property m The [Category.MUSICAL] rating for the part.
 * @property a The [Category.AERO] rating for the part.
 * @property s The [Category.SHINY] rating for the part.
 *
 * @constructor Creates a new instance of [MachinePart] with the specified [x], [m], [a], and [s]
 *  ratings.
 */
data class MachinePart(val x: Int, val m: Int, val a: Int, val s: Int) {
    /**
     * Returns the specified [category] rating for the part.
     */
    operator fun get(category: Category): Int = when (category) {
        Category.EXTREME -> x
        Category.MUSICAL -> m
        Category.AERO -> a
        Category.SHINY -> s
    }

    /**
     * Returns the sum of all category ratings for the part.
     */
    fun sumRatings(): Int = x + m + a + s

    override fun toString(): String = "{x=$x,m=$m,a=$a,s=$s}"

    companion object {
        /**
         * A regex that matches the category ratings for a machine part.
         */
        private val PART_REGEX = Regex("""\{x=(\d+),m=(\d+),a=(\d+),s=(\d+)}""")

        /**
         * Returns a machine part with category ratings read from the specified [string].
         *
         * The [string] must have the following format: `{x=$x,m=$m,a=$a,s=$s}`
         *
         * @throws IllegalArgumentException If [string] is not formatted correctly.
         */
        fun fromString(string: String): MachinePart {
            val matchResult = PART_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed part string: $string" }
            val (x, m, a, s) = matchResult.destructured
            return MachinePart(x = x.toInt(), m = m.toInt(), a = a.toInt(), s = s.toInt())
        }
    }
}
