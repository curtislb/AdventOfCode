package com.curtislb.adventofcode.year2023.day23.hike

import com.curtislb.adventofcode.common.collection.arrayQueueOf
import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.mutableGridOf
import java.io.File

/**
 * A 2D grid of tiles representing a collection of interconnected hiking trails that span the height
 * of the grid.
 *
 * @property grid A grid of [Tile]s that makes up the map of hiking trails. The grid must have at
 *  least one [Tile.PATH] tile in each of its first and last rows.
 *
 * @throws IllegalArgumentException If the [grid] does not have a [Tile.PATH] tile in each of its
 *  first and last rows.
 */
class HikingTrailMap(private val grid: Grid<Tile>) {
    /**
     * The position of the starting path tile in the first row of the grid.
     */
    private val startPosition: Point

    /**
     * The position of the goal path tile in the last row of the grid.
     */
    private val goalPosition: Point

    init {
        // Find the grid position of the starting path tile
        val startColumnIndex = grid.firstRow().indexOf(Tile.PATH)
        require(startColumnIndex != -1) { "Grid must have a path tile in the first row" }
        startPosition = Point.fromMatrixCoordinates(0, startColumnIndex)

        // Find the grid position of the goal path tile
        val goalColumnIndex = grid.lastRow().indexOf(Tile.PATH)
        require(goalColumnIndex != -1) { "Grid must have a path tile in the last row" }
        goalPosition = Point.fromMatrixCoordinates(grid.lastRowIndex, goalColumnIndex)
    }

    /**
     * Returns the length of the longest walking path from the starting tile (in the first grid row)
     * to the goal tile (in the last grid row), without stepping on the same tile more than once.
     *
     * If there is no walkable path from the starting tile to the goal tile, this function instead
     * returns -1.
     *
     * If [isIcy] is `true`, slope tiles (e.g. [Tile.SLOPE_UP]) are considered to be icy, meaning it
     * is only possible to step onto an adjacent tile in the "downhill" direction for that slope.
     */
    fun findLongestPathDistance(isIcy: Boolean = true): Int {
        // Find all relevant "waypoints" in the grid
        val waypoints = mutableSetOf(startPosition, goalPosition)
        grid.forEachPointValue { point, char ->
            if (char.isWalkable && isWaypoint(point)) {
                waypoints.add(point)
            }
        }

        // Use BFS to construct a "weighted graph" of edges between waypoints
        val waypointEdgesMap = mutableMapOf<Point, MutableMap<Point, Int>>()
        for (source in waypoints) {
            val pointQueue = arrayQueueOf(source)
            val distanceMap = mutableMapOf(source to 0)
            while (pointQueue.isNotEmpty()) {
                val point = pointQueue.poll()
                val distance = distanceMap[point]!!

                // Stop searching along path when a waypoint is found
                if (point in waypoints && point != source) {
                    waypointEdgesMap.getOrPut(source) { mutableMapOf() }[point] = distance
                    continue
                }

                // Enqueue adjacent tiles that are walkable and unvisited
                for (neighbor in getWalkableNeighbors(point, isIcy)) {
                    if (neighbor !in distanceMap) {
                        pointQueue.offer(neighbor)
                        distanceMap[neighbor] = distance + 1
                    }
                }
            }
        }

        return maxDistanceToGoal(startPosition, waypointEdgesMap, visitedSet = mutableSetOf())
    }

    /**
     * Returns the positions of all tiles onto which it's possible to step from the tile at the
     * specified grid [position].
     *
     * If [isIcy] is `true`, slope tiles (e.g. [Tile.SLOPE_UP]) are considered to be icy, meaning it
     * is only possible to step onto an adjacent tile in the "downhill" direction for that slope.
     */
    private fun getWalkableNeighbors(position: Point, isIcy: Boolean): List<Point> {
        return when (val tile = grid[position]) {
            // Can step from a normal path tile onto any adjacent tile
            Tile.PATH -> position.cardinalNeighbors().filter(::isWalkable)

            // Shouldn't be possible to step onto a forest tile
            Tile.FOREST -> emptyList()

            // Can only step in the "downhill" direction from an icy slope
            Tile.SLOPE_UP, Tile.SLOPE_RIGHT, Tile.SLOPE_DOWN, Tile.SLOPE_LEFT -> {
                if (isIcy) {
                    val direction = Direction.fromChar(tile.symbol)
                    val neighbor = position.move(direction)
                    if (isWalkable(neighbor)) listOf(neighbor) else emptyList()
                } else {
                    position.cardinalNeighbors().filter(::isWalkable)
                }
            }
        }
    }

    /**
     * Returns `true` if it is possible to step onto the tile at the specified grid [position].
     */
    private fun isWalkable(position: Point) = grid.getOrNull(position)?.isWalkable == true

    /**
     * Returns `true` if the tile at the specified grid [position] satisfies *any* of the following
     * criteria:
     *
     * - It is the starting path tile.
     * - It is the goal path tile.
     * - It is adjacent to 3+ walkable tiles.
     */
    private fun isWaypoint(position: Point): Boolean {
        return position == startPosition ||
            position == goalPosition ||
            position.cardinalNeighbors().count(::isWalkable) > 2
    }

    /**
     * Returns the maximum distance from [source] to the goal position by following weighted edges
     * in the specified [edgesMap] and without visiting any position more than once, including
     * any previously visited positions in the given [visitedSet].
     *
     * If it is no path from [source] to the goal that satisfies the above conditions, this function
     * instead returns -1.
     */
    private fun maxDistanceToGoal(
        source: Point,
        edgesMap: Map<Point, Map<Point, Int>>,
        visitedSet: MutableSet<Point>
    ): Int {
        // Distance from the goal to itself is 0
        if (source == goalPosition) {
            return 0
        }

        // Set default distance for if no path is found
        var maxDistance = -1

        // Mark source as visited
        visitedSet.add(source)

        // Check recursively for the maximum distance from all neighbors
        for ((neighbor, distance) in edgesMap[source]!!) {
            // Don't check a previously visited position
            if (neighbor in visitedSet) {
                continue
            }

            // Check distance from neighbor and save it if needed
            val neighborDistance = maxDistanceToGoal(neighbor, edgesMap, visitedSet)
            if (neighborDistance >= 0) {
                maxDistance = maxOf(maxDistance, distance + neighborDistance)
            }
        }

        // Unmark source as visited (backtrack)
        visitedSet.remove(source)

        return maxDistance
    }

    companion object {
        /**
         * Returns an instance of [HikingTrailMap] with the tile grid read from the given [file].
         *
         * The [file] must contain lines of equal length, where each character represents the [Tile]
         * at the corresponding grid position.
         *
         * @throws IllegalArgumentException If [file] is formatted incorrectly.
         */
        fun fromFile(file: File): HikingTrailMap {
            val grid = mutableGridOf<Tile>()
            file.forEachLine { line ->
                val row = line.map { Tile.fromChar(it) }
                grid.addShallowRow(row)
            }
            return HikingTrailMap(grid)
        }
    }
}
