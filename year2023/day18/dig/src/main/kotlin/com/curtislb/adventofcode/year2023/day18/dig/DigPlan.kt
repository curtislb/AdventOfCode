package com.curtislb.adventofcode.year2023.day18.dig

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.polygonArea

/**
 * A dig plan that consists of a list of instructions for digging out a lava lagoon.
 *
 * @property instructions The ordered list of instructions in the dig plan.
 *
 * @constructor Creates a new instance of [DigPlan] with the specified [instructions].
 */
class DigPlan(private val instructions: List<Instruction>) {
    /**
     * Calculates the area of the lava lagoon produced by following the dig plan instructions.
     */
    fun calculateArea(): Long {
        // Keep track of the polygon perimeter and vertices produced by the dig plan
        var perimeter = 0L
        var position = Point.ORIGIN
        val vertices = mutableListOf<Point>()
        for (instruction in instructions) {
            perimeter += instruction.distance
            position = position.move(instruction.direction, instruction.distance)
            vertices.add(position)
        }

        // Calculate the interior area, and add a correction for the perimeter squares
        return polygonArea(vertices) + (perimeter / 2L) + 1L
    }
}
