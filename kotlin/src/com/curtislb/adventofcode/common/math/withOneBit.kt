package com.curtislb.adventofcode.common.math

/**
 * Returns a copy of this integer with the bit at the given little-endian [bitIndex] set to 1.
 */
fun Int.withOneBit(bitIndex: Int): Int = this or (1L shl bitIndex).toInt()

/**
 * Returns a copy of this integer with the bit at the given little-endian [bitIndex] set to 1.
 */
fun Long.withOneBit(bitIndex: Int): Long = this or (1L shl bitIndex)
