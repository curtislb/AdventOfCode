package com.curtislb.adventofcode.year2019.day03.wire

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.Segment

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
        for (segmentString in segmentStrings) {
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
     * Finds the intersection point of this wire and [other] that is closest to the origin `(0, 0)`.
     *
     * The origin is not considered an intersection point unless both wires return to that point.
     * Additionally, parallel wire segments are not considered intersecting, even if they overlap at
     * one or more points.
     *
     * @return A pair containing the nearest intersection point and its Manhattan distance from the
     *  origin. If this wire and [other] do not intersect, this function instead returns
     *  [NO_INTERSECT].
     */
    infix fun nearestIntersect(other: Wire): Pair<Point?, Int> {
        var bestIntersection: Point? = null
        var bestDistance = Int.MAX_VALUE
        segments.forEachIndexed { i, segment ->
            other.segments.forEachIndexed { j, otherSegment ->
                // Ignore the "intersection" of the first two segments at the origin
                if (i != 0 || j != 0) {
                    // Check for a new nearest intersection point
                    val intersection = segment intersect otherSegment
                    val distance = intersection?.manhattanDistance(Point.ORIGIN) ?: Int.MAX_VALUE
                    if (distance < bestDistance) {
                        bestIntersection = intersection
                        bestDistance = distance
                    }
                }
            }
        }
        return Pair(bestIntersection, bestDistance)
    }

    /**
     * Finds the intersection point of this wire and [other] with the shortest combined distance
     * along both wires from the origin `(0, 0)` to that point.
     *
     * The origin is not considered an intersection point unless both wires return to that point.
     * Additionally, parallel wire segments are not considered intersecting, even if they overlap at
     * one or more points.
     *
     * @return A pair containing the shortest-path intersection point and its total distance along
     *  both wires. If this wire and [other] do not intersect, this function instead returns
     *  [NO_INTERSECT].
     */
    infix fun shortestIntersect(other: Wire): Pair<Point?, Int> {
        var bestIntersection: Point? = null
        var bestLength = Int.MAX_VALUE
        var pathLength = 0
        var segmentStart = Point.ORIGIN
        segments.forEachIndexed { i, segment ->
            var otherPathLength = 0
            var otherSegmentStart = Point.ORIGIN
            other.segments.forEachIndexed { j, otherSegment ->
                // Ignore the "intersection" of the first two segments at the origin
                if (i != 0 || j != 0) {
                    val intersection = segment intersect otherSegment
                    if (intersection != null) {
                        // Calculate the total path length to this intersection point
                        val totalPathLength = pathLength + otherPathLength +
                            (segmentStart manhattanDistance intersection) +
                            (otherSegmentStart manhattanDistance intersection)

                        // Check if we've found a new shortest (nonzero length) path intersection
                        if (totalPathLength in 1 until bestLength) {
                            bestIntersection = intersection
                            bestLength = totalPathLength
                        }
                    }
                }
                otherPathLength += otherSegment.distance
                otherSegmentStart = otherSegment.otherEndpoint(otherSegmentStart)
            }
            pathLength += segment.distance
            segmentStart = segment.otherEndpoint(segmentStart)
        }
        return Pair(bestIntersection, bestLength)
    }

    companion object {
        /**
         * Placeholder value returned by [Wire.nearestIntersect] and [Wire.shortestIntersect] if the
         * wires do not intersect.
         */
        val NO_INTERSECT: Pair<Point?, Int> = Pair(null, Int.MAX_VALUE)
    }
}
