package com.curtislb.adventofcode.year2023.day16.beam

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.Pose
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.mutableGridOf
import java.io.File

/**
 * A contraption that consists of a 2D grid of tiles, which a beam can pass through while being
 * reflected by mirrors and split in multiple directions by splitters.
 *
 * @property grid The grid of [Tile]s that makes up the contraption.
 *
 * @constructor Creates a new instance of [BeamContraption] with the specified [grid] of tiles.
 */
class BeamContraption(private val grid: Grid<Tile>) {
    /**
     * Returns the number of grid tiles that a beam--with an initial position and direction given by
     * [beamStart]--passes through before exiting the contraption.
     */
    fun beamEnergy(beamStart: Pose): Int {
        // Keep track of energized tiles and previously seen beam poses
        val energized = mutableSetOf<Point>()
        val processed = mutableSetOf<Pose>()

        // Use flood fill (DFS) to trace the path of the beam
        val beamStack = ArrayDeque<Pose>().apply { addLast(beamStart) }
        while (beamStack.isNotEmpty()) {
            // Pop the latest beam pose from the stack, and check if it's valid
            val beam = beamStack.removeLast()
            if (beam in processed || beam.position !in grid) {
                continue
            }

            // Process the beam pose, and mark the tile as energized
            energized.add(beam.position)
            processed.add(beam)

            // Push subsequent beam pose(s) onto the stack
            when (grid[beam.position]) {
                // Beam passes through an empty tile
                Tile.EMPTY -> {
                    beamStack.addLast(beam.move())
                }

                // Beam is reflected by north-east mirror
                Tile.MIRROR_NE -> {
                    beamStack.addLast(reflectNorthEast(beam))
                }

                // Beam is reflected by south-east mirror
                Tile.MIRROR_SE -> {
                    beamStack.addLast(reflectSouthEast(beam))
                }

                // Horizontal beam is split by vertical splitter
                Tile.SPLIT_NS -> {
                    if (beam.direction.isHorizontal()) {
                        beamStack.addLast(beam.turnAndMove(Direction.UP))
                        beamStack.addLast(beam.turnAndMove(Direction.DOWN))
                    } else {
                        beamStack.addLast(beam.move())
                    }
                }

                // Vertical beam is split by horizontal splitter
                Tile.SPLIT_EW -> {
                    if (beam.direction.isVertical()) {
                        beamStack.addLast(beam.turnAndMove(Direction.LEFT))
                        beamStack.addLast(beam.turnAndMove(Direction.RIGHT))
                    } else {
                        beamStack.addLast(beam.move())
                    }
                }
            }
        }

        // Return the count of energized tiles
        return energized.size
    }

    /**
     * Returns the maximum number of tiles passed through by any beam that enters the contraption
     * grid from an edge while initially facing a cardinal direction.
     */
    fun findMaxBeamEnergy(): Int {
        val beamStarts = mutableListOf<Pose>()

        // Check beams entering the grid from the left or right
        for (rowIndex in grid.rowIndices) {
            val leftPosition = Point.fromMatrixCoordinates(rowIndex, 0)
            val rightPosition = Point.fromMatrixCoordinates(rowIndex, grid.lastColumnIndex)
            beamStarts.add(Pose(leftPosition, Direction.RIGHT))
            beamStarts.add(Pose(rightPosition, Direction.LEFT))
        }

        // Check beams entering the grid from the top or bottom
        for (colIndex in grid.columnIndices) {
            val topPosition = Point.fromMatrixCoordinates(0, colIndex)
            val bottomPosition = Point.fromMatrixCoordinates(grid.lastRowIndex, colIndex)
            beamStarts.add(Pose(topPosition, Direction.DOWN))
            beamStarts.add(Pose(bottomPosition, Direction.UP))
        }

        return beamStarts.maxOf { beamEnergy(it) }
    }

    /**
     * Returns the new beam pose after the given [beam] is reflected by a [Tile.MIRROR_NE] mirror.
     *
     * @throws IllegalArgumentException If [beam] has a diagonal direction.
     */
    private fun reflectNorthEast(beam: Pose): Pose {
        val newDirection = when (beam.direction) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.UP
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.DOWN
            else -> throw IllegalArgumentException("Invalid beam direction: ${beam.direction}")
        }
        return beam.turnAndMove(newDirection)
    }

    /**
     * Returns the new beam pose after the given [beam] is reflected by a [Tile.MIRROR_SE] mirror.
     *
     * @throws IllegalArgumentException If [beam] has a diagonal direction.
     */
    private fun reflectSouthEast(beam: Pose): Pose {
        val newDirection = when (beam.direction) {
            Direction.UP -> Direction.LEFT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.RIGHT
            Direction.LEFT -> Direction.UP
            else -> throw IllegalArgumentException("Invalid beam direction: ${beam.direction}")
        }
        return beam.turnAndMove(newDirection)
    }

    companion object {
        /**
         * Returns a [BeamContraption] with a tile grid read from the given [file].
         *
         * The [file] must contain lines of equal length, with each character representing a [Tile]
         * located at the corresponding grid position.
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): BeamContraption {
            val grid = mutableGridOf<Tile>()
            file.forEachLine { line ->
                val row = line.map { Tile.fromChar(it) }
                grid.addShallowRow(row)
            }
            return BeamContraption(grid)
        }
    }
}
