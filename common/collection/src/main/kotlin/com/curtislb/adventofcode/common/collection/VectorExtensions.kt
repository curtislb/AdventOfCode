package com.curtislb.adventofcode.common.collection

/**
 * TODO
 */
fun IntArray.addVector(other: IntArray) {
    require(size == other.size) { "Arrays must have the same size: $size != ${other.size}" }
    for (i in indices) {
        this[i] += other[i]
    }
}

/**
 * TODO
 */
fun IntArray.neighboringVectors(): List<IntArray> {
    val neighbors = mutableListOf<IntArray>()
    listOf(-1, 0, 1).forEachNested(size) { indexedOffsets ->
        val offsets = indexedOffsets.map { (_, offset) -> offset }.toIntArray()
        if (offsets.any { it != 0 }) {
            neighbors.add(plusVector(offsets))
        }
        false // Don't stop iterating
    }
    return neighbors
}

/**
 * TODO
 */
fun IntArray.plusVector(other: IntArray): IntArray {
    require(size == other.size) { "Arrays must have the same size: $size != ${other.size}" }
    return IntArray(size) { index -> this[index] + other[index] }
}
