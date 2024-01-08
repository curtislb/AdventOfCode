package com.curtislb.adventofcode.year2023.day15.hash

/**
 * An arrangement of lenses, with various labels and focal lengths, into an ordered sequence of
 * boxes.
 *
 * @param boxCount The number of boxes into which lenses can be arranged.
 *
 * @constructor Creates a new instance of [LensConfiguration] with the given `boxCount`.
 */
class LensConfiguration(boxCount: Int) {
    /**
     * A sequence of boxes that makes up the current lens configuration.
     *
     * Each box is represented by an ordered [LinkedHashMap] from the label of each lens in that box
     * to its focal length.
     */
    private val boxes: Array<LinkedHashMap<String, Int>> = Array(boxCount) { linkedMapOf() }

    /**
     * The HASH algorithm used to convert each lens label to the index of its corresponding box.
     */
    private val hashAlgorithm: HashAlgorithm = HashAlgorithm(boxCount)

    /**
     * Returns the focusing power of all the lenses in the current configuration.
     *
     * The focusing power is given by multiplying the index (starting from 1) of each box by the sum
     * of the focal length of each lens in the box multiplied by its index (also from 1) within the
     * box.
     */
    fun findFocusingPower(): Int = boxes.withIndex().sumOf { (boxIndex, box) ->
        val boxPower = box.values.withIndex().sumOf { (valueIndex, value) ->
            (valueIndex + 1) * value
        }
        (boxIndex + 1) * boxPower
    }

    /**
     * Updates the current lens arrangement by performing the specified HASHMAP process [step].
     */
    fun performStep(step: HashmapStep) {
        val boxIndex = hashAlgorithm.convert(step.label)
        val box = boxes[boxIndex]
        when (step.operation) {
            Operation.Dash -> box.remove(step.label)
            is Operation.Equals -> box[step.label] = step.operation.focalLength
        }
    }
}
