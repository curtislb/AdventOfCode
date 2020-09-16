package com.curtislb.adventofcode.common.grid

/**
 * A horizontal or vertical segment in a 2D grid.
 *
 * @param start The starting point of the segment.
 * @param direction The direction in which the segment extends from [start].
 * @param length The length of the segment in grid units.
 *
 * @throws IllegalArgumentException If [length] is negative.
 */
data class Segment(val start: Point, val direction: Direction, val length: Int) {
    /**
     * The end point of this segment.
     */
    val end: Point get() = start.move(direction, length)

    /**
     * Whether this segment is horizontal, as opposed to vertical.
     */
    val isHorizontal: Boolean get() = direction == Direction.RIGHT || direction == Direction.LEFT

    init {
        require(length >= 0) { "Length must be non-negative: $length" }
    }

    /**
     * Returns `true` if this segment and [other] are perpendicular, or `false` otherwise.
     */
    fun isPerpendicular(other: Segment): Boolean = isHorizontal != other.isHorizontal

    /**
     * Returns the intersection point between this segment and [other], or `null` if the segments do not intersect.
     *
     * This method only checks for an intersection with a perpendicular segment. This means that it will return `null`
     * if this segment and [other] are parallel, even if they overlap at one or more points.
     */
    fun intersection(other: Segment): Point? {
        // Parallel segments cannot intersect.
        if (!isPerpendicular(other)) {
            return null
        }

        // Categorize segments by their orientation.
        val horizontalSegment: Segment
        val verticalSegment: Segment
        if (isHorizontal) {
            horizontalSegment = this
            verticalSegment = other
        } else {
            horizontalSegment = other
            verticalSegment = this
        }

        // Check for intersection point, and return it if one exists.
        val (horizontalLeft, horizontalRight) = listOf(horizontalSegment.start, horizontalSegment.end).sortedBy { it.x }
        val (verticalBottom, verticalTop) = listOf(verticalSegment.start, verticalSegment.end).sortedBy { it.y }
        val horizontalY = horizontalSegment.start.y
        val verticalX = verticalSegment.start.x
        if (horizontalY in verticalBottom.y..verticalTop.y && verticalX in horizontalLeft.x..horizontalRight.x) {
            return Point(verticalX, horizontalY)
        }

        // No intersection point.
        return null
    }
}
