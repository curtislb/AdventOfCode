package com.curtislb.adventofcode.year2021.day13.origami

import com.curtislb.adventofcode.common.geometry.Point

/**
 * An instruction describing a single fold that should be applied to an [OrigamiSheet].
 *
 * @param foldDirection The direction in which part of the sheet should be folded.
 * @param axisOffset The non-negative horizontal or vertical offset of the fold axis.
 *
 * @throws IllegalArgumentException If [axisOffset] is negative.
 */
data class FoldInstruction(val foldDirection: FoldDirection, val axisOffset: Int) {
    init {
        require(axisOffset >= 0) { "Axis offset must be non-negative: $axisOffset" }
    }

    /**
     * Returns the result of applying this fold instruction to the given [point].
     *
     * The rules for applying a fold instruction to a point are as follows:
     * - If the [foldDirection] is [FoldDirection.LEFT], reflect the point across the line
     *   `x = axisOffset` *if and only if* the point's x-coordinate is greater than [axisOffset].
     * - If the [foldDirection] is [FoldDirection.UP], reflect the point across the line
     *   `y = -axisOffset` *if and only if* the point's y-coordinate is less than -[axisOffset].
     */
    fun fold(point: Point): Point = when (foldDirection) {
        FoldDirection.LEFT -> {
            if (point.x > axisOffset) point.copy(x = axisOffset - (point.x - axisOffset)) else point
        }

        FoldDirection.UP -> {
            // Coordinate for the y-axis should be negative (or zero)
            val axisY = -axisOffset
            if (point.y < axisY) point.copy(y = axisY - (point.y - axisY)) else point
        }
    }

    override fun toString(): String {
        val axis = when (foldDirection) {
            FoldDirection.LEFT -> "x"
            FoldDirection.UP -> "y"
        }
        return "fold along $axis=$axisOffset"
    }

    companion object {
        /**
         * A regex matching the string representation of a fold instruction.
         */
        private val INSTRUCTION_REGEX = Regex("""\s*fold\s+along\s+([xy])\s*=\s*(\d+)\s*""")

        /**
         * Returns a [FoldInstruction] from a [string] of the form `"fold along $axis=$offset"`,
         * where `axis` is `"x"` or `"y"` and `offset` is a non-negative decimal integer.
         *
         * @throws IllegalArgumentException If [string] is not of the required form.
         */
        fun fromString(string: String): FoldInstruction {
            val matchResult = INSTRUCTION_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed instruction: $string" }

            val (axisString, offsetString) = matchResult.destructured
            val axisOffset = offsetString.toInt()
            return when (axisString) {
                "x" -> FoldInstruction(FoldDirection.LEFT, axisOffset)
                "y" -> FoldInstruction(FoldDirection.UP, axisOffset)
                else -> throw IllegalArgumentException("Fold axis must be x or y: $axisString")
            }
        }
    }
}
