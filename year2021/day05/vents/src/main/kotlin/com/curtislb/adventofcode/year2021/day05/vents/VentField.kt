package com.curtislb.adventofcode.year2021.day05.vents

import com.curtislb.adventofcode.common.collection.uniquePairs
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.Segment
import com.curtislb.adventofcode.common.parse.toInts

/**
 * A field of hydrothermal vents, each represented by a segment in a 2D grid.
 *
 * @param ventStrings A list of strings, representing the vents that make up this field. Each string
 *  must be of the form `"$x1,$y1 -> $x2,$y2"`, where `(x1, y1)` and `(x2, y2)` are the endpoints of
 *  the segment corresponding to that vent.
 * @param filterPredicate A function used to select which vents should be added to the field.
 */
class VentField(ventStrings: List<String>, filterPredicate: (vent: Segment) -> Boolean = { true }) {
    /**
     * A list of segments, representing all vents in this field.
     */
    private val vents: List<Segment> = mutableListOf<Segment>().apply {
        for (ventString in ventStrings) {
            val (x1, y1, x2, y2) = ventString.toInts()
            val segment = Segment.between(Point(x1, y1), Point(x2, y2))
            if (filterPredicate(segment)) {
                add(segment)
            }
        }
    }

    /**
     * Returns all points in the field where two or more vents overlap.
     */
    fun overlapPoints(): Set<Point> {
        val points = mutableSetOf<Point>()
        for ((ventA, ventB) in vents.uniquePairs()) {
            ventA.overlapWith(ventB)?.let { points.addAll(it.points()) }
        }
        return points
    }
}
