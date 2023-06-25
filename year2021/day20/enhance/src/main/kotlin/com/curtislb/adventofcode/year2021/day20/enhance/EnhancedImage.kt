package com.curtislb.adventofcode.year2021.day20.enhance

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.gridOfPoints
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.grid.sumRowsBy
import com.curtislb.adventofcode.common.io.forEachSection
import java.io.File

/**
 * An image, consisting of lit and unlit pixels, that has been "enhanced" by applying an
 * [EnhancementAlgorithm] for some number of iterations.
 *
 * @param algorithm The enhancement algorithm to be applied repeatedly to the image.
 * @param originalImage A grid of lit and unlit pixels in the original image.
 */
class EnhancedImage(private val algorithm: EnhancementAlgorithm, originalImage: Grid<Boolean>) {
    /**
     * The number of times the enhancement algorithm has been applied to the original image.
     */
    var enhancementLevel: Int = 0

    /**
     * A grid of pixel values in the interior of the current image, with `true` representing a lit
     * pixel and `false` representing an unlit one.
     */
    private var innerPixelGrid: Grid<Boolean> = originalImage

    /**
     * Whether all outer pixels are lit on the infinite canvas containing [innerPixelGrid].
     */
    private var outerPixelValue: Boolean = false

    /**
     * Returns the number of lit pixels in the current image, or [Int.MAX_VALUE] if infinitely many
     * pixels are lit.
     */
    fun countLitPixels(): Int = if (outerPixelValue) {
        // Infinitely many outer pixels are lit
        Int.MAX_VALUE
    } else {
        // Count lit pixels in the inner grid
        innerPixelGrid.sumRowsBy { row ->
            row.count { it }
        }
    }

    /**
     * Applies the [EnhancementAlgorithm] to the current image a number of times equal to the given
     * [iterationCount].
     */
    fun enhance(iterationCount: Int = 1) {
        enhancementLevel += iterationCount
        repeat(iterationCount) {
            // Calculate new value for all outer pixels
            val newOuterPixelValue =
                if (outerPixelValue) algorithm.lastPixel() else algorithm.firstPixel()

            // Check which inner pixels should flip to lit or unlit
            val flippedPixels = mutableSetOf<Point>()
            for (rowIndex in -1..innerPixelGrid.height) {
                for (colIndex in -1..innerPixelGrid.width) {
                    val pixel = algorithm.getEnhancedPixel(
                        rowIndex,
                        colIndex,
                        innerPixelGrid,
                        outerPixelValue
                    )
                    if (pixel != newOuterPixelValue) {
                        val point = Point.fromMatrixCoordinates(rowIndex, colIndex)
                        flippedPixels.add(point)
                    }
                }
            }

            // Update outer and inner pixel values for the current image
            outerPixelValue = newOuterPixelValue
            innerPixelGrid = gridOfPoints(flippedPixels) { point ->
                if (point in flippedPixels) !outerPixelValue else outerPixelValue
            }
        }
    }

    companion object {
        /**
         * Returns an [EnhancedImage] with the [EnhancementAlgorithm] and original image read from
         * the given [file].
         *
         * The [file] must contain the following two sections in order, separated by a blank line:
         *
         * - A single line representing the [EnhancementAlgorithm], consisting of 512 pixel values.
         * - One or more lines representing the original image, where each line represents a row of
         *   pixels in the image.
         *
         * In each of the above sections, the `#` character represents a lit pixel, and the `.`
         * character represents an unlit one.
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): EnhancedImage {
            val algorithmPixels = mutableListOf<Boolean>()
            val imagePixels = mutableGridOf<Boolean>()
            file.forEachSection { lines ->
                if (algorithmPixels.isEmpty()) {
                    // Read algorithm string from the first section
                    for (char in lines.single()) {
                        algorithmPixels.add(isLitPixel(char))
                    }
                } else if (imagePixels.isEmpty()) {
                    // Read original image grid from the second section
                    for (line in lines) {
                        val imageRow = line.trim().map(::isLitPixel)
                        imagePixels.addShallowRow(imageRow)
                    }
                } else {
                    throw IllegalArgumentException("Incorrect file format")
                }
            }

            val algorithm = EnhancementAlgorithm(algorithmPixels)
            return EnhancedImage(algorithm, imagePixels)
        }

        /**
         * Returns if the given [pixelChar] represents a lit pixel.
         */
        private fun isLitPixel(pixelChar: Char): Boolean = pixelChar == '#'
    }
}
