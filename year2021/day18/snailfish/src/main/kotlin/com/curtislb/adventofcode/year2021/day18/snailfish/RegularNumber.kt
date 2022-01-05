package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * TODO
 */
data class RegularNumber(val value: Int) : SnailfishNumber() {
    override fun magnitude(): Int = value

    override fun explode(depth: Int): ExplodeResult = ExplodeResult(this, isChanged = false)

    override fun split(): SplitResult = if (value < 10) {
        SplitResult(this, isChanged = false)
    } else {
        val splitNumber = NumberPair(RegularNumber(value / 2), RegularNumber((value + 1) / 2))
        SplitResult(splitNumber, isChanged = true)
    }

    override fun addToLeftmost(value: Int): SnailfishNumber = RegularNumber(this.value + value)

    override fun addToRightmost(value: Int): SnailfishNumber = addToLeftmost(value)

    override fun toString(): String = value.toString()
}
