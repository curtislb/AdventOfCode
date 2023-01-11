package com.curtislb.adventofcode.year2021.day20.enhance

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.math.digitsToInt

/**
 * An algorithm, consisting of a list of [Boolean] pixel values, that represents how the pixel
 * values in a 2D image should be changed during each iteration to "enhance" that image.
 *
 * @param pixelValues The list of pixel values that define the algorithm.
 */
class EnhancementAlgorithm(private val pixelValues: List<Boolean>) {
    init {
        // Enforce algorithm pixel size
        require(pixelValues.size == PIXEL_SIZE)
    }

    /**
     * Returns the pixel value at the given [index] of the algorithm.
     */
    operator fun get(index: Int): Boolean = pixelValues[index]

    /**
     * Returns the first pixel value of the algorithm.
     */
    fun firstPixel(): Boolean = pixelValues.first()

    /**
     * Returns the last pixel value of the algorithm.
     */
    fun lastPixel(): Boolean = pixelValues.last()

    /**
     * Returns the new pixel value at the given [rowIndex] and [colIndex] after applying the
     * algorithm to an image consisting of an [innerPixelGrid] and an infinite canvas of
     * [outerPixelValue]s.
     */
    fun getEnhancedPixel(
        rowIndex: Int,
        colIndex: Int,
        innerPixelGrid: Grid<Boolean>,
        outerPixelValue: Boolean
    ): Boolean {
        // Determine the binary digits of the index for the new pixel value
        val indexDigits = mutableListOf<Int>()
        for (i in (rowIndex - 1)..(rowIndex + 1)) {
            for (j in (colIndex - 1)..(colIndex + 1)) {
                val pixel = innerPixelGrid.getOrNull(i, j) ?: outerPixelValue
                indexDigits.add(if (pixel) 1 else 0)
            }
        }

        // Get the "enhanced" pixel value from the given algorithm index
        val algorithmIndex = indexDigits.digitsToInt(radix = 2)
        return pixelValues[algorithmIndex]
    }

    companion object {
        /**
         * Returns the number of pixel values in an [EnhancementAlgorithm].
         */
        const val PIXEL_SIZE = 512
    }
}