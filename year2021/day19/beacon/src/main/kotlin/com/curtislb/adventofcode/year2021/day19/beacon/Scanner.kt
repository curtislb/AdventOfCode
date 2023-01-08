package com.curtislb.adventofcode.year2021.day19.beacon

import com.curtislb.adventofcode.common.collection.mapToSet
import com.curtislb.adventofcode.common.grid.Point3D

/**
 * A scanner that can locate the relative positions of beacons in a 3D grid, from one of 24 spatial
 * orientations.
 *
 * @param beaconStrings A list of strings representing beacon positions in the format `"$x,$y,$z"`.
 *
 * @throws IllegalArgumentException If `beaconStrings` is not formatted correctly.
 */
class Scanner(beaconStrings: List<String>) {
    /**
     * The relative positions of all beacons as read by the scanner.
     */
    private val beaconReadings: Set<Point3D> = beaconStrings.mapToSet { Point3D.fromString(it) }

    /**
     * A map from each [ScannerOrientation] to the set of [beaconReadings] transformed into that
     * orientation, assuming an initial orientation of [ScannerOrientation.X_Y_Z].
     */
    private val orientationToBeaconsMap: MutableMap<ScannerOrientation, Set<Point3D>> =
        mutableMapOf(ScannerOrientation.X_Y_Z to beaconReadings)

    /**
     * Returns the positions of all beacons detected by the scanner when transformed into the given
     * [orientation], assuming from an initial orientation of [ScannerOrientation.X_Y_Z].
     */
    fun getBeaconPositions(orientation: ScannerOrientation): Set<Point3D> =
        orientationToBeaconsMap.getOrPut(orientation) {
            beaconReadings.mapToSet { orientation.transform(it) }
        }

    override fun toString(): String = beaconReadings.joinToString(separator = "\n")
}