package com.curtislb.adventofcode.year2019.day08.image

import com.curtislb.adventofcode.common.io.forEachChar
import com.curtislb.adventofcode.common.math.toDigit
import java.io.File

/**
 * Processes digits from a [file] representing pixels in a multi-layer image with a given
 * [imageArea] (width * height).
 *
 * @param onDigit An optional callback to be run each time a digit is encountered.
 * @param onLayerFinished An optional callback to run each time all digits in a layer are read.
 */
fun processLayers(
    file: File,
    imageArea: Int,
    onDigit: (indexInLayer: Int, digit: Int) -> Unit = { _, _ -> },
    onLayerFinished: () -> Unit = {}
) {
    var indexInLayer = 0
    file.forEachChar { char ->
        // Process the next digit for this layer.
        if (char in '0'..'9') {
            onDigit(indexInLayer, char.toDigit())
            indexInLayer++
        }

        // Check if we're done with the current layer.
        if (indexInLayer == imageArea) {
            onLayerFinished()
            indexInLayer = 0
        }
    }
}
