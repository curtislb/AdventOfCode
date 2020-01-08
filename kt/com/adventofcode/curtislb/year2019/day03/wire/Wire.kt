package com.adventofcode.curtislb.year2019.day03.wire

import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.common.grid.Segment
import com.adventofcode.curtislb.common.grid.toDirection

/**
 * A wire consisting of a series of segments in a 2D grid.
 * @param wireString A string representation of the [Wire], where each comma-separated value represents the direction
 *  (`U` for up, `R` for right, etc.) and the integer length of the next wire segment.
 */
class Wire(wireString: String) {
    /**
     * A list containing all segments of the [Wire] in order.
     */
    val segments: List<Segment>

    init {
        val segmentStrings = wireString.split(',')
        val segmentArrayList = ArrayList<Segment>(segmentStrings.size)
        var start = Point(0, 0)
        segmentStrings.forEach { segmentString ->
            // Construct each segment and add it to the list in order.
            val direction = segmentString[0].toDirection()
            val length = segmentString.substring(1).toInt()
            val segment = Segment(start, direction, length)
            segmentArrayList.add(segment)

            // The start of the next segment is the end of the current one.
            start = segment.end
        }
        segments = segmentArrayList
    }

    /**
     * Looks for the intersection between this and another [Wire] that is closest to a given [Point].
     *
     * Note that any two parallel wire segments are not considered to be intersecting, even if they overlap at one or
     * more points.
     *
     * @param other The [Wire] to be checked for intersections with this one.
     * @param origin The [Point] from which the Manhattan distance of each intersection will be calculated in order to
     *  determine the nearest one. Any intersections that occur at this point are ignored.
     * @return A [Pair] containing the nearest intersection point and its Manhattan distance from [origin]. If this wire
     *  and [other] intersect at one or more points (other than [origin]), then the [Point] and distance returned will
     *  be that of the intersection with the smallest Manhattan distance to [origin]. If this wire and [other] do not
     *  intersect, the [Pair] (`null`, [Int.MAX_VALUE]) will be returned instead.
     */
    fun findNearestIntersectionWith(other: Wire, origin: Point = Point(0, 0)): Pair<Point?, Int> {
        var nearestIntersection: Point? = null
        var nearestDistance: Int = Int.MAX_VALUE
        segments.forEach { segment ->
            other.segments.forEach { otherSegment ->
                // Check for a new nearest intersection point (ignoring the origin).
                val intersection = segment.intersectionWith(otherSegment)
                val distance = if (intersection != null) origin.manhattanDistanceTo(intersection) else Int.MAX_VALUE
                if (distance in 1 until nearestDistance) {
                    nearestIntersection = intersection
                    nearestDistance = distance
                }
            }
        }
        return Pair(nearestIntersection, nearestDistance)
    }

    /**
     * Looks for the intersection between this and another [Wire] that is the shortest distance along both wires.
     *
     * The total distance to an intersection [Point] is given by the sum of the distances to that point (in grid units)
     * along each of the two wires.
     *
     * Note that any two parallel wire segments are not considered to be intersecting, even if they overlap at one or
     * more points.
     *
     * @param other The [Wire] to be checked for intersections with this one.
     * @return A [Pair] containing the shortest path intersection point and its total distance along both wires. If this
     *  wire and [other] intersect at one or more points, then the [Point] and distance returned will be that of the
     *  intersection with the shortest total distance, as defined above. If this wire and [other] do not intersect, the
     *  [Pair] (`null`, [Int.MAX_VALUE]) will be returned instead.
     */
    fun findShortestPathIntersectionWith(other: Wire): Pair<Point?, Int> {
        var shortestPathIntersection: Point? = null
        var shortestPathLength = Int.MAX_VALUE
        var thisPathLength = 0
        segments.forEach { thisSegment ->
            var otherPathLength = 0
            other.segments.forEach { otherSegment ->
                val intersection = thisSegment.intersectionWith(otherSegment)
                if (intersection != null) {
                    // The total path length is the length of all previous segments of both wires plus the distance
                    // along each current segment to the intersection point.
                    val totalPathLength = (thisPathLength + otherPathLength
                        + thisSegment.start.manhattanDistanceTo(intersection)
                        + otherSegment.start.manhattanDistanceTo(intersection))

                    // Check if we've found a new shortest path intersection.
                    if (totalPathLength < shortestPathLength) {
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
