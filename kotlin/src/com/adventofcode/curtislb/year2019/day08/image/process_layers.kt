package com.adventofcode.curtislb.year2019.day08.image

import com.adventofcode.curtislb.common.io.forEachChar
import com.adventofcode.curtislb.common.math.toDigit
import java.io.File

/**
 * Reads in and processes digits representing pixels in a multi-layer image.
 * @param file The [File] from which digits should be read.
 * @param imageArea The [Int] area (width * height) of the final image.
 * @param onDigit An optional callback to be run each time a digit is encountered.
 * @param onLayerFinished An optional callback to be run each time all digits in a layer have been read.
 */
fun processLayers(
    file: File,
    imageArea: Int,
    onDigit: (indexInLayer: Int, digit: Int) -> Unit = { _, _ -> },
    onLayerFinished: () -> Unit = { }
) {
    var indexInLayer = 0
    file.forEachChar { char ->
        // Check if we're done with the current layer.
        if (indexInLayer == imageArea) {
            onLayerFinished()
            indexInLayer = 0
        }

        // Process the next digit for this layer.
        if (char in '0'..'9') {
            onDigit(indexInLayer, char.toDigit())
            indexInLayer++
        }
    }
}
