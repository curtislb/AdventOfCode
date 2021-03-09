package com.curtislb.adventofcode.common.math

/**
 * Returns a copy of this integer with the bit at the given little-endian [bitIndex] set to 0.
 */
fun Int.withZeroBit(bitIndex: Int): Int = this and (1L shl bitIndex).toInt().inv()

/**
 * Returns a copy of this integer with the bit at the given little-endian [bitIndex] set to 0.
 */
fun Long.withZeroBit(bitIndex: Int): Long = this and (1L shl bitIndex).inv()
