package com.curtislb.adventofcode.common.parse

import java.math.BigInteger

/**
 * TODO
 */
private val INTEGER_REGEX = Regex("""\b([+\-]?\d+)\b""")

/**
 * Returns a list of all decimal integers in this string.
 *
 * Integers must be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE]. If integers in the file may be outside this range,
 * use [toLongs] or [toBigIntegers] instead.
 */
fun String.toInts(): List<Int> = INTEGER_REGEX.findAll(this).map { it.value.toInt() }.toList()

/**
 * Returns a list of all decimal integers in this string.
 *
 * Integers must be in the range [Long.MIN_VALUE]..[Long.MAX_VALUE]. If integers in the file may be outside this range,
 * use [toBigIntegers] instead. If integers are guaranteed to be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE], [toInts]
 * may be used instead.
 */
fun String.toLongs(): List<Long> = INTEGER_REGEX.findAll(this).map { it.value.toLong() }.toList()

/**
 * Returns a list of all decimal integers in this string.
 *
 * Integers may be unbounded and will be stored as [BigInteger] values. If integers in the file are guaranteed to be in
 * the range [Int.MIN_VALUE]..[Int.MAX_VALUE], [toInts] may be used instead. If integers are guaranteed to be in the
 * range [Long.MIN_VALUE]..[Long.MAX_VALUE], [toLongs] may be used instead.
 */
fun String.toBigIntegers(): List<BigInteger> = INTEGER_REGEX.findAll(this).map { it.value.toBigInteger() }.toList()
