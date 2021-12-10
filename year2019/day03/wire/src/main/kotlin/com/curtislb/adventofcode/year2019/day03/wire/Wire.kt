package com.curtislb.adventofcode.year2019.day03.wire

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.Segment

/**
 * A wire consisting of a series of segments laid out in a 2D grid.
 *
 * @param wireString A string representation of the wire, where each comma-separated value
 *  represents the [Direction] and length of the next wire segment (e.g. `"U3"` for [Direction.UP],
 *  length 3), starting from the point `(0, 0)`.
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
            // Construct each segment and add it to the list in order
            val direction = Direction.fromChar(segmentString[0])
            val length = segmentString.substring(1).toInt()
            val segment = Segment.from(start, direction, length)
            segmentArrayList.add(segment)

            // The next segment starts from the opposite end of the current one
            start = segment.otherEndpoint(start)
        }
        segments = segmentArrayList
    }

    /**
     * Finds the intersection of this wire and [other] that is closest to the origin.
     *
     * The origin point `(0, 0)` is not considered an intersection unless both wires return to this
     * point. Parallel wire segments are not considered intersecting, even if they overlap at one or
     * more points.
     *
     * @return A pair containing the nearest intersection point and its Manhattan distance from the
     *  origin. If this wire and [other] do not intersect, the pair (`null`, [Int.MAX_VALUE]) is
     *  returned instead.
     */
    fun findNearestIntersection(other: Wire): Pair<Point?, Int> {
        var nearestIntersection: Point? = null
        var nearestDistance = Int.MAX_VALUE
        segments.forEachIndexed { i, segment ->
            other.segments.forEachIndexed { j, otherSegment ->
                // Ignore the "intersection" of the first two segments at the origin
                if (i != 0 || j != 0) {
                    // Check for a new nearest intersection point
                    val intersection = segment.intersectionWith(otherSegment)
                    val distance = intersection?.manhattanDistance(Point.ORIGIN) ?: Int.MAX_VALUE
                    if (distance < nearestDistance) {
                        nearestIntersection = intersection
                        nearestDistance = distance
                    }
                }
            }
        }
        return Pair(nearestIntersection, nearestDistance)
    }

    /**
     * Finds the intersection of this wire and [other] with the shortest total distance along wires.
     *
     * The total distance to an intersection point is the sum of the distances to that point (in
     * grid units) along each of the two wires.
     *
     * The origin point `(0, 0)` is not considered an intersection unless both wires return to this
     * point. Parallel wire segments are not considered intersecting, even if they overlap at one or
     * more points.
     *
     * @return A pair containing the shortest-path intersection point and its total distance along
     *  both wires. If this wire and [other] do not intersect, the pair (`null`, [Int.MAX_VALUE]) is
     *  returned instead.
     */
    fun findShortestPathIntersection(other: Wire): Pair<Point?, Int> {
        var shortestPathIntersection: Point? = null
        var shortestPathLength = Int.MAX_VALUE
        var thisPathLength = 0
        var thisSegmentStart = Point.ORIGIN
        segments.forEachIndexed { i, thisSegment ->
            var otherPathLength = 0
            var otherSegmentStart = Point.ORIGIN
            other.segments.forEachIndexed { j, otherSegment ->
                // Ignore the "intersection" of the first two segments at the origin
                if (i != 0 || j != 0) {
                    val intersection = thisSegment.intersectionWith(otherSegment)
                    if (intersection != null) {
                        // Calculate the total path length to this intersection point
                        val totalPathLength = thisPathLength + otherPathLength +
                            thisSegmentStart.manhattanDistance(intersection) +
                            otherSegmentStart.manhattanDistance(intersection)

                        // Check if we've found a new shortest (nonzero length) path intersection
                        if (totalPathLength in 1 until shortestPathLength) {
                            shortestPathIntersection = intersection
                            shortestPathLength = totalPathLength
                        }
                    }
                }
                otherPathLength += otherSegment.distance
                otherSegmentStart = otherSegment.otherEndpoint(otherSegmentStart)
            }
            thisPathLength += thisSegment.distance
            thisSegmentStart = thisSegment.otherEndpoint(thisSegmentStart)
        }
        return Pair(shortestPathIntersection, shortestPathLength)
    }
}
