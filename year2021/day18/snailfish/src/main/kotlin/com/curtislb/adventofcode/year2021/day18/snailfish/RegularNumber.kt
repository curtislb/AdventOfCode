package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * A snailfish number that consists of a single integer value.
 *
 * @param value The integer value represented by this number.
 */
data class RegularNumber(val value: Int) : SnailfishNumber() {
    /**
     * Returns the magnitude of this number.
     *
     * The magnitude of a [RegularNumber] equals its [value].
     */
    override fun magnitude(): Int = value

    /**
     * Returns the result of applying the "explode" operation to this number.
     *
     * The result of applying this operation to a [RegularNumber] is always that number, unchanged.
     */
    override fun explode(depth: Int): ExplodeResult = ExplodeResult(this, isChanged = false)

    /**
     * Returns the result of applying the "split" reduction operation to this number.
     *
     * The result of applying this operation to a [RegularNumber] is:
     *
     * - The number, unchanged, if [value] is less than 10.
     * - A [NumberPair] consisting of `floor(value / 2)` and `ceil(value / 2)`, if [value] is 10 or
     *   greater.
     */
    override fun split(): SplitResult = if (value < 10) {
        SplitResult(this, isChanged = false)
    } else {
        val splitNumber = NumberPair(RegularNumber(value / 2), RegularNumber((value + 1) / 2))
        SplitResult(splitNumber, isChanged = true)
    }

    override fun addToLeftmost(value: Int): SnailfishNumber = RegularNumber(this.value + value)

    override fun addToRightmost(value: Int): SnailfishNumber = RegularNumber(this.value + value)

    override fun toString(): String = value.toString()

    companion object {
        /**
         * A [RegularNumber] representing the value 0.
         */
        val ZERO = RegularNumber(0)
    }
}
