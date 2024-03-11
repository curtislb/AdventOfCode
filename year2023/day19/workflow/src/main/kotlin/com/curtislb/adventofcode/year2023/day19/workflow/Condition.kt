package com.curtislb.adventofcode.year2023.day19.workflow

/**
 * A condition that tests whether a part matches a specified requirement.
 */
sealed interface Condition {
    /**
     * Returns `true` if the given [part] satisfies the condition.
     */
    fun isMatch(part: MachinePart): Boolean

    /**
     * A condition that is true for a part with a rating below a given threshold.
     *
     * @property category The rating category for the condition.
     * @property threshold The rating value below which the condition is true.
     */
    data class Less(val category: Category, val threshold: Int) : Condition {
        override fun isMatch(part: MachinePart): Boolean = part[category] < threshold
        override fun toString(): String = "${category.char}<$threshold"
    }

    /**
     * A condition that is true for a part with a rating above a given threshold.
     *
     * @property category The rating category for the condition.
     * @property threshold The rating value above which the condition is true.
     */
    data class More(val category: Category, val threshold: Int) : Condition {
        override fun isMatch(part: MachinePart): Boolean = part[category] > threshold
        override fun toString(): String = "${category.char}>$threshold"
    }

    /**
     * A condition that is true for any part.
     */
    data object True : Condition {
        override fun isMatch(part: MachinePart): Boolean = true
        override fun toString(): String = "true"
    }
}
