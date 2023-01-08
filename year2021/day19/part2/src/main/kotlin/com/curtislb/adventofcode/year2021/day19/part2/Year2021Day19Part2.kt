/*
--- Part Two ---

Sometimes, it's a good idea to appreciate just how big the ocean is. Using the Manhattan distance,
how far apart do the scanners get?

In the above example, scanners 2 (1105,-1205,1229) and 3 (-92,-2380,-20) are the largest Manhattan
distance apart. In total, they are 1197 + 1175 + 1249 = 3621 units apart.

What is the largest Manhattan distance between any two scanners?
*/

package com.curtislb.adventofcode.year2021.day19.part2

import com.curtislb.adventofcode.common.collection.uniquePairs
import com.curtislb.adventofcode.year2021.day19.beacon.ScannerField
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 19, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param minOverlapCount The minimum number of common beacons needed between two scanners to
 *  determine the scanners' relative locations.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt"), minOverlapCount: Int = 12): Int {
    val scannerField = ScannerField.fromFile(inputPath.toFile())
    val scannerPositions = scannerField.findBeaconAndScannerPositions(minOverlapCount).scanners
    return scannerPositions.toList().uniquePairs().maxOf { (p, q) -> p.manhattanDistance(q) }
}

fun main() {
    println(solve())
}