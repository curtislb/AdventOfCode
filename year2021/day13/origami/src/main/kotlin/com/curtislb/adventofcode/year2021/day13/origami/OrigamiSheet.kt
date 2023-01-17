package com.curtislb.adventofcode.year2021.day13.origami

import com.curtislb.adventofcode.common.collection.mapToMutableSet
import com.curtislb.adventofcode.common.geometry.Point

/**
 * A transparent 2D sheet of points, along with a list of instructions for how those points should
 * be folded to produce a particular image.
 *
 * @param pointStrings A list of strings representing each [Point] on the original, unfolded sheet.
 *  Each string must be of the form `"$x,y"`.
 * @param instructionStrings A list of strings representing [FoldInstruction]s that should be
 *  applied to the sheet in sequence.
 */
class OrigamiSheet(pointStrings: List<String>, instructionStrings: List<String>) {
    /**
     * The visible arrangement of points after [completedFoldCount] folds have been applied.
     */
    val points: Set<Point> get() = _points

    private var _points: MutableSet<Point> = pointStrings.mapToMutableSet { pointString ->
        Point.fromString(pointString, invertY = true)
    }

    /**
     * A list of fold instructions that should be applied in order to produce the desired image.
     */
    val instructions: List<FoldInstruction> = instructionStrings.map { instructionString ->
        FoldInstruction.fromString(instructionString)
    }

    /**
     * The number of fold instructions that have already been completed.
     */
    var completedFoldCount: Int = 0
        private set

    /**
     * Applies [instructionCount] fold instructions to the sheet in order, starting after the most
     * recently completed instruction.
     *
     * Each [FoldInstruction] is applied to all [points] simultaneously, and any newly overlapping
     * points are consolidated in the resulting point set.
     *
     * If fewer than [instructionCount] fold instructions are left to be completed, then all
     * remaining instructions will be applied.
     */
    fun fold(instructionCount: Int = instructions.size) {
        val newFoldCount = (completedFoldCount + instructionCount).coerceAtMost(instructions.size)
        for (instruction in instructions.subList(completedFoldCount, newFoldCount)) {
            _points = _points.mapToMutableSet { instruction.fold(it) }
        }
        completedFoldCount = newFoldCount
    }
}
