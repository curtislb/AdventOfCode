package com.curtislb.adventofcode.year2023.day09.oasis

/**
 * An OASIS report, which allows past or future values to be extrapolated based on list of observed
 * historical values.
 *
 * @param historicalValues A list of observed values in the order they occurred.
 *
 * @constructor Creates a new instance of [OasisReport] with the given list of `historicalValues`.
 */
class OasisReport(historicalValues: List<Int>) {
    init {
        require(historicalValues.isNotEmpty()) { "Must have at least one historical value" }
    }

    /**
     * A list of rows, where the first row contains historical and extrapolated values and each
     * subsequent row contains the differences between adjacent values in the row above it.
     */
    private val rows: MutableList<ArrayDeque<Int>> =
        mutableListOf(ArrayDeque(historicalValues)).apply {
            var lastRow = last()
            while (lastRow.any { it != lastRow.first() }) {
                lastRow = ArrayDeque(lastRow.windowed(2).map { it[1] - it[0] })
                add(lastRow)
            }
        }

    /**
     * Extrapolates and returns the value that will occur immediately after all values currently in
     * the report, including previously extrapolated values.
     */
    fun extrapolateNext(): Int {
        var newValue = rows.last().last()
        for (index in rows.lastIndex downTo 1) {
            rows[index].addLast(newValue)
            newValue += rows[index - 1].last()
        }
        rows.first().addLast(newValue)
        return newValue
    }

    /**
     * Extrapolates and returns the value that would have occurred immediately before all values
     * currently in the report, including previously extrapolated values.
     */
    fun extrapolatePrevious(): Int {
        var newValue = rows.last().first()
        for (index in rows.lastIndex downTo 1) {
            rows[index].addFirst(newValue)
            newValue = rows[index - 1].first() - newValue
        }
        rows.first().addFirst(newValue)
        return newValue
    }
}
