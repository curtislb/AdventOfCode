package com.curtislb.adventofcode.year2021.day19.beacon

import com.curtislb.adventofcode.common.geometry.Point3D
import com.curtislb.adventofcode.common.io.forEachSection
import java.io.File

/**
 * A collection of [Scanner]s that detect beacons in partially overlapping regions of a 3D cube.
 *
 * @param scanners A list of all [Scanner]s in the field.
 */
class ScannerField(private val scanners: List<Scanner>) {
    /**
     * Returns the positions of all scanners in the field, and all the beacons they can detect,
     * relative to the position and orientation of the first scanner in [scanners].
     *
     * @param minOverlapCount The minimum number of common beacons needed between two scanners to
     *  determine the scanners' relative locations.
     *
     * @throws IllegalArgumentException If [minOverlapCount] is negative.
     */
    fun findBeaconAndScannerPositions(minOverlapCount: Int): BeaconAndScannerPositions {
        require(minOverlapCount >= 0) { "Min overlap count must be non-negative: $minOverlapCount" }

        // Keep track of known beacon and scanner locations
        val knownBeacons = scanners[0].getBeaconPositions(ScannerOrientation.X_Y_Z).toMutableSet()
        val knownScanners = mutableMapOf(0 to Point3D.ORIGIN)

        // Locate positions of all remaining scanners (and the beacons they detect)
        while (knownScanners.size < scanners.size) {
            for (scannerIndex in scanners.indices) {
                if (scannerIndex in knownScanners) {
                    continue
                }

                // Check each orientation to see if scanner beacons overlap with known set
                for (orientation in ScannerOrientation.values()) {
                    val beacons = scanners[scannerIndex].getBeaconPositions(orientation)
                    val translation = findTranslation(beacons, knownBeacons, minOverlapCount)
                    if (translation != null) {
                        for (beacon in beacons) {
                            knownBeacons.add(beacon + translation)
                        }
                        knownScanners[scannerIndex] = translation
                        break
                    }
                }
            }
        }

        // Once all beacons and scanners are located, return their positions
        return BeaconAndScannerPositions(
            beacons = knownBeacons,
            scanners = knownScanners.values.toSet()
        )
    }

    /**
     * Returns a 3D point representing a translation vector that maps at least [minOverlapCount]
     * positions in [points] to ones in [transformedPoints]. If no such vector exists, this function
     * instead returns `null`.
     */
    private fun findTranslation(
        points: Set<Point3D>,
        transformedPoints: Set<Point3D>,
        minOverlapCount: Int
    ): Point3D? {
        // Select an initial point and a point to which it may be translated
        for (point in points) {
            for (transformedPoint in transformedPoints) {
                // Check if this translation overlaps enough points to reach the threshold
                var overlapCount = 1
                for ((index, otherPoint) in points.withIndex()) {
                    if (otherPoint == point) continue
                    val queryPoint = transformedPoint + (otherPoint - point)
                    if (queryPoint in transformedPoints) {
                        // If the overlap threshold is reached, return this translation
                        overlapCount++
                        if (overlapCount >= minOverlapCount) {
                            return transformedPoint - point
                        }
                    } else if (points.size - index <= minOverlapCount - overlapCount) {
                        // Not enough points left to reach the overlap threshold
                        break
                    }
                }
            }
        }

        // No viable translation found
        return null
    }

    companion object {
        /**
         * Returns a [ScannerField], with each scanner's beacon readings read from the given [file].
         *
         * The [file] must have the following format, where `$x01` represents the x-coordinate of
         * the beacon at index 1 for the scanner at index 0, and so on:
         *
         * ```
         * --- scanner 0 ---
         * $x00,$y00,$z00
         * $x01,$y01,$z01
         *       ...
         * $x0n,$y0n,$z0n
         *
         * --- scanner 1 ---
         * $x10,$y10,$z10
         * $x11,$y11,$z11
         *       ...
         * $x1n,$y1n,$z1n
         *
         *       ...
         *
         * --- scanner m ---
         * $xm0,$ym0,$zm0
         * $xm1,$ym1,$zm1
         *       ...
         * $xmn,$ymn,$zmn
         * ```
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): ScannerField {
            val scanners = mutableListOf<Scanner>()
            file.forEachSection { lines ->
                val beaconStrings = lines.subList(1, lines.size)
                scanners.add(Scanner(beaconStrings))
            }
            return ScannerField(scanners)
        }
    }
}
