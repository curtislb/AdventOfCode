/*
--- Part Two ---

You still can't quite make out the details in the image. Maybe you just didn't enhance it enough.

If you enhance the starting input image in the above example a total of 50 times, 3351 pixels are
lit in the final output image.

Start again with the original input image and apply the image enhancement algorithm 50 times. How
many pixels are lit in the resulting image?
*/

package com.curtislb.adventofcode.year2021.day20.part2

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.constructPointGrid
import com.curtislb.adventofcode.common.grid.sumRowsBy
import com.curtislb.adventofcode.common.io.forEachSection
import com.curtislb.adventofcode.common.math.digitsToInt
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 20, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int? {
    var algorithmString: String? = null
    val initialLitPixels = mutableSetOf<Point>()
    inputPath.toFile().forEachSection { lines ->
        if (algorithmString == null) {
            algorithmString = lines.single().trim()
        } else {
            lines.forEachIndexed { rowIndex, line ->
                line.trim().forEachIndexed { colIndex, char ->
                    if (char == '#') {
                        initialLitPixels.add(Point.fromMatrixCoordinates(rowIndex, colIndex))
                    }
                }
            }
        }
    }

    val algorithmPixels = algorithmString!!.map { it == '#' }
    var innerPixelGrid = constructPointGrid(initialLitPixels) { it in initialLitPixels }
    var outerPixel = false

    repeat(50) {
        val newOuterPixel = if (outerPixel) algorithmPixels.last() else algorithmPixels.first()

        val flippedPixels = mutableSetOf<Point>()
        for (rowIndex in -1..innerPixelGrid.height) {
            for (colIndex in -1..innerPixelGrid.width) {
                val point = Point.fromMatrixCoordinates(rowIndex, colIndex)
                val pixel = enhancePixel(algorithmPixels, innerPixelGrid, outerPixel, point)
                if (pixel != newOuterPixel) {
                    flippedPixels.add(point)
                }
            }
        }

        outerPixel = newOuterPixel
        innerPixelGrid = constructPointGrid(flippedPixels) { point ->
            if (point in flippedPixels) !newOuterPixel else newOuterPixel
        }
    }

    return if (outerPixel) {
        null
    } else {
        innerPixelGrid.sumRowsBy { row ->
            row.count { it }
        }
    }
}

private fun enhancePixel(
    algorithmPixels: List<Boolean>,
    innerPixelGrid: Grid<Boolean>,
    outerPixel: Boolean,
    point: Point
): Boolean {
    val indexDigits = mutableListOf<Int>()
    for (y in (point.y + 1) downTo (point.y - 1)) {
        for (x in (point.x - 1)..(point.x + 1)) {
            val inputPoint = Point(x, y)
            val inputPixel = if (inputPoint in innerPixelGrid) {
                innerPixelGrid[inputPoint]
            } else {
                outerPixel
            }
            indexDigits.add(if (inputPixel) 1 else 0)
        }
    }

    val algorithmIndex = indexDigits.digitsToInt(radix = 2)
    return algorithmPixels[algorithmIndex]
}

fun main() {
    println(solve())
}
