package com.curtislb.adventofcode.common.math

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 0.
 *
 * @throws IllegalArgumentException If [bitIndex] is outside the range `0..31`.
 */
fun Int.clearBit(bitIndex: Int): Int {
    require(bitIndex in 0..31) { "Bit index must be in range 0..31: $bitIndex" }
    return this and (1 shl bitIndex).inv()
}

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 0.
 *
 * @throws IllegalArgumentException If [bitIndex] is outside the range `0..63`.
 */
fun Long.clearBit(bitIndex: Int): Long {
    require(bitIndex in 0..63) { "Bit index must be in range 0..63: $bitIndex" }
    return this and (1L shl bitIndex).inv()
}

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 1.
 *
 * @throws IllegalArgumentException If [bitIndex] is outside the range `0..31`.
 */
fun Int.setBit(bitIndex: Int): Int {
    require(bitIndex in 0..31) { "Bit index must be in range 0..31: $bitIndex" }
    return this or (1 shl bitIndex)
}

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 1.
 *
 * @throws IllegalArgumentException If [bitIndex] is outside the range `0..63`.
 */
fun Long.setBit(bitIndex: Int): Long {
    require(bitIndex in 0..63) { "Bit index must be in range 0..63: $bitIndex" }
    return this or (1L shl bitIndex)
}

/**
 * Checks if the little-endian binary digit at [bitIndex] in this integer is 1.
 */
fun Int.testBit(bitIndex: Int): Boolean {
    require(bitIndex in 0..31) { "Bit index must be in range 0..31: $bitIndex" }
    return this and (1 shl bitIndex) != 0
}
