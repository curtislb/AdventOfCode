package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * A snailfish number that consists of nested left and right snailfish numbers.
 *
 * @param left The nested snailfish number representing the left half of this number.
 * @param right The nested snailfish number representing the right half of this number.
 */
data class NumberPair(val left: SnailfishNumber, val right: SnailfishNumber) : SnailfishNumber() {
    /**
     * Returns the magnitude of this number.
     *
     * The magnitude of a [NumberPair] equals 3 times the magnitude of the [left] number, plus twice
     * the magnitude of the [right] number.
     */
    override fun magnitude(): Int = 3 * left.magnitude() + 2 * right.magnitude()

    /**
     * Returns the result of applying the "explode" operation to this number.
     *
     * The result of applying this operation to a [NumberPair] is:
     *
     * - [RegularNumber.ZERO] with [left] and [right] exploded values, if it is a [RegularNumber]
     *   pair with a [depth] of 4 or greater. These exploded values are added to the [RegularNumber]
     *   values immediately to the left and right (respectively) of this pair within the outermost
     *   snailfish number.
     * - The result of applying [explode] to [left], if [left] contains a [RegularNumber] pair with
     *   a [depth] of 4 or greater.
     * - The result of applying [explode] to [right], if [right] contains a [RegularNumber] pair
     *   with a [depth] of 4 or greater but [left] does not.
     * - The [NumberPair], unchanged, in all other cases.
     */
    override fun explode(depth: Int): ExplodeResult {
        if (left is RegularNumber && right is RegularNumber) {
            return if (depth >= 4) {
                // Regular number pair with a depth >= 4: explodes
                ExplodeResult(
                    number = RegularNumber.ZERO,
                    isChanged = true,
                    leftValue = left.value,
                    rightValue = right.value
                )
            } else {
                // Regular number pair with a depth < 4: does not explode
                ExplodeResult(number = this, isChanged = false)
            }
        }

        // If left has an eligible regular number pair, explode it and propagate the result
        if (left is NumberPair) {
            val result = left.explode(depth + 1)
            if (result.isChanged) {
                val newPair = NumberPair(result.number, right.addToLeftmost(result.rightValue))
                return ExplodeResult(
                    number = newPair,
                    isChanged = true,
                    leftValue = result.leftValue
                )
            }
        }

        // If right has an eligible regular number pair, explode it and propagate the result
        if (right is NumberPair) {
            val result = right.explode(depth + 1)
            if (result.isChanged) {
                val newPair = NumberPair(left.addToRightmost(result.leftValue), result.number)
                return ExplodeResult(
                    number = newPair,
                    isChanged = true,
                    rightValue = result.rightValue
                )
            }
        }

        // Neither left nor right has an eligible regular number pair
        return ExplodeResult(this, isChanged = false)
    }

    /**
     * Returns the result of applying the "split" reduction operation to this number.
     *
     * The result of applying this operation to a [NumberPair] is:
     *
     * - The result of applying [split] to [left], if [left] contains a [RegularNumber] with a
     *   value of 10 or greater.
     * - The result of applying [split] to [right], if [right] contains a [RegularNumber] with a
     *   value of 10 or greater but [left] does not.
     * - The [NumberPair], unchanged, in all other cases.
     */
    override fun split(): SplitResult {
        val leftResult = left.split()
        if (leftResult.isChanged) {
            val newPair = copy(left = leftResult.number)
            return SplitResult(number = newPair, isChanged = true)
        }

        val rightResult = right.split()
        if (rightResult.isChanged) {
            val newPair = copy(right = rightResult.number)
            return SplitResult(number = newPair, isChanged = true)
        }

        return SplitResult(number = this, isChanged = false)
    }

    override fun addToLeftmost(value: Int): SnailfishNumber = copy(left = left.addToLeftmost(value))

    override fun addToRightmost(value: Int): SnailfishNumber =
        copy(right = right.addToRightmost(value))

    override fun toString(): String = "[$left,$right]"
}
