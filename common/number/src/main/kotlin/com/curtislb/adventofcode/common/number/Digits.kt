package com.curtislb.adventofcode.common.number

import kotlin.math.floor
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.log2

/**
 * A list of lowercase English words, representing the decimal digits from 0 to 9.
 */
val DIGIT_WORDS = listOf(
    "zero",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine"
)

/**
 * Returns the numeric value of the decimal digit represented by this lowercase English word string.
 *
 * @throws IllegalArgumentException If the string does not correspond to any decimal digit.
 */
fun String.digitWordToInt(): Int {
    val digit = DIGIT_WORDS.indexOf(this)
    require(digit != -1) { "Invalid digit word: $this" }
    return digit
}

/**
 * Returns `true` if this string represents a single decimal digit.
 */
fun String.isDigit(): Boolean = length == 1 && this[0] in '0'..'9'

/**
 * Returns the integer given by concatenating the digits in this iterable with the given [base].
 *
 * @throws IllegalArgumentException If any number in this iterable is not a digit with the given
 *  [base].
 */
fun Iterable<Int>.digitsToInt(base: Int = 10): Int {
    val digitRange = 0 until base
    return fold(0) { prefix, digit ->
        require(digit in digitRange) { "Invalid digit for base $base: $digit" }
        prefix * base + digit
    }
}

/**
 * Returns the integer given by concatenating the digits in this iterable with the given [base].
 *
 * @throws IllegalArgumentException If any number in this iterable is not a digit with the given
 *  [base].
 */
fun Iterable<Int>.digitsToLong(base: Int = 10): Long {
    val digitRange = 0 until base
    return fold(0L) { prefix, digit ->
        require(digit in digitRange) { "Invalid digit for base $base: $digit" }
        prefix * base + digit
    }
}

/**
 * Returns the number of digits in this non-negative integer when written with the given [base].
 *
 * @throws IllegalArgumentException If this number is negative or [base] is less than 2.
 */
fun Int.countDigits(base: Int = 10): Int {
    require(this >= 0) { "Number must be non-negative: $this" }
    require(base >= 2) { "Base must be at least 2: $base" }

    // The number zero is written as "0" in all bases
    if (this == 0) {
        return 1
    }

    // Use specialized log functions for common bases
    val baseLog = when (base) {
        2 -> log2(toDouble())
        10 -> log10(toDouble())
        else -> log(toDouble(), base.toDouble())
    }
    return floor(baseLog).toInt() + 1
}

/**
 * Returns the number of digits in this non-negative integer when written with the given [base].
 *
 * @throws IllegalArgumentException If this number is negative or [base] is less than 2.
 */
fun Long.countDigits(base: Int = 10): Int {
    require(this >= 0L) { "Number must be non-negative: $this" }
    require(base >= 2) { "Base must be at least 2: $base" }

    // The number zero is written as "0" in all bases
    if (this == 0L) {
        return 1
    }

    // Use specialized log functions for common bases
    val baseLog = when (base) {
        2 -> log2(toDouble())
        10 -> log10(toDouble())
        else -> log(toDouble(), base.toDouble())
    }
    return floor(baseLog).toInt() + 1
}

/**
 * Returns the digit at [position] (from the left) for this non-negative integer when written with
 * the given [base].
 *
 * The value of [position] must be in the range `0 until n`, where `n` is the number of significant
 * (i.e. not leading zero) digits of this integer with the given [base].
 *
 * @throws IllegalArgumentException If this number is negative, [position] is negative, [base] is
 *  less than 2, or there is no digit at [position] for this number with the given [base].
 */
fun Int.bigEndianDigit(position: Int, base: Int = 10): Int {
    require(this >= 0) { "Integer must be non-negative: $this" }
    require(position >= 0) { "Position must be non-negative: $position" }
    require(base >= 2) { "Base must be at least 2: $base" }

    // Check if position is valid for this number
    val digitCount = countDigits(base)
    require(position < digitCount) {
        "Integer $this has no big-endian digit at position $position in base $base"
    }

    return littleEndianDigitInternal(digitCount - position - 1, base)
}

/**
 * Returns the digit at [position] (from the right) for this non-negative integer with the given
 * [base].
 *
 * If the value of [position] is greater than the number of significant digits with the given
 * [base], this method will return 0.
 *
 * @throws IllegalArgumentException If this number is negative, [position] is negative, or [base]
 *  is less than 2.
 */
fun Int.littleEndianDigit(position: Int, base: Int = 10): Int {
    require(this >= 0) { "Integer must be non-negative: $this" }
    require(position >= 0) { "Position must be non-negative: $position" }
    require(base >= 2) { "Base must be at least 2: $base" }
    return littleEndianDigitInternal(position, base)
}

/**
 * Returns the digit at [position] (from the right) for this non-negative integer when written with
 * the given [base].
 */
private fun Int.littleEndianDigitInternal(position: Int, base: Int): Int =
    (this / base.pow(position)) % base
