package com.curtislb.adventofcode.year2019.day03.wire

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.Segment

/**
 * A wire consisting of a series of segments laid out in a 2D grid.
 *
 * @param wireString A string representation of the wire, where each comma-separated value represents the [Direction]
 *  and integer length of the next wire segment (e.g. `"U3"` for [Direction.UP], length 3).
 */
class Wire(wireString: String) {
    /**
     * A list containing all segments of the wire in order.
     */
    private val segments: List<Segment>

    init {
        val segmentStrings = wireString.split(',')
        val segmentArrayList = ArrayList<Segment>(segmentStrings.size)
        var start = Point.ORIGIN
        segmentStrings.forEach { segmentString ->
            // Construct each segment and add it to the list in order.
            val direction = Direction.from(segmentString[0])
            val length = segmentString.substring(1).toInt()
            val segment = Segment(start, direction, length)
            segmentArrayList.add(segment)

            // The start of the next segment is the end of the current one.
            start = segment.end
        }
        segments = segmentArrayList
    }

    /**
     * Finds the intersection between this wire and [other] that is closest to the given [origin].
     *
     * Any intersections that occur at the point [origin] are ignored. Parallel wire segments are not considered to
     * intersect, even if they overlap at one or more points.
     *
     * @return A pair containing the nearest intersection point and its Manhattan distance from [origin]. If this wire
     *  and [other] do not intersect, the pair (`null`, [Int.MAX_VALUE]) is returned instead.
     */
    fun findNearestIntersectionWith(other: Wire, origin: Point = Point.ORIGIN): Pair<Point?, Int> {
        var nearestIntersection: Point? = null
        var nearestDistance = Int.MAX_VALUE
        segments.forEach { segment ->
            other.segments.forEach { otherSegment ->
                // Check for a new nearest intersection point (ignoring the origin).
                val intersection = segment.intersection(otherSegment)
                val distance = if (intersection != null) origin.manhattanDistance(intersection) else Int.MAX_VALUE
                if (distance in 1 until nearestDistance) {
                    nearestIntersection = intersection
                    nearestDistance = distance
                }
            }
        }
        return Pair(nearestIntersection, nearestDistance)
    }

    /**
     * Finds the intersection between this wire and [other] that is the shortest distance along both wires.
     *
     * The total distance to an intersection point is the sum of the distances to that point (in grid units) along each
     * of the two wires. If both wires originate from the same point, this intersection is ignored. Parallel wire
     * segments are not considered to intersect, even if they overlap at one or more points.
     *
     * @return A pair containing the shortest-path intersection point and its total distance along both wires. If this
     *  wire and [other] do not intersect, the pair (`null`, [Int.MAX_VALUE]) is returned instead.
     */
    fun findShortestPathIntersectionWith(other: Wire): Pair<Point?, Int> {
        var shortestPathIntersection: Point? = null
        var shortestPathLength = Int.MAX_VALUE
        var thisPathLength = 0
        segments.forEach { thisSegment ->
            var otherPathLength = 0
            other.segments.forEach { otherSegment ->
                val intersection = thisSegment.intersection(otherSegment)
                if (intersection != null) {
                    // Calculate the total path length to this intersection point.
                    val totalPathLength = (thisPathLength + otherPathLength
                        + thisSegment.start.manhattanDistance(intersection)
                        + otherSegment.start.manhattanDistance(intersection))

                    // Check if we've found a new shortest (nonzero length) path intersection.
                    if (totalPathLength in 1 until shortestPathLength) {
                        shortestPathIntersection = intersection
                        shortestPathLength = totalPathLength
                    }
                }
                otherPathLength += otherSegment.length
            }
            thisPathLength += thisSegment.length
        }
        return Pair(shortestPathIntersection, shortestPathLength)
    }
}
