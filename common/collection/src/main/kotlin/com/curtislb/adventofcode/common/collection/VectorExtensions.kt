package com.curtislb.adventofcode.common.collection

/**
 * Updates the values of this array to match the vector sum of this and [other].
 *
 * @throws IllegalArgumentException If this and [other] do not have the same size.
 */
fun IntArray.addVector(other: IntArray) {
    require(size == other.size) { "Arrays must have the same size: $size != ${other.size}" }
    for (i in indices) {
        this[i] += other[i]
    }
}

/**
 * Updates the values of this array to match the vector sum of this and [other].
 *
 * @throws IllegalArgumentException If this and [other] do not have the same size.
 */
fun LongArray.addVector(other: LongArray) {
    require(size == other.size) { "Arrays must have the same size: $size != ${other.size}" }
    for (i in indices) {
        this[i] += other[i]
    }
}

/**
 * Returns a list of arrays representing all other vectors whose component values differ from this
 * by at most 1.
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
 * Returns a list of arrays representing all other vectors whose component values differ from this
 * by at most 1.
 */
fun LongArray.neighboringVectors(): List<LongArray> {
    val neighbors = mutableListOf<LongArray>()
    listOf(-1L, 0L, 1L).forEachNested(size) { indexedOffsets ->
        val offsets = indexedOffsets.map { (_, offset) -> offset }.toLongArray()
        if (offsets.any { it != 0L }) {
            neighbors.add(plusVector(offsets))
        }
        false // Don't stop iterating
    }
    return neighbors
}

/**
 * Returns a new array representing the vector sum of this and [other].
 *
 * @throws IllegalArgumentException If this and [other] do not have the same size.
 */
fun IntArray.plusVector(other: IntArray): IntArray {
    require(size == other.size) { "Arrays must have the same size: $size != ${other.size}" }
    return IntArray(size) { index -> this[index] + other[index] }
}

/**
 * Returns a new array representing the vector sum of this and [other].
 *
 * @throws IllegalArgumentException If this and [other] do not have the same size.
 */
fun LongArray.plusVector(other: LongArray): LongArray {
    require(size == other.size) { "Arrays must have the same size: $size != ${other.size}" }
    return LongArray(size) { index -> this[index] + other[index] }
}
