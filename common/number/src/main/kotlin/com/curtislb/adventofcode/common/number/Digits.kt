package com.curtislb.adventofcode.common.number

import kotlin.math.floor
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.log2

/**
 * Returns the integer given by concatenating the digits in this iterable with the given [radix].
 *
 * @throws IllegalArgumentException If any number in this iterable is not a digit with the given
 *  [radix].
 */
fun Iterable<Int>.digitsToInt(radix: Int = 10): Int {
    val digitRange = 0 until radix
    return fold(0) { prefix, digit ->
        require(digit in digitRange) { "Invalid digit for radix $radix: $digit" }
        prefix * radix + digit
    }
}

/**
 * Returns the integer given by concatenating the digits in this iterable with the given [radix].
 *
 * @throws IllegalArgumentException If any number in this iterable is not a digit with the given
 *  [radix].
 */
fun Iterable<Int>.digitsToLong(radix: Int = 10): Long {
    val digitRange = 0 until radix
    return fold(0L) { prefix, digit ->
        require(digit in digitRange) { "Invalid digit for radix $radix: $digit" }
        prefix * radix + digit
    }
}

/**
 * Returns the number of digits in this non-negative integer when written with the given [radix].
 *
 * @throws IllegalArgumentException If this number is negative or [radix] is less than 2.
 */
fun Int.countDigits(radix: Int = 10): Int {
    require(this >= 0) { "Number must be non-negative: $this" }
    require(radix >= 2) { "Radix must be at least 2: $radix" }

    // The number zero is written as "0" in all radixes
    if (this == 0) {
        return 1
    }

    // Use specialized log functions for common radixes
    val radixLog = when (radix) {
        2 -> log2(toDouble())
        10 -> log10(toDouble())
        else -> log(toDouble(), radix.toDouble())
    }
    return floor(radixLog).toInt() + 1
}

/**
 * Returns the number of digits in this non-negative integer when written with the given [radix].
 *
 * @throws IllegalArgumentException If this number is negative or [radix] is less than 2.
 */
fun Long.countDigits(radix: Int = 10): Int {
    require(this >= 0L) { "Number must be non-negative: $this" }
    require(radix >= 2) { "Radix must be at least 2: $radix" }

    // The number zero is written as "0" in all radixes
    if (this == 0L) {
        return 1
    }

    // Use specialized log functions for common radixes
    val radixLog = when (radix) {
        2 -> log2(toDouble())
        10 -> log10(toDouble())
        else -> log(toDouble(), radix.toDouble())
    }
    return floor(radixLog).toInt() + 1
}

/**
 * Returns the digit at [position] (from the left) for this non-negative integer when written with
 * the given [radix].
 *
 * The value of [position] must be in the range `0 until n`, where `n` is the number of significant
 * (i.e. not leading zero) digits of this integer with the given [radix].
 *
 * @throws IllegalArgumentException If this number is negative, [position] is negative, [radix] is
 *  less than 2, or there is no digit at [position] for this number with the given [radix].
 */
fun Int.bigEndianDigit(position: Int, radix: Int = 10): Int {
    require(this >= 0) { "Integer must be non-negative: $this" }
    require(position >= 0) { "Position must be non-negative: $position" }
    require(radix >= 2) { "Radix must be at least 2: $radix" }

    // Check if position is valid for this number
    val digitCount = countDigits(radix)
    require(position < digitCount) {
        "Integer $this has no big-endian digit at position $position in radix $radix."
    }

    return littleEndianDigitInternal(digitCount - position - 1, radix)
}

/**
 * Returns the digit at [position] (from the right) for this non-negative integer with the given
 * [radix].
 *
 * If the value of [position] is greater than the number of significant digits with the given
 * [radix], this method will return 0.
 *
 * @throws IllegalArgumentException If this number is negative, [position] is negative, or [radix]
 *  is less than 2.
 */
fun Int.littleEndianDigit(position: Int, radix: Int = 10): Int {
    require(this >= 0) { "Integer must be non-negative: $this" }
    require(position >= 0) { "Position must be non-negative: $position" }
    require(radix >= 2) { "Radix must be at least 2: $radix" }
    return littleEndianDigitInternal(position, radix)
}

/**
 * Returns the digit at [position] (from right) for this non-negative integer when written with the
 * given [radix].
 */
private fun Int.littleEndianDigitInternal(position: Int, radix: Int): Int =
    (this / radix.pow(position)) % radix
