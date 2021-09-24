package com.curtislb.adventofcode.year2019.day19.drone

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.range.BigIntegerRange
import com.curtislb.adventofcode.common.range.overlapWith
import com.curtislb.adventofcode.common.range.toIterableRange
import java.io.File
import java.math.BigInteger
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A system for deploying drones within a grid, in order to observe the effective range of an invisible tractor beam.
 *
 * @param file A file containing the [Intcode] program that controls each drone.
 */
class DroneSystem(file: File) {
    /**
     * A string representation of the [Intcode] program that controls each drone.
     */
    private val programString: String = file.readText().trim()

    /**
     * Deploys a drone to position ([rowIndex], [colIndex]) in the grid, invoking the function [onOutput] to handle
     * either of the following outputs from its associated program:
     * - 0 - The drone is *not* being pulled, indicating that the tractor beam does *not* affect this position.
     * - 1 - The drone *is* being pulled, indicating that the tractor beam *does* affect this position.
     */
    fun deployDrone(rowIndex: BigInteger, colIndex: BigInteger, onOutput: (output: BigInteger) -> Unit) {
        val drone = Intcode(programString, onOutput)
        drone.sendInput(colIndex, rowIndex)
        drone.run()
    }

    /**
     * Deploys drones to all grid positions whose row indices are in [rowRange] and whose column indices are in
     * [colRange], and returns the total number of these positions that are affected by the tractor beam.
     */
    fun scanArea(rowRange: BigIntegerRange, colRange: BigIntegerRange): Int {
        val count = AtomicInteger(0)
        runBlocking {
            for (rowIndex in rowRange) {
                for (colIndex in colRange) {
                    launch(Dispatchers.Default) {
                        deployDrone(rowIndex, colIndex) { count.addAndGet(it.toInt()) }
                    }
                }
            }
        }
        return count.toInt()
    }

    /**
     * Returns the range of column indices that are affected by the tractor beam in row [rowIndex].
     *
     * If provided, [beamStartGuess] and [beamEndGuess] specify the initial column indices around which this method will
     * search for the beginning and end of the beam, respectively.
     */
    fun findBeamRange(
        rowIndex: BigInteger,
        beamStartGuess: BigInteger = rowIndex * BigInteger("3") / BigInteger.TWO,
        beamEndGuess: BigInteger = rowIndex * BigInteger.TWO
    ): BigIntegerRange {
        var isInBeam = false
        val onOutput = { output: BigInteger -> isInBeam = output == BigInteger.ONE }

        // Make an initial guess for the beam range in this row.
        var beamStart = beamStartGuess
        var beamEnd = beamEndGuess

        // Search for the actual start of the beam range.
        deployDrone(rowIndex, beamStart, onOutput)
        while (isInBeam) {
            beamStart--
            deployDrone(rowIndex, beamStart, onOutput)
        }
        while (!isInBeam && beamStart <= beamEnd) {
            beamStart++
            deployDrone(rowIndex, beamStart, onOutput)
        }

        // Search for the actual end of the beam range.
        deployDrone(rowIndex, beamEnd, onOutput)
        while (isInBeam) {
            beamEnd++
            deployDrone(rowIndex, beamEnd, onOutput)
        }
        while (!isInBeam && beamEnd >= beamStart) {
            beamEnd--
            deployDrone(rowIndex, beamEnd, onOutput)
        }

        return BigIntegerRange(beamStart, beamEnd)
    }

    /**
     * Returns a range containing the overlapping column indices of the beam in the row [rowIndex] and the one
     * [rowDelta] rows below it in the grid.
     */
    fun findBeamOverlap(rowIndex: BigInteger, rowDelta: BigInteger): BigIntegerRange {
        val row1Range = findBeamRange(rowIndex)
        val row2Range = findBeamRange(rowIndex + rowDelta)
        return row1Range.overlapWith(row2Range).toIterableRange()
    }
}
