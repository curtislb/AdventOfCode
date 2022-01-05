package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * TODO
 */
data class NumberPair(val left: SnailfishNumber, val right: SnailfishNumber) : SnailfishNumber() {
    override fun magnitude(): Int = 3 * left.magnitude() + 2 * right.magnitude()

    override fun explode(depth: Int): ExplodeResult {
        if (left is RegularNumber && right is RegularNumber) {
            return if (depth >= 4) {
                ExplodeResult(RegularNumber(0), isChanged = true, left.value, right.value)
            } else {
                ExplodeResult(this, isChanged = false)
            }
        }

        if (left is NumberPair) {
            val result = left.explode(depth + 1)
            if (result.isChanged) {
                val newPair = NumberPair(result.number, right.addToLeftmost(result.rightValue))
                return ExplodeResult(newPair, isChanged = true, leftValue = result.leftValue)
            }
        }

        if (right is NumberPair) {
            val result = right.explode(depth + 1)
            if (result.isChanged) {
                val newPair = NumberPair(left.addToRightmost(result.leftValue), result.number)
                return ExplodeResult(newPair, isChanged = true, rightValue = result.rightValue)
            }
        }

        return ExplodeResult(this, isChanged = false)
    }

    override fun split(): SplitResult {
        val leftResult = left.split()
        if (leftResult.isChanged) {
            return SplitResult(copy(left = leftResult.number), isChanged = true)
        }

        val rightResult = right.split()
        if (rightResult.isChanged) {
            return SplitResult(copy(right = rightResult.number), isChanged = true)
        }

        return SplitResult(this, isChanged = false)
    }

    override fun addToLeftmost(value: Int): SnailfishNumber =
        copy(left = left.addToLeftmost(value))

    override fun addToRightmost(value: Int): SnailfishNumber =
        copy(right = right.addToRightmost(value))

    override fun toString(): String = "[$left,$right]"
}
