package com.curtislb.adventofcode.common.number

/**
 * The maximum possible 1-bit index for a non-negative 32-bit integer.
 */
private const val MAX_INT_BIT_INDEX = 30

/**
 * The maximum possible 1-bit index for a non-negative 64-bit integer.
 */
private const val MAX_LONG_BIT_INDEX = 62

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 0.
 *
 * @throws IllegalArgumentException If this integer is negative, or if [bitIndex] is not in `0..30`.
 */
fun Int.clearBit(bitIndex: Int): Int {
    require(this >= 0) { "Can't clear bit of negative integer: $this" }
    require(bitIndex in 0..MAX_INT_BIT_INDEX) { "Bit index is out of range: $bitIndex" }
    return this and (1 shl bitIndex).inv()
}

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 0.
 *
 * @throws IllegalArgumentException If this integer is negative, or if [bitIndex] is not in `0..62`.
 */
fun Long.clearBit(bitIndex: Int): Long {
    require(this >= 0) { "Can't clear bit of negative integer: $this" }
    require(bitIndex in 0..MAX_LONG_BIT_INDEX) { "Bit index is out of range: $bitIndex" }
    return this and (1L shl bitIndex).inv()
}

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 1.
 *
 * @throws IllegalArgumentException If this integer is negative, or if [bitIndex] is not in `0..30`.
 */
fun Int.setBit(bitIndex: Int): Int {
    require(this >= 0) { "Can't set bit of negative integer: $this" }
    require(bitIndex in 0..MAX_INT_BIT_INDEX) { "Bit index is out of range: $bitIndex" }
    return this or (1 shl bitIndex)
}

/**
 * Returns a copy of this integer with the little-endian binary digit at [bitIndex] set to 1.
 *
 * @throws IllegalArgumentException If this integer is negative, or if [bitIndex] is not in `0..62`.
 */
fun Long.setBit(bitIndex: Int): Long {
    require(this >= 0) { "Can't set bit of negative integer: $this" }
    require(bitIndex in 0..MAX_LONG_BIT_INDEX) { "Bit index is out of range: $bitIndex" }
    return this or (1L shl bitIndex)
}

/**
 * Returns `true` if the little-endian binary digit at [bitIndex] in this integer is 1.
 *
 * @throws IllegalArgumentException If the integer is negative, or if [bitIndex] is negative.
 */
fun Int.testBit(bitIndex: Int): Boolean {
    require(this >= 0) { "Can't test bit of negative integer: $this" }
    require(bitIndex >= 0) { "Bit index must be non-negative: $bitIndex" }
    return bitIndex <= MAX_INT_BIT_INDEX && (this and (1 shl bitIndex)) != 0
}
