package com.curtislb.adventofcode.year2021.day03.diagnostic

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.addRowWith
import com.curtislb.adventofcode.common.grid.forEachIndexed
import com.curtislb.adventofcode.common.grid.mapRow
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.math.digitsToInt

/**
 * A submarine diagnostic report, consisting of a series of equal-length binary values.
 *
 * @param lines A list of strings, representing the binary values in the report.
 *
 * @throws IllegalArgumentException If any line in [lines] contains an invalid (non-bit) character,
 *  or if not all lines contain the same number of bits.
 */
class DiagnosticReport(lines: List<String>) {
    /**
     * A boolean grid representing the binary digit values of the report.
     */
    private val boolGrid: Grid<Boolean> = mutableGridOf<Boolean>().apply {
        lines.forEach { line ->
            addRowWith {
                line.trim().map { bitChar ->
                    when (bitChar) {
                        '0' -> add(false)
                        '1' -> add(true)
                        else -> throw IllegalArgumentException("Invalid binary digit: $bitChar")
                    }
                }
            }
        }
    }

    /**
     * Private backing property for [gammaRate].
     */
    private var _gammaRate: Int = UNSET_RATE

    /**
     * The gamma rate for the submarine, according to this report.
     */
    val gammaRate: Int
        get() {
            if (_gammaRate == UNSET_RATE) {
                initGammaAndEpsilonRates()
            }
            return _gammaRate
        }

    /**
     * Private backing property for [epsilonRate].
     */
    private var _epsilonRate: Int = UNSET_RATE

    /**
     * The epsilon rate for the submarine, according to this report.
     */
    val epsilonRate: Int
        get() {
            if (_epsilonRate == UNSET_RATE) {
                initGammaAndEpsilonRates()
            }
            return _epsilonRate
        }

    /**
     * The power consumption rate of the submarine, according to this report.
     */
    val powerConsumption: Int get() = gammaRate * epsilonRate

    /**
     * The oxygen generator rating for the submarine, according to this report.
     */
    val oxygenGeneratorRating: Int by lazy {
        findRating { onesCount, bitsCount -> commonBit(onesCount, bitsCount) == 1 }
    }

    /**
     * The CO2 scrubber rating for the submarine, according to this report.
     */
    val co2ScrubberRating: Int by lazy {
        findRating { onesCount, bitsCount -> commonBit(onesCount, bitsCount) == 0 }
    }

    /**
     * The life support rating for the submarine, according to this report.
     */
    val lifeSupportRating: Int get() = oxygenGeneratorRating * co2ScrubberRating

    /**
     * Finds and sets the values for [gammaRate] and [epsilonRate].
     */
    private fun initGammaAndEpsilonRates() {
        // Sum the bits for each input column
        val bitSums = IntArray(boolGrid.width)
        boolGrid.forEachIndexed { _, colIndex, isOneBit ->
            if (isOneBit) {
                bitSums[colIndex]++
            }
        }

        // Find the gamma rate by concatenating the most common bits for all columns
        val commonBits = bitSums.map { commonBit(it, boolGrid.height) }
        _gammaRate = commonBits.digitsToInt(radix = 2)

        // Find the epsilon rate by inverting the bits of the gamma rate
        val bitmask = (2 shl (boolGrid.width - 1)) - 1
        _epsilonRate = bitmask - _gammaRate
    }

    /**
     * Returns the rating for a system by applying a filtering process with the given [bitCriteria].
     *
     * The filtering process works as follows:
     *
     * - Starting from the leftmost column of the report, use [bitCriteria] to select a bit (0 if it
     *   returns `false`, or 1 if it returns `true`), where `onesCount` is the number of 1 bits in
     *   the column, and `bitsCount` is the total number of bits in the column. Keep only the rows
     *   whose bit in that column matches the selected bit.
     * - Proceed one column to the right and repeat the process, considering only bits in rows that
     *   were kept from the previous step.
     * - Continue on in this fashion until only one row remains, then return the value of that row
     *   interpreted as a big-endian binary integer.
     */
    private fun findRating(bitCriteria: (onesCount: Int, bitsCount: Int) -> Boolean): Int {
        // Apply bit filtering to find the row for this rating
        val allRowIndices = boolGrid.rowIndices.toList()
        val ratingRowIndex = boolGrid.columnIndices.fold(allRowIndices) { rowIndices, colIndex ->
            if (rowIndices.size == 1) {
                rowIndices
            } else {
                val bitSum = rowIndices.count { boolGrid[it, colIndex] }
                val isOneBit = bitCriteria(bitSum, rowIndices.size)
                rowIndices.filter { boolGrid[it, colIndex] == isOneBit }
            }
        }.first()

        // Convert the selected row to an integer rating
        val ratingBits = boolGrid.mapRow(ratingRowIndex) { if (it) 1 else 0 }
        return ratingBits.digitsToInt(radix = 2)
    }

    companion object {
        /**
         * A placeholder value indicating that a given rate has not been initialized.
         */
        private const val UNSET_RATE = -1

        /**
         * Returns the most common bit (0 or 1) by comparing the given [onesCount] to the total
         * [bitsCount].
         *
         * If there are equally many 0 and 1 bits, this function returns 1.
         */
        private fun commonBit(onesCount: Int, bitsCount: Int): Int =
            if (onesCount >= bitsCount - onesCount) 1 else 0
    }
}
