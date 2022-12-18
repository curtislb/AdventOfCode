package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * The result of applying the "explode" reduction operation to a snailfish number.
 *
 * @param number A new number to replace the current one in the outermost snailfish number.
 * @param isChanged Whether the operation changed [number] or produced any other values.
 * @param leftValue A value to add to the regular number immediately left of the exploded pair.
 * @param rightValue A value to add to the regular number immediately right of the exploded pair.
 */
internal data class ExplodeResult(
    val number: SnailfishNumber,
    val isChanged: Boolean,
    val leftValue: Int = 0,
    val rightValue: Int = 0
)
