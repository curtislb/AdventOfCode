package com.curtislb.adventofcode.year2021.day19.beacon

import com.curtislb.adventofcode.common.grid.Point3D

/**
 * A set of beacon and scanner positions, returned by [ScannerField.findBeaconAndScannerPositions].
 *
 * @param beacons A set of beacon positions, all relative to the same [Scanner].
 * @param scanners A set of scanner positions, all relative to the same [Scanner].
 */
data class BeaconAndScannerPositions(val beacons: Set<Point3D>, val scanners: Set<Point3D>)
