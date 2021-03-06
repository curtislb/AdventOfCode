/*
--- Part Two ---

Now, you're ready to check the image for sea monsters.

The borders of each tile are not part of the actual image; start by removing them.

In the example above, the tiles become:

.#.#..#. ##...#.# #..#####
###....# .#....#. .#......
##.##.## #.#.#..# #####...
###.#### #...#.## ###.#..#
##.#.... #.##.### #...#.##
...##### ###.#... .#####.#
....#..# ...##..# .#.###..
.####... #..#.... .#......

#..#.##. .#..###. #.##....
#.####.. #.####.# .#.###..
###.#.#. ..#.#### ##.#..##
#.####.. ..##..## ######.#
##..##.# ...#...# .#.#.#..
...#..#. .#.#.##. .###.###
.#.#.... #.##.#.. .###.##.
###.#... #..#.##. ######..

.#.#.### .##.##.# ..#.##..
.####.## #.#...## #.#..#.#
..#.#..# ..#.#.#. ####.###
#..####. ..#.#.#. ###.###.
#####..# ####...# ##....##
#.##..#. .#...#.. ####...#
.#.###.. ##..##.. ####.##.
...###.. .##...#. ..#..###

Remove the gaps to form the actual image:

.#.#..#.##...#.##..#####
###....#.#....#..#......
##.##.###.#.#..######...
###.#####...#.#####.#..#
##.#....#.##.####...#.##
...########.#....#####.#
....#..#...##..#.#.###..
.####...#..#.....#......
#..#.##..#..###.#.##....
#.####..#.####.#.#.###..
###.#.#...#.######.#..##
#.####....##..########.#
##..##.#...#...#.#.#.#..
...#..#..#.#.##..###.###
.#.#....#.##.#...###.##.
###.#...#..#.##.######..
.#.#.###.##.##.#..#.##..
.####.###.#...###.#..#.#
..#.#..#..#.#.#.####.###
#..####...#.#.#.###.###.
#####..#####...###....##
#.##..#..#...#..####...#
.#.###..##..##..####.##.
...###...##...#...#..###

Now, you're ready to search for sea monsters! Because your image is monochrome, a sea monster will look like this:

                  #
#    ##    ##    ###
 #  #  #  #  #  #

When looking for this pattern in the image, the spaces can be anything; only the # need to match. Also, you might need
to rotate or flip your image before it's oriented correctly to find sea monsters. In the above image, after flipping and
rotating it to the appropriate orientation, there are two sea monsters (marked with O):

.####...#####..#...###..
#####..#..#.#.####..#.#.
.#.#...#.###...#.##.O#..
#.O.##.OO#.#.OO.##.OOO##
..#O.#O#.O##O..O.#O##.##
...#.#..##.##...#..#..##
#.##.#..#.#..#..##.#.#..
.###.##.....#...###.#...
#.####.#.#....##.#..#.#.
##...#..#....#..#...####
..#.##...###..#.#####..#
....#.##.#.#####....#...
..##.##.###.....#.##..#.
#...#...###..####....##.
.#.##...#.##.#.#.###...#
#.###.#..####...##..#...
#.###...#.##...#.##O###.
.O##.#OO.###OO##..OOO##.
..O#.O..O..O.#O##O##.###
#.#..##.########..#..##.
#.#####..#.#...##..#....
#....##..#.#########..##
#...#.....#..##...###.##
#..###....##.#...##.##.#

Determine how rough the waters are in the sea monsters' habitat by counting the number of # that are not part of a sea
monster. In the above example, the habitat's water roughness is 273.

How many # are not part of a sea monster?
*/

package com.curtislb.adventofcode.year2020.day20.part2

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.PointMask
import com.curtislb.adventofcode.common.grid.constructPointGrid
import com.curtislb.adventofcode.common.grid.forEachPoint
import com.curtislb.adventofcode.common.grid.sumRowsBy
import com.curtislb.adventofcode.common.grid.toMutableGrid
import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2020.day20.image.ImageData
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2020, day 20, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = pathToInput(year = 2020, day = 20)): Int {
    val file = inputPath.toFile()
    val imageData = ImageData(file)

    val maskPoints = listOf(
        Point(0, -1),
        Point(1, -2),
        Point(4, -2),
        Point(5, -1),
        Point(6, -1),
        Point(7, -2),
        Point(10, -2),
        Point(11, -1),
        Point(12, -1),
        Point(13, -2),
        Point(16, -2),
        Point(17, -1),
        Point(18, 0),
        Point(18, -1),
        Point(19, -1)
    )

    val maskGrids = mutableListOf(constructPointGrid(maskPoints) { it in maskPoints })
    for (rotationCount in 1..3) {
        maskGrids.add(maskGrids.last().rotatedLeft())
    }
    maskGrids.add(maskGrids.last().flippedHorizontal())
    for (rotationCount in 1..3) {
        maskGrids.add(maskGrids.last().rotatedLeft())
    }

    val pointMasks = mutableListOf<PointMask>().apply {
        maskGrids.forEach { maskGrid ->
            val points = mutableSetOf<Point>().apply {
                maskGrid.forEachPoint { point, isIncluded ->
                    if (isIncluded) {
                        add(point)
                    }
                }
            }
            add(PointMask(points))
        }
    }

    val imageGrid = imageData.constructImageGrid()
    val monsterPoints = mutableSetOf<Point>()
    pointMasks.forEach { initialMask ->
        var pointMask = initialMask
        val maxRowIndex = imageData.sideLength - pointMask.height
        val maxColIndex = imageData.sideLength - pointMask.width
        for (rowIndex in 0..maxRowIndex) {
            for (colIndex in 0..maxColIndex) {
                val isMonster = pointMask.applyMask(imageGrid).all { (_, isFilled) -> isFilled }
                if (isMonster) {
                    monsterPoints.addAll(pointMask.includedPoints)
                }
                pointMask = pointMask.translated(Direction.RIGHT)
            }
            pointMask = pointMask.translated(Direction.LEFT, maxColIndex + 1).translated(Direction.DOWN)
        }
    }

    val filledPointCount = imageGrid.toMutableGrid().sumRowsBy { row -> row.count { isFilled -> isFilled } }
    return filledPointCount - monsterPoints.size
}

fun main() {
    println(solve())
}
