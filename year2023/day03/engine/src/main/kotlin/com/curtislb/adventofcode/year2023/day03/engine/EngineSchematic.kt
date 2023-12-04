package com.curtislb.adventofcode.year2023.day03.engine

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.number.digitsToInt
import com.curtislb.adventofcode.common.number.product
import java.io.File

/**
 * The schematic for a gondola engine, consisting of a grid of digits, symbols, and empty spaces.
 *
 * @property grid The grid of characters that make up the schematic.
 *
 * @constructor Creates a new instance of [EngineSchematic] with the given character [grid].
 */
class EngineSchematic(private val grid: Grid<Char>) {
    /**
     * Returns the sum of all part numbers in the schematic.
     *
     * A part number is defined as a contiguous sequence of decimal digits that is horizontally or
     * vertically adjacent to at least one "symbol", which can be any character other than a period
     * (`.`) or digit (`'0'..'9'`).
     */
    fun sumPartNumbers(): Int {
        var total = 0
        grid.forEachPointValue { point, value ->
            if (value != '.' && !value.isDigit()) {
                total += findAdjacentPartNumbers(point).sum()
            }
        }
        return total
    }

    /**
     * Returns the sum of all gear ratios in the schematic.
     *
     * A gear ratio is defined as the product of both part numbers (see [sumPartNumbers]) that are
     * horizontally or vertically adjacent to a `*` symbol with *exactly* two adjacent part numbers.
     */
    fun sumGearRatios(): Int {
        var total = 0
        grid.forEachPointValue { point, value ->
            if (value == '*') {
                val partNumbers = findAdjacentPartNumbers(point)
                if (partNumbers.size == 2) {
                    total += partNumbers.product()
                }
            }
        }
        return total
    }

    /**
     * Returns a list of all part numbers in the schematic that are adjacent to the given [point].
     */
    private fun findAdjacentPartNumbers(point: Point): List<Int> {
        val partNumbers = mutableListOf<Int>()
        val foundRanges = mutableMapOf<Int, MutableList<IntRange>>()
        for (neighbor in point.allNeighbors()) {
            // Ignore out-of-bounds and non-digit points
            if (grid.getOrNull(neighbor)?.isDigit() != true) {
                continue
            }

            // Ignore points that are part of an already-found number
            val neighborRow = neighbor.matrixRow
            val neighborCol = neighbor.matrixCol
            if (foundRanges[neighborRow]?.any { neighborCol in it } == true) {
                continue
            }

            // Find the value and column range of the part number
            val (number, colRange) = findPartNumber(neighborRow, neighborCol)
            partNumbers.add(number)
            foundRanges.getOrPut(neighborRow) { mutableListOf() }.add(colRange)
        }
        return partNumbers
    }

    /**
     * Returns the value of the full part number with a digit at the given [rowIndex] and [colIndex]
     * in the schematic, along with an integer range representing the column indices of its digits.
     */
    private fun findPartNumber(rowIndex: Int, colIndex: Int): Pair<Int, IntRange> {
        val numberChars = ArrayDeque<Int>()

        // Add digits to the right of the starting point (inclusive)
        var digitIndex = colIndex
        while (digitIndex < grid.width && grid[rowIndex, digitIndex].isDigit()) {
            numberChars.addLast(grid[rowIndex, digitIndex].digitToInt())
            digitIndex++
        }
        val rangeEnd = digitIndex - 1

        // Add digits to the left of the starting point (exclusive)
        digitIndex = colIndex - 1
        while (digitIndex >= 0 && grid[rowIndex, digitIndex].isDigit()) {
            numberChars.addFirst(grid[rowIndex, digitIndex].digitToInt())
            digitIndex--
        }
        val rangeStart = digitIndex + 1

        // Return the part number and its column range
        val partNumber = numberChars.digitsToInt()
        return Pair(partNumber, rangeStart..rangeEnd)
    }

    companion object {
        /**
         * Returns an [EngineSchematic] with a grid of characters read from the given [file].
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): EngineSchematic {
            val grid = mutableGridOf<Char>().apply {
                file.forEachLine { addShallowRow(it.toList()) }
            }
            return EngineSchematic(grid)
        }
    }
}
