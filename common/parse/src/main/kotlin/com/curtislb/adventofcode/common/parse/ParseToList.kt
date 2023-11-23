package com.curtislb.adventofcode.common.parse

import java.math.BigInteger

/**
 * Regex that matches a single decimal integer value.
 */
private val INTEGER_REGEX = Regex("""-?\d+""")

/**
 * Returns a list of all decimal integers in this string as [Int] values.
 *
 * If integers in the file may be outside the range [Int.MIN_VALUE]..[Int.MAX_VALUE], use
 * [parseLongs] or [parseBigIntegers] instead.
 */
fun String.parseInts(): List<Int> = INTEGER_REGEX.findAll(this).map { it.value.toInt() }.toList()

/**
 * Returns a list of all decimal integers in this string as [Long] values.
 *
 * If integers in the file may be outside the range [Long.MIN_VALUE]..[Long.MAX_VALUE], use
 * [parseBigIntegers] instead. If integers are guaranteed to be in the range
 * [Int.MIN_VALUE]..[Int.MAX_VALUE], [parseInts] may be used instead.
 */
fun String.parseLongs(): List<Long> = INTEGER_REGEX.findAll(this).map { it.value.toLong() }.toList()

/**
 * Returns a list of all decimal integers in this string as [BigInteger] values.
 *
 * If integers in the file are guaranteed to be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE],
 * [parseInts] may be used instead. If integers are guaranteed to be in the range
 * [Long.MIN_VALUE]..[Long.MAX_VALUE], [parseLongs]  may be used instead.
 */
fun String.parseBigIntegers(): List<BigInteger> =
    INTEGER_REGEX.findAll(this).map { it.value.toBigInteger() }.toList()
