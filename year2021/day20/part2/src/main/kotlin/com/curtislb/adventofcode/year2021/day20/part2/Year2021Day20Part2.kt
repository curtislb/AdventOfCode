/*
--- Part Two ---

You still can't quite make out the details in the image. Maybe you just didn't enhance it enough.

If you enhance the starting input image in the above example a total of 50 times, 3351 pixels are
lit in the final output image.

Start again with the original input image and apply the image enhancement algorithm 50 times. How
many pixels are lit in the resulting image?
*/

package com.curtislb.adventofcode.year2021.day20.part2

import com.curtislb.adventofcode.year2021.day20.enhance.EnhancedImage
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 20, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param iterationCount The number of times to apply the enhancement algorithm to the image.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt"), iterationCount: Int = 50): Int {
    val enhancedImage = EnhancedImage.fromFile(inputPath.toFile()).apply { enhance(iterationCount) }
    return enhancedImage.countLitPixels()
}

fun main() {
    println(solve())
}
