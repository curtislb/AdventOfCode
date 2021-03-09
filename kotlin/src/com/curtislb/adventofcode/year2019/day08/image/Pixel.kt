package com.curtislb.adventofcode.year2019.day08.image

/**
 * Pixel colors that may be present in an image.
 *
 * @param symbol The character used to represent this pixel.
 */
enum class Pixel(val symbol: Char) {
    /**
     * An opaque black pixel.
     */
    BLACK(' '),

    /**
     * An opaque white pixel.
     */
    WHITE('#'),

    /**
     * A transparent pixel.
     */
    ALPHA(' ');

    companion object {
        /**
         * Returns the pixel corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding pixel.
         */
        fun from(value: Int): Pixel = when (value) {
            0 -> BLACK
            1 -> WHITE
            2 -> ALPHA
            else -> throw IllegalArgumentException("Unknown pixel value: $value")
        }
    }
}
