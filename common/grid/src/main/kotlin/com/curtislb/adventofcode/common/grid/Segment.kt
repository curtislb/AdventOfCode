package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.range.overlapWith
import com.curtislb.adventofcode.common.range.sizeInclusive
import java.util.Objects
import kotlin.math.abs

/**
 * A unique representation of a horizontal, vertical, or diagonal segment in a 2D grid.
 *
 * @param start One endpoint of this segment. See [end] for more detail on how these points are
 *  defined relative to one another.
 * @param slope The slope of this segment.
 * @param distance The distance in grid units between the endpoints of this segment. Note that this
 *  is *not* equal to the Euclidean distance between the points, as diagonally adjacent points are
 *  considered to have a distance of 1.
 */
class Segment private constructor(
    val start: Point,
    private val slope: Slope,
    val distance: Int
) {
    /**
     * The opposite endpoint of this segment from [start].
     *
     * For any [Segment], the x-coordinate of [end] is always at least that of [start]. Similarly,
     * the y-coordinate of [end] is at least that of [start] if this segment is vertical.
     */
    val end: Point = start.move(slope.direction, distance)

    /**
     * The range of x-coordinate values between [start] and [end] (both inclusive).
     */
    val xRange: IntRange = start.x..end.x

    /**
     * The range of y-coordinate values between [start] and [end] (both inclusive).
     */
    val yRange: IntRange = if (start.y <= end.y) start.y..end.y else end.y..start.y

    /**
     * Whether both endpoints of this segment are the same point.
     */
    val isPoint: Boolean get() = distance == 0

    /**
     * Whether this segment is horizontal.
     *
     * A segment consisting of a single point is not considered horizontal.
     */
    val isHorizontal: Boolean get() = !isPoint && slope == Slope.HORIZONTAL

    /**
     * Whether this segment if vertical.
     *
     * A segment consisting of a single point is not considered vertical.
     */
    val isVertical: Boolean get() = !isPoint && slope == Slope.VERTICAL

    /**
     * Whether this segment is up-right or down-right diagonal.
     *
     * A segment consisting of a single point is not considered diagonal.
     */
    val isDiagonal: Boolean get() = !isPoint && slope == Slope.UP_RIGHT || slope == Slope.DOWN_RIGHT

    /**
     * Checks if this segment and [other] are parallel.
     *
     * A segment consisting of a single point is not considered parallel to any other segment.
     */
    fun isParallel(other: Segment): Boolean = !isPoint && !other.isPoint && slope == other.slope

    /**
     * Checks if this segment and [other] are perpendicular.
     *
     * A segment consisting of a single point is not considered perpendicular to any other segment.
     */
    fun isPerpendicular(other: Segment): Boolean =
        !isPoint && !other.isPoint && slope == when (other.slope) {
            Slope.HORIZONTAL -> Slope.VERTICAL
            Slope.VERTICAL -> Slope.HORIZONTAL
            Slope.UP_RIGHT -> Slope.DOWN_RIGHT
            Slope.DOWN_RIGHT -> Slope.UP_RIGHT
        }

    /**
     * Returns the other endpoint of this segment when given one as a [point].
     *
     * @throws IllegalArgumentException If [point] is not an endpoint of this segment.
     */
    fun otherEndpoint(point: Point): Point = when (point) {
        start -> end
        end -> start
        else -> throw IllegalArgumentException("Point must be $start or $end: $point")
    }

    /**
     * Returns a sequence of all grid points that lie on this segment, including both endpoints.
     *
     * If [start] and [end] are the same point, the returned sequence will contain only that point.
     */
    fun points(): Sequence<Point> = when (slope) {
        Slope.HORIZONTAL -> sequence {
            for (x in xRange) {
                yield(start.copy(x = x))
            }
        }

        Slope.VERTICAL -> sequence {
            for (y in yRange) {
                yield(start.copy(y = y))
            }
        }

        Slope.UP_RIGHT, Slope.DOWN_RIGHT -> sequence {
            for (distance in 0 until xRange.sizeInclusive) {
                yield(start.move(slope.direction, distance))
            }
        }
    }

    /**
     * Checks if the given [point] lies on this segment.
     */
    operator fun contains(point: Point): Boolean = overlapWith(ofPoint(point)) != null

    /**
     * Checks if all points that lie on [other] also lie on this segment.
     */
    operator fun contains(other: Segment): Boolean = overlapWith(other) == other

    /**
     * Returns a segment containing all grid points that lie on both this segment and [other].
     *
     * If this segment and [other] don't overlap, this function instead returns `null`.
     */
    fun overlapWith(other: Segment): Segment? =
        if (isParallel(other)) {
            overlapWithParallelSegment(other)
        } else {
            intersectionWithNonParallelSegment(other)?.let { ofPoint(it) }
        }

    /**
     * Returns the single point of intersection between this segment and [other].
     *
     * If this segment and [other] don't intersect or are parallel (even if they overlap at one or
     * more points), this function instead returns `null`.
     */
    fun intersectionWith(other: Segment): Point? =
        if (isParallel(other)) null else intersectionWithNonParallelSegment(other)

    /**
     * Returns the overlap between this segment and [other], assuming the two segments are parallel.
     */
    private fun overlapWithParallelSegment(other: Segment): Segment? = when (slope) {
        Slope.HORIZONTAL -> horizontalSegmentOverlap(this, other)
        Slope.VERTICAL -> verticalSegmentOverlap(this, other)
        Slope.UP_RIGHT -> upRightSegmentOverlap(this, other)
        Slope.DOWN_RIGHT -> downRightSegmentOverlap(this, other)
    }

    /**
     * Returns the intersection point of this segment and [other], assuming the two segments are
     * *not* parallel.
     */
    private fun intersectionWithNonParallelSegment(other: Segment): Point? = when (slope) {
        Slope.HORIZONTAL -> when (other.slope) {
            Slope.HORIZONTAL -> overlapWithParallelSegment(other)?.start
            Slope.VERTICAL -> horizontalVerticalIntersection(this, other)
            Slope.UP_RIGHT -> horizontalUpRightIntersection(this, other)
            Slope.DOWN_RIGHT -> horizontalDownRightIntersection(this, other)
        }

        Slope.VERTICAL -> when (other.slope) {
            Slope.HORIZONTAL -> horizontalVerticalIntersection(other, this)
            Slope.VERTICAL -> overlapWithParallelSegment(other)?.start
            Slope.UP_RIGHT -> verticalUpRightIntersection(this, other)
            Slope.DOWN_RIGHT -> verticalDownRightIntersection(this, other)
        }

        Slope.UP_RIGHT -> when (other.slope) {
            Slope.HORIZONTAL -> horizontalUpRightIntersection(other, this)
            Slope.VERTICAL -> verticalUpRightIntersection(other, this)
            Slope.UP_RIGHT -> overlapWithParallelSegment(other)?.start
            Slope.DOWN_RIGHT -> oppositeDiagonalIntersection(this, other)
        }

        Slope.DOWN_RIGHT -> when (other.slope) {
            Slope.HORIZONTAL -> horizontalDownRightIntersection(other, this)
            Slope.VERTICAL -> verticalDownRightIntersection(other, this)
            Slope.UP_RIGHT -> oppositeDiagonalIntersection(other, this)
            Slope.DOWN_RIGHT -> overlapWithParallelSegment(other)?.start
        }
    }

    override fun equals(other: Any?): Boolean = other is Segment &&
        start == other.start &&
        slope == other.slope &&
        distance == other.distance

    override fun hashCode(): Int = Objects.hash(start, slope, distance)

    override fun toString(): String = "$start -> $end"

    companion object {
        /**
         * Returns a segment with [pointA] and [pointB] as endpoints.
         */
        fun between(pointA: Point, pointB: Point): Segment =
            if (pointA == pointB) {
                // Segment is a single point
                ofPoint(pointA)
            } else if (pointA.x == pointB.x) {
                // Segment is vertical
                val (start, end) = listOf(pointA, pointB).sortedBy { it.y }
                Segment(start, Slope.VERTICAL, end.y - start.y)
            } else if (pointA.y == pointB.y) {
                // Segment is horizontal
                val (start, end) = listOf(pointA, pointB).sortedBy { it.x }
                Segment(start, Slope.HORIZONTAL, end.x - start.x)
            } else {
                // Segment is diagonal
                val (start, end) = listOf(pointA, pointB).sortedBy { it.x }

                // Check that the segment has a slope of +1 or -1
                val delta = end - start
                if (abs(delta.x) == abs(delta.y)) {
                    val slope = if (start.y <= end.y) Slope.UP_RIGHT else Slope.DOWN_RIGHT
                    Segment(start, slope, delta.x)
                } else {
                    throw IllegalArgumentException(
                        "Segment must be horizontal, vertical, or diagonal: $pointA -> $pointB"
                    )
                }
            }

        /**
         * Returns a segment that extends [distance] units in [direction] from the given [point].
         */
        fun from(point: Point, direction: Direction, distance: Int): Segment =
            if (distance == 0) {
                // Direction doesn't matter for single-point segments
                ofPoint(point)
            } else {
                when (direction) {
                    // Use the given point as start
                    Direction.UP, Direction.UP_RIGHT, Direction.RIGHT, Direction.DOWN_RIGHT ->
                        Segment(point, Slope.fromDirection(direction), distance)

                    // Use the given point as end
                    Direction.DOWN, Direction.DOWN_LEFT, Direction.LEFT, Direction.UP_LEFT -> {
                        val otherPoint = point.move(direction, distance)
                        Segment(otherPoint, Slope.fromDirection(direction.reverse()), distance)
                    }
                }
            }

        /**
         * Returns a segment that consists of a single [point].
         */
        fun ofPoint(point: Point): Segment = Segment(point, Slope.VERTICAL, 0)

        /**
         * Returns the overlap between horizontal segments [segmentA] and [segmentB].
         */
        private fun horizontalSegmentOverlap(segmentA: Segment, segmentB: Segment): Segment? {
            // Segments must have the same y-coordinate to overlap
            if (segmentA.start.y != segmentB.start.y) {
                return null
            }

            // Check if the segments' x-coordinates overlap
            val xOverlap = segmentA.xRange.overlapWith(segmentB.xRange)
            if (xOverlap.isEmpty()) {
                return null
            }

            val overlapStart = segmentA.start.copy(x = xOverlap.start)
            val overlapEnd = segmentA.start.copy(x = xOverlap.endInclusive)
            return between(overlapStart, overlapEnd)
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
            val yOverlap = segmentA.yRange.overlapWith(segmentB.yRange)
            if (yOverlap.isEmpty()) {
                return null
            }

            val overlapStart = segmentA.start.copy(y = yOverlap.start)
            val overlapEnd = segmentA.start.copy(y = yOverlap.endInclusive)
            return between(overlapStart, overlapEnd)
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
            val xOverlap = segmentA.xRange.overlapWith(segmentB.xRange)
            if (xOverlap.isEmpty()) {
                return null
            }

            // The segments' y-coordinates must also overlap
            val yOverlap = segmentA.yRange.overlapWith(segmentB.yRange)
            val overlapStart = Point(xOverlap.start, yOverlap.start)
            val overlapEnd = Point(xOverlap.endInclusive, yOverlap.endInclusive)
            return between(overlapStart, overlapEnd)
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
            val xOverlap = segmentA.xRange.overlapWith(segmentB.xRange)
            if (xOverlap.isEmpty()) {
                return null
            }

            // The segments' y-coordinates must also overlap
            val yOverlap = segmentA.yRange.overlapWith(segmentB.yRange)
            val overlapStart = Point(xOverlap.start, yOverlap.endInclusive)
            val overlapEnd = Point(xOverlap.endInclusive, yOverlap.start)
            return between(overlapStart, overlapEnd)
        }

        /**
         * Returns the intersection point of two [horizontal] and [vertical] segments.
         */
        private fun horizontalVerticalIntersection(horizontal: Segment, vertical: Segment): Point? =
            if (horizontal.start.y in vertical.yRange && vertical.start.x in horizontal.xRange) {
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

            val isInHorizontal = x in horizontal.xRange
            val isInUpRight = x in upRight.xRange
            return if (isInHorizontal && isInUpRight) Point(x, horizontal.start.y) else null
        }

        /**
         * Returns the intersection point of two [horizontal] and [downRight] diagonal segments.
         */
        private fun horizontalDownRightIntersection(
            horizontal: Segment,
            downRight: Segment
        ): Point? {
            // Find the x-coordinate of the diagonal segment if extended to the horizontal one
            val x = downRight.start.x + (downRight.start.y - horizontal.start.y)

            val isInHorizontal = x in horizontal.xRange
            val isInDownRight = x in downRight.xRange
            return if (isInHorizontal && isInDownRight) Point(x, horizontal.start.y) else null
        }

        /**
         * Returns the intersection point of two [vertical] and [upRight] diagonal segments.
         */
        private fun verticalUpRightIntersection(vertical: Segment, upRight: Segment): Point? {
            // Find the y-coordinate of the diagonal segment if extended to the vertical one
            val y = upRight.start.y + (vertical.start.x - upRight.start.x)

            val isInVertical = y in vertical.yRange
            val isInUpRight = y in upRight.yRange
            return if (isInVertical && isInUpRight) Point(vertical.start.x, y) else null
        }

        /**
         * Returns the intersection point of two [vertical] and [downRight] diagonal segments.
         */
        private fun verticalDownRightIntersection(vertical: Segment, downRight: Segment): Point? {
            // Find the y-coordinate of the diagonal segment if extended to the vertical one
            val y = downRight.start.y - (vertical.start.x - downRight.start.x)

            val isInVertical = y in vertical.yRange
            val isInDownRight = y in downRight.yRange
            return if (isInVertical && isInDownRight) Point(vertical.start.x, y) else null
        }

        /**
         * Returns the intersection point of two [upRight] and [downRight] diagonal segments.
         */
        private fun oppositeDiagonalIntersection(upRight: Segment, downRight: Segment): Point? {
            // Calculate 2x and 2y, assuming an intersection exists at point (x, y)
            val twoX = upRight.start.x - upRight.start.y + downRight.start.x + downRight.start.y
            val twoY = upRight.start.y - upRight.start.x + downRight.start.x + downRight.start.y
            if (twoX % 2 != 0 || twoY % 2 != 0) {
                // Intersection can't exist at non-integer coordinates
                return null
            }

            // Check if the intersection point is valid for both segments
            val x = twoX / 2
            val isInUpRight = x in upRight.xRange
            val isInDownRight = x in downRight.xRange
            return if (isInUpRight && isInDownRight) Point(x, twoY / 2) else null
        }
    }

    /**
     * A valid linear slope for a segment.
     *
     * @param direction The direction (from [start] to [end]) represented by this slope.
     */
    private enum class Slope(val direction: Direction) {

        /**
         * The slope of a segment that lies on a single horizontal line.
         */
        HORIZONTAL(Direction.RIGHT),

        /**
         * The slope of a segment that lies on a single vertical line.
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
}
