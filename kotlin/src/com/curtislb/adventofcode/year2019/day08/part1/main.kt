/*
--- Day 8: Space Image Format ---

The Elves' spirits are lifted when they realize you have an opportunity to reboot one of their Mars rovers, and so they
are curious if you would spend a brief sojourn on Mars. You land your ship near the rover.

When you reach the rover, you discover that it's already in the process of rebooting! It's just waiting for someone to
enter a BIOS password. The Elf responsible for the rover takes a picture of the password (your puzzle input) and sends
it to you via the Digital Sending Network.

Unfortunately, images sent via the Digital Sending Network aren't encoded with any normal encoding; instead, they're
encoded in a special Space Image Format. None of the Elves seem to remember why this is the case. They send you the
instructions to decode it.

Images are sent as a series of digits that each represent the color of a single pixel. The digits fill each row of the
image left-to-right, then move downward to the next row, filling rows top-to-bottom until every pixel of the image is
filled.

Each image actually consists of a series of identically-sized layers that are filled in this way. So, the first digit
corresponds to the top-left pixel of the first layer, the second digit corresponds to the pixel to the right of that on
the same layer, and so on until the last digit, which corresponds to the bottom-right pixel of the last layer.

For example, given an image 3 pixels wide and 2 pixels tall, the image data 123456789012 corresponds to the following
image layers:

Layer 1: 123
         456

Layer 2: 789
         012

The image you received is 25 pixels wide and 6 pixels tall.

To make sure the image wasn't corrupted during transmission, the Elves would like you to find the layer that contains
the fewest 0 digits. On that layer, what is the number of 1 digits multiplied by the number of 2 digits?
*/

package com.curtislb.adventofcode.year2019.day08.part1

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day08.image.processLayers
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for day 8, part 1.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param imageWidth The width of each layer of the image, in number of pixels.
 * @param imageHeight The height of each layer of the image, in number of pixels.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 8), imageWidth: Int = 25, imageHeight: Int = 6): Int {
    var result = 0
    var minZeros = Int.MAX_VALUE
    var digitCounts = IntArray(10)
    processLayers(
        inputPath.toFile(),
        imageArea = imageWidth * imageHeight,
        onDigit = { _, digit -> digitCounts[digit]++ },
        onLayerFinished = {
            // Update the result if this layer has the fewest zeros so far.
            if (digitCounts[0] < minZeros) {
                result = digitCounts[1] * digitCounts[2]
                minZeros = digitCounts[0]
            }
            digitCounts = IntArray(10)
        }
    )
    return result
}

// Answer: 1820
fun main() { println(solve()) }
