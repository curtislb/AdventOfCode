package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * TODO
 */
internal data class ExplodeResult(
    val number: SnailfishNumber,
    val isChanged: Boolean,
    val leftValue: Int = 0,
    val rightValue: Int = 0
)
