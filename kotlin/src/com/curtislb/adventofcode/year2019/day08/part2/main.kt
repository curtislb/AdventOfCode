/*
--- Part Two ---

Now you're ready to decode the image. The image is rendered by stacking the layers and aligning the pixels with the
same positions in each layer. The digits indicate the color of the corresponding pixel: 0 is black, 1 is white, and 2
is transparent.

The layers are rendered with the first layer in front and the last layer in back. So, if a given position has a
transparent pixel in the first and second layers, a black pixel in the third layer, and a white pixel in the fourth
layer, the final image would have a black pixel at that position.

For example, given an image 2 pixels wide and 2 pixels tall, the image data 0222112222120000 corresponds to the
following image layers:

Layer 1: 02
         22

Layer 2: 11
         22

Layer 3: 22
         12

Layer 4: 00
         00

Then, the full image can be found by determining the top visible pixel in each position:

  - The top-left pixel is black because the top layer is 0.
  - The top-right pixel is white because the top layer is 2 (transparent), but the second layer is 1.
  - The bottom-left pixel is white because the top two layers are 2, but the third layer is 1.
  - The bottom-right pixel is black because the only visible pixel in that position is 0 (from layer 4).

So, the final image looks like this:

01
10

What message is produced after decoding your image?
*/

package com.curtislb.adventofcode.year2019.day08.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day08.image.Pixel
import com.curtislb.adventofcode.year2019.day08.image.processLayers
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for day 8, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param imageWidth The width of each layer of the image, in number of pixels.
 * @param imageHeight The height of each layer of the image, in number of pixels.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 8), imageWidth: Int = 25, imageHeight: Int = 6): String {
    // Calculate pixel values for the composed image.
    val image = Array(imageHeight) { Array(imageWidth) { Pixel.ALPHA } }
    processLayers(
        inputPath.toFile(),
        imageArea = imageWidth * imageHeight,
        onDigit = { indexInLayer, digit ->
            val row = indexInLayer / imageWidth
            val col = indexInLayer % imageWidth
            if (image[row][col] == Pixel.ALPHA) {
                image[row][col] = Pixel.from(digit)
            }
        }
    )

    // Convert the resulting image to a string.
    return image.joinToString(separator = "\n") { row ->
        row.joinToString(separator = "") { pixel -> pixel.symbol.toString() }
    }
}

// Answer: ZUKCJ
fun main() {
    println(solve())
}
