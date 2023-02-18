package com.curtislb.adventofcode.year2021.day15.chiton

import com.curtislb.adventofcode.common.collection.getCyclic
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.graph.WeightedGraph
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.toGrid
import java.io.File
import kotlin.math.abs

/**
 * A collection of risk levels associated with entering each space of a chiton-filled 2D grid.
 *
 * The full grid consists of a [scaleFactor] x [scaleFactor] tiling of [baseRiskGrid]. The risk
 * level for a given space equals that of the corresponding space in [baseRiskGrid], plus the
 * Manhattan distance from its tile to the top-left, calculated cyclically in the range `1..9`.
 *
 * @param baseRiskGrid A 2D grid of risk levels for the top-left tile in the full map.
 * @param scaleFactor The number of times [baseRiskGrid] is tiled right and down in the full map.
 *
 * @throws IllegalArgumentException If [baseRiskGrid] has values not in `1..9`, or if [scaleFactor]
 *  is negative or zero.
 */
class RiskLevelMap(private val baseRiskGrid: Grid<Int>, private val scaleFactor: Int = 1) {
    init {
        require(baseRiskGrid.values().all { it in 1..9 }) {
            "Base risk levels must be in 1..9:\n$baseRiskGrid"
        }
        require(scaleFactor > 0) { "Scale factor must be positive: $scaleFactor" }
    }

    /**
     * The number of grid rows in the full map.
     */
    val height: Int
        get() = baseRiskGrid.height * scaleFactor

    /**
     * The number of grid columns in the full map.
     */
    val width: Int
        get() = baseRiskGrid.width * scaleFactor

    /**
     * A graph with edges from each position in the full map to its adjacent positions, weighted by
     * the risk level at the new position.
     */
    private val riskGraph = object : WeightedGraph<Point>() {
        override fun getEdges(node: Point): List<Edge<Point>> =
            node.cardinalNeighbors()
                .filter { it in this@RiskLevelMap }
                .map { neighbor ->
                    val (rowIndex, colIndex) = neighbor.toMatrixCoordinates()
                    Edge(neighbor, riskLevel(rowIndex, colIndex).toLong())
                }
    }

    /**
     * Returns if [point] is within the bounds of the full map.
     */
    operator fun contains(point: Point): Boolean {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return rowIndex in 0 until height && colIndex in 0 until width
    }

    /**
     * Returns the risk level at the given [point] position in the full map.
     *
     * @throws IndexOutOfBoundsException If [point] is outside the map bounds.
     */
    operator fun get(point: Point): Int {
        if (point !in this) {
            throw IndexOutOfBoundsException("Point must fit in ${height}x$width grid: $point")
        }
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return riskLevel(rowIndex, colIndex)
    }

    /**
     * Returns the risk level at the given [rowIndex] and [colIndex] in the full map.
     *
     * @throws IndexOutOfBoundsException If [rowIndex] or [colIndex] is outside the map bounds.
     */
    operator fun get(rowIndex: Int, colIndex: Int): Int {
        if (rowIndex !in 0 until height) {
            throw IndexOutOfBoundsException("Row index must be in 0..${height - 1}: $rowIndex")
        }
        if (colIndex !in 0 until width) {
            throw IndexOutOfBoundsException("Column index must be in 0..${width - 1}: $colIndex")
        }
        return riskLevel(rowIndex, colIndex)
    }

    /**
     * Returns the minimum total risk required to move from the top-left corner to the bottom-right
     * corner of the full map.
     *
     * The total risk of a path is given by adding the risk level of each space that is *entered*
     * (not exited) while traveling along that path.
     */
    fun findMinimalPathRisk(): Long? = riskGraph.aStarDistance(
        source = Point.ORIGIN,
        heuristic = ::manhattanDistanceToGoal,
        isGoal = ::isGoal
    )

    /**
     * Returns the Manhattan distance from the given [point] to the bottom-right corner of the map.
     */
    private fun manhattanDistanceToGoal(point: Point): Long {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        val rowDistance = abs(height - 1 - rowIndex)
        val colDistance = abs(width - 1 - colIndex)
        return rowDistance.toLong() + colDistance.toLong()
    }

    /**
     * Returns if [point] corresponds to the bottom-right corner of the full map.
     */
    private fun isGoal(point: Point): Boolean {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return rowIndex == height - 1 && colIndex == width - 1
    }

    /**
     * Returns the risk level at the given [rowIndex] and [colIndex], which is assumed to be within
     * the bounds of the full map.
     */
    private fun riskLevel(rowIndex: Int, colIndex: Int): Int {
        val baseRisk = baseRiskGrid.shallowRows().getCyclic(rowIndex).getCyclic(colIndex)
        val riskIncrease = rowIndex / baseRiskGrid.height + colIndex / baseRiskGrid.width
        return ((baseRisk + riskIncrease - 1) % 9) + 1
    }

    companion object {
        /**
         * Returns a [RiskLevelMap] with the given [scaleFactor] and a [baseRiskGrid] parsed from
         * the lines of the given [file].
         *
         * Each line of [file] must be of equal length and consist of only nonzero decimal digits,
         * with each representing the risk level of a corresponding space in [baseRiskGrid].
         *
         * @throws IllegalArgumentException If [file] does not have the correct format, or if
         *  [scaleFactor] is negative or zero.
         */
        fun fromFile(file: File, scaleFactor: Int = 1): RiskLevelMap {
            val baseRiskRows = file.readLines().map { line ->
                line.toCharArray().map { it.digitToInt() }
            }
            return RiskLevelMap(baseRiskRows.toGrid(), scaleFactor)
        }
    }
}
