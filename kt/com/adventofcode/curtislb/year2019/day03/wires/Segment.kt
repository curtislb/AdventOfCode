package com.adventofcode.curtislb.year2019.day03.wires

/**
 * A horizontal or vertical segment on a 2D grid with positive and negative integer coordinates.
 * @param start The starting point of the segment on the grid.
 * @param direction The direction in which the segment extends from its starting point.
 * @param length The length of the segment in grid units.
 */
data class Segment(val start: Point, val direction: Direction, val length: Int) {
    /**
     * The end point of this [Segment] on the grid.
     */
    val end: Point
        get() = start.move(direction, length)

    /**
     * Whether or not this [Segment] is horizontal, as opposed to vertical.
     */
    val isHorizontal: Boolean
        get() = direction == Direction.RIGHT || direction == Direction.LEFT

    /**
     * Determines if this and another [Segment] are perpendicular to one another.
     * @param other The [Segment] to be compared with this one.
     * @return `true` if the two segments are perpendicular, or `false` otherwise.
     */
    fun isPerpendicularTo(other: Segment): Boolean = isHorizontal != other.isHorizontal

    /**
     * Checks for a single intersection point between this and another [Segment].
     *
     * Note that this function only checks for an intersection between perpendicular segments. This means that it will
     * always return `null` for a [Segment] that is parallel to this one, even if the two overlap at one or more points.
     *
     * @param other The [Segment] to be checked for intersections with this one.
     * @return The [Point] at which the two segments intersect, or `null` if they do not intersect.
     */
    fun intersectionWith(other: Segment): Point? {
        // Parallel segments cannot intersect.
        if (!isPerpendicularTo(other)) {
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
