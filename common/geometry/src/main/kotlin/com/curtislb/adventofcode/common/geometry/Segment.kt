package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.range.overlap
import com.curtislb.adventofcode.common.range.size
import java.util.Objects
import kotlin.math.abs

/**
 * A unique representation of a horizontal, vertical, or diagonal segment in a 2D grid.
 *
 * @property start One endpoint of the segment. See [end] for more information on how these points
 *  are defined relative to each other.
 * @property slope The slope of the segment.
 * @property distance The distance in grid spaces between the endpoints of the segment. Note that
 *  this is *not* the same as the Euclidean distance, as diagonally adjacent points are considered
 *  to have a [distance] of 1.
 *
 * @constructor Creates a new instance of [Segment] with the given [start] point, [slope], and
 *  [distance] (in grid spaces) between endpoints.
 */
class Segment private constructor(val start: Point, private val slope: Slope, val distance: Int) {
    /**
     * The opposite endpoint of the segment from [start].
     *
     * For any [Segment], the x-coordinate of [end] is greater than or equal to that of [start].
     * Similarly, if the segment is vertical, the y-coordinate of [end] is greater than or equal to
     * that of [start].
     */
    val end: Point = start.move(slope.direction, distance)

    /**
     * The x- and y-coordinate ranges of all points that lie on the segment.
     */
    val coordRanges: CoordinateRanges = CoordinateRanges(
        x = start.x..end.x,
        y = if (start.y <= end.y) start.y..end.y else end.y..start.y
    )

    /**
     * Whether both endpoints of the segment are the same point.
     */
    val isPoint: Boolean
        get() = distance == 0

    /**
     * Whether the segment is horizontal.
     *
     * A segment consisting of a single point is not considered horizontal (see [isPoint]).
     */
    val isHorizontal: Boolean
        get() = !isPoint && slope == Slope.HORIZONTAL

    /**
     * Whether the segment is vertical.
     *
     * A segment consisting of a single point is not considered vertical (see [isPoint]).
     */
    val isVertical: Boolean
        get() = !isPoint && slope == Slope.VERTICAL

    /**
     * Whether the segment is up-right or down-right diagonal.
     *
     * A segment consisting of a single point is not considered diagonal (see [isPoint]).
     */
    val isDiagonal: Boolean
        get() = !isPoint && slope == Slope.UP_RIGHT || slope == Slope.DOWN_RIGHT

    /**
     * Returns the other endpoint of the segment when given one as a [point].
     *
     * @throws IllegalArgumentException If [point] is not an endpoint of the segment.
     */
    fun otherEndpoint(point: Point): Point = when (point) {
        start -> end
        end -> start
        else -> throw IllegalArgumentException("Point must be $start or $end: $point")
    }

    /**
     * Returns a sequence of all grid points that lie on the segment, including both endpoints.
     *
     * If [start] and [end] are the same point, this function will return a sequence containing only
     * that point.
     */
    fun points(): Sequence<Point> = when (slope) {
        Slope.HORIZONTAL -> sequence {
            for (x in coordRanges.x) {
                yield(start.copy(x = x))
            }
        }

        Slope.VERTICAL -> sequence {
            for (y in coordRanges.y) {
                yield(start.copy(y = y))
            }
        }

        Slope.UP_RIGHT, Slope.DOWN_RIGHT -> sequence {
            for (offset in 0 until coordRanges.x.size()) {
                yield(start.move(slope.direction, offset))
            }
        }
    }

    /**
     * Returns `true` if this segment and [other] are parallel.
     *
     * A segment consisting of a single point is not considered parallel to any other segment.
     */
    infix fun parallelTo(other: Segment): Boolean =
        !isPoint && !other.isPoint && slope == other.slope

    /**
     * Returns `true` if this segment and [other] are perpendicular.
     *
     * A segment consisting of a single point is not considered perpendicular to any other segment.
     */
    infix fun perpendicularTo(other: Segment): Boolean {
        if (isPoint || other.isPoint) {
            // Single point is not perpendicular to any segment
            return false
        }

        return when (slope) {
            Slope.HORIZONTAL -> other.slope == Slope.VERTICAL
            Slope.VERTICAL -> other.slope == Slope.HORIZONTAL
            Slope.UP_RIGHT -> other.slope == Slope.DOWN_RIGHT
            Slope.DOWN_RIGHT -> other.slope == Slope.UP_RIGHT
        }
    }

    /**
     * Returns the single grid point of intersection between this segment and [other].
     *
     * If this segment and [other] don't intersect, intersect at a non-grid point, or are parallel
     * (even if they [overlap] at one or more points), this function instead returns `null`.
     */
    infix fun intersect(other: Segment): Point? =
        if (isPoint || other.isPoint) {
            // Overlap with point segment (if any) is a single point
            overlapWithSameSlopeSegment(other)?.start
        } else {
            intersectWithNonPointSegment(other)
        }

    /**
     * Returns a segment containing all grid points that lie on both this segment and [other].
     *
     * If this segment and [other] don't overlap, this function instead returns `null`.
     */
    infix fun overlap(other: Segment): Segment? =
        if (slope == other.slope) {
            overlapWithSameSlopeSegment(other)
        } else {
            // Non-parallel segments have at most one overlap point
            intersectWithNonPointSegment(other)?.let { ofPoint(it) }
        }

    /**
     * Returns the overlap between this segment and [other], assuming both segments have the same
     * [slope].
     */
    private fun overlapWithSameSlopeSegment(other: Segment): Segment? = when (slope) {
        Slope.HORIZONTAL -> horizontalSegmentOverlap(this, other)
        Slope.VERTICAL -> verticalSegmentOverlap(this, other)
        Slope.UP_RIGHT -> upRightSegmentOverlap(this, other)
        Slope.DOWN_RIGHT -> downRightSegmentOverlap(this, other)
    }

    /**
     * Returns the single grid point of intersection between this segment and [other], assuming
     * neither segment consists of a single point.
     *
     * If this segment and [other] don't intersect, intersect at a non-grid point, or are parallel,
     * this function instead returns `null`.
     */
    private fun intersectWithNonPointSegment(other: Segment): Point? = when (slope) {
        Slope.HORIZONTAL -> when (other.slope) {
            Slope.HORIZONTAL -> null
            Slope.VERTICAL -> horizontalVerticalIntersection(this, other)
            Slope.UP_RIGHT -> horizontalUpRightIntersection(this, other)
            Slope.DOWN_RIGHT -> horizontalDownRightIntersection(this, other)
        }

        Slope.VERTICAL -> when (other.slope) {
            Slope.HORIZONTAL -> horizontalVerticalIntersection(other, this)
            Slope.VERTICAL -> null
            Slope.UP_RIGHT -> verticalUpRightIntersection(this, other)
            Slope.DOWN_RIGHT -> verticalDownRightIntersection(this, other)
        }

        Slope.UP_RIGHT -> when (other.slope) {
            Slope.HORIZONTAL -> horizontalUpRightIntersection(other, this)
            Slope.VERTICAL -> verticalUpRightIntersection(other, this)
            Slope.UP_RIGHT -> null
            Slope.DOWN_RIGHT -> oppositeDiagonalIntersection(this, other)
        }

        Slope.DOWN_RIGHT -> when (other.slope) {
            Slope.HORIZONTAL -> horizontalDownRightIntersection(other, this)
            Slope.VERTICAL -> verticalDownRightIntersection(other, this)
            Slope.UP_RIGHT -> oppositeDiagonalIntersection(other, this)
            Slope.DOWN_RIGHT -> null
        }
    }

    override fun toString(): String = "$start -> $end"

    override fun equals(other: Any?): Boolean =
        other is Segment &&
        start == other.start &&
        slope == other.slope &&
        distance == other.distance

    override fun hashCode(): Int = Objects.hash(start, slope, distance)

    companion object {
        /**
         * Returns a segment with [pointA] and [pointB] as endpoints.
         *
         * @throws IllegalArgumentException If the segment is not horizontal, vertical, or diagonal.
         */
        fun between(pointA: Point, pointB: Point): Segment = when {
            // Segment is a single point
            pointA == pointB -> ofPoint(pointA)

            // Segment is vertical
            pointA.x == pointB.x -> {
                // Start is point with smaller y-coordinate
                val start: Point
                val end: Point
                if (pointA.y <= pointB.y) {
                    start = pointA
                    end = pointB
                } else {
                    start = pointB
                    end = pointA
                }
                Segment(start, Slope.VERTICAL, end.y - start.y)
            }

            // Segment is horizontal
            pointA.y == pointB.y -> {
                // Start is point with smaller x-coordinate
                val start: Point
                val end: Point
                if (pointA.x <= pointB.x) {
                    start = pointA
                    end = pointB
                } else {
                    start = pointB
                    end = pointA
                }
                Segment(start, Slope.HORIZONTAL, end.x - start.x)
            }

            // Segment is diagonal (or invalid)
            else -> {
                // Start is point with smaller x-coordinate
                val start: Point
                val end: Point
                if (pointA.x <= pointB.x) {
                    start = pointA
                    end = pointB
                } else {
                    start = pointB
                    end = pointA
                }

                // Check that the segment has a slope of +1 or -1
                val deltaX = end.x - start.x
                val deltaY = end.y - start.y
                if (abs(deltaX) == abs(deltaY)) {
                    val slope = if (start.y <= end.y) Slope.UP_RIGHT else Slope.DOWN_RIGHT
                    Segment(start, slope, deltaX)
                } else {
                    throw IllegalArgumentException(
                        "Segment must be horizontal, vertical, or diagonal: $pointA -> $pointB"
                    )
                }
            }
        }

        /**
         * Returns a segment that starts (or ends) at the given [point] and extends a number of grid
         * spaces equal to [distance] in the given [direction].
         */
        fun from(point: Point, direction: Direction, distance: Int): Segment {
            if (distance < 0) {
                // Reverse direction if distance is negative
                return from(point, direction.reverse(), -distance)
            }

            if (distance == 0) {
                // Direction doesn't matter for single-point segments
                return ofPoint(point)
            }

            return when (direction) {
                // Use the given point as start
                Direction.UP, Direction.UP_RIGHT, Direction.RIGHT, Direction.DOWN_RIGHT ->
                    Segment(
                        start = point,
                        slope = Slope.fromDirection(direction),
                        distance = distance
                    )

                // Use the given point as end
                Direction.DOWN, Direction.DOWN_LEFT, Direction.LEFT, Direction.UP_LEFT ->
                    Segment(
                        start = point.move(direction, distance),
                        slope = Slope.fromDirection(direction.reverse()),
                        distance = distance
                    )
            }
        }

        /**
         * Returns a segment that consists of a single [point].
         */
        fun ofPoint(point: Point): Segment =
            Segment(start = point, slope = Slope.HORIZONTAL, distance = 0)
    }
}

/**
 * A valid type of linear slope for a [Segment].
 *
 * @property direction The direction from [Segment.start] to [Segment.end] represented by the slope.
 */
internal enum class Slope(val direction: Direction) {
    /**
     * The slope of a segment that lies on a horizontal line.
     */
    HORIZONTAL(Direction.RIGHT),

    /**
     * The slope of a segment that lies on a vertical line.
     */
    VERTICAL(Direction.UP),

    /**
     * The slope of a segment that lies on a line rotated 45 degrees counterclockwise from
     * positive x.
     */
    UP_RIGHT(Direction.UP_RIGHT),

    /**
     * The slope of a segment that lies on a line rotated 45 degrees clockwise from positive x.
     */
    DOWN_RIGHT(Direction.DOWN_RIGHT);

    companion object {
        /**
         * Returns the slope corresponding to the given [direction].
         *
         * @throws IllegalArgumentException If [direction] has no corresponding slope.
         */
        fun fromDirection(direction: Direction): Slope = when (direction) {
            Direction.UP -> VERTICAL
            Direction.UP_RIGHT -> UP_RIGHT
            Direction.RIGHT -> HORIZONTAL
            Direction.DOWN_RIGHT -> DOWN_RIGHT
            else -> throw IllegalArgumentException("No slope for direction: $direction")
        }
    }
}

/**
 * Returns the overlap between horizontal segments [segmentA] and [segmentB].
 */
private fun horizontalSegmentOverlap(segmentA: Segment, segmentB: Segment): Segment? {
    // Segments must have the same y-coordinate to overlap
    if (segmentA.start.y != segmentB.start.y) {
        return null
    }

    // Check if the segments' x-coordinates overlap
    val xOverlap = segmentA.coordRanges.x overlap segmentB.coordRanges.x
    if (xOverlap.isEmpty()) {
        return null
    }

    val overlapStart = segmentA.start.copy(x = xOverlap.first)
    val overlapEnd = segmentA.start.copy(x = xOverlap.last)
    return Segment.between(overlapStart, overlapEnd)
}

/**
 * Returns the overlap between vertical segments [segmentA] and [segmentB].
 */
private fun verticalSegmentOverlap(segmentA: Segment, segmentB: Segment): Segment? {
    // Segments must have the same x-coordinate to overlap
    if (segmentA.start.x != segmentB.start.x) {
        return null
    }

    // Check if the segments' y-coordinates overlap
    val yOverlap = segmentA.coordRanges.y overlap segmentB.coordRanges.y
    if (yOverlap.isEmpty()) {
        return null
    }

    val overlapStart = segmentA.start.copy(y = yOverlap.first)
    val overlapEnd = segmentA.start.copy(y = yOverlap.last)
    return Segment.between(overlapStart, overlapEnd)
}

/**
 * Returns the overlap between up-right diagonal segments [segmentA] and [segmentB].
 */
private fun upRightSegmentOverlap(segmentA: Segment, segmentB: Segment): Segment? {
    // Check whether both segments would have the same y-intercept if extended
    val interceptA = segmentA.start.y - segmentA.start.x
    val interceptB = segmentB.start.y - segmentB.start.x
    if (interceptA != interceptB) {
        return null
    }

    // Check if the segments' x-coordinates overlap
    val xOverlap = segmentA.coordRanges.x overlap segmentB.coordRanges.x
    if (xOverlap.isEmpty()) {
        return null
    }

    // The segments' y-coordinates must also overlap
    val yOverlap = segmentA.coordRanges.y overlap segmentB.coordRanges.y
    val overlapStart = Point(xOverlap.first, yOverlap.first)
    val overlapEnd = Point(xOverlap.last, yOverlap.last)
    return Segment.between(overlapStart, overlapEnd)
}

/**
 * Returns the overlap between down-right diagonal segments [segmentA] and [segmentB].
 */
private fun downRightSegmentOverlap(segmentA: Segment, segmentB: Segment): Segment? {
    // Check whether both segments would have the same y-intercept if extended
    val interceptA = segmentA.start.y + segmentA.start.x
    val interceptB = segmentB.start.y + segmentB.start.x
    if (interceptA != interceptB) {
        return null
    }

    // Check if the segments' x-coordinates overlap
    val xOverlap = segmentA.coordRanges.x overlap segmentB.coordRanges.x
    if (xOverlap.isEmpty()) {
        return null
    }

    // The segments' y-coordinates must also overlap
    val yOverlap = segmentA.coordRanges.y overlap segmentB.coordRanges.y
    val overlapStart = Point(xOverlap.first, yOverlap.last)
    val overlapEnd = Point(xOverlap.last, yOverlap.first)
    return Segment.between(overlapStart, overlapEnd)
}

/**
 * Returns the intersection point of two [horizontal] and [vertical] segments.
 */
private fun horizontalVerticalIntersection(horizontal: Segment, vertical: Segment): Point? =
    if (
        horizontal.start.y in vertical.coordRanges.y &&
        vertical.start.x in horizontal.coordRanges.x
    ) {
        Point(vertical.start.x, horizontal.start.y)
    } else {
        null
    }

/**
 * Returns the intersection point of two [horizontal] and [upRight] diagonal segments.
 */
private fun horizontalUpRightIntersection(horizontal: Segment, upRight: Segment): Point? {
    // Find the x-coordinate of the diagonal segment if extended to the horizontal one
    val x = upRight.start.x + (horizontal.start.y - upRight.start.y)
    return if (x in horizontal.coordRanges.x && x in upRight.coordRanges.x) {
        Point(x, horizontal.start.y)
    } else {
        null
    }
}

/**
 * Returns the intersection point of two [horizontal] and [downRight] diagonal segments.
 */
private fun horizontalDownRightIntersection(horizontal: Segment, downRight: Segment): Point? {
    // Find the x-coordinate of the diagonal segment if extended to the horizontal one
    val x = downRight.start.x + (downRight.start.y - horizontal.start.y)
    return if (x in horizontal.coordRanges.x && x in downRight.coordRanges.x) {
        Point(x, horizontal.start.y)
    } else {
        null
    }
}

/**
 * Returns the intersection point of two [vertical] and [upRight] diagonal segments.
 */
private fun verticalUpRightIntersection(vertical: Segment, upRight: Segment): Point? {
    // Find the y-coordinate of the diagonal segment if extended to the vertical one
    val y = upRight.start.y + (vertical.start.x - upRight.start.x)
    return if (y in vertical.coordRanges.y && y in upRight.coordRanges.y) {
        Point(vertical.start.x, y)
    } else {
        null
    }
}

/**
 * Returns the intersection point of two [vertical] and [downRight] diagonal segments.
 */
private fun verticalDownRightIntersection(vertical: Segment, downRight: Segment): Point? {
    // Find the y-coordinate of the diagonal segment if extended to the vertical one
    val y = downRight.start.y - (vertical.start.x - downRight.start.x)
    return if (y in vertical.coordRanges.y && y in downRight.coordRanges.y) {
        Point(vertical.start.x, y)
    } else {
        null
    }
}

/**
 * Returns the intersection point of two [upRight] and [downRight] diagonal segments.
 */
private fun oppositeDiagonalIntersection(upRight: Segment, downRight: Segment): Point? {
    // Calculate 2x, assuming an intersection exists at point (x, y)
    val twoX = upRight.start.x - upRight.start.y + downRight.start.x + downRight.start.y
    if (twoX % 2 != 0) {
        // Intersection can't exist at non-integer coordinates
        return null
    }

    // Check if the intersection point is valid for both segments
    val x = twoX / 2
    return if (x in upRight.coordRanges.x && x in downRight.coordRanges.x) {
        val y = upRight.start.y + (x - upRight.start.x)
        Point(x, y)
    } else {
        null
    }
}
