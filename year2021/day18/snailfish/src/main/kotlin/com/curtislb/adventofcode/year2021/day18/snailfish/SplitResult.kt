package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * The result of applying the "split" reduction operation to a snailfish number.
 *
 * @param number A new number to replace the current one in the outermost snailfish number.
 * @param isChanged Whether the operation changed [number].
 */
internal class SplitResult(val number: SnailfishNumber, val isChanged: Boolean)
