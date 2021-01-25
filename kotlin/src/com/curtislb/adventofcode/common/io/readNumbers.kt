package com.curtislb.adventofcode.common.io

import java.io.File
import java.math.BigInteger
import java.nio.charset.Charset

private val DECIMAL_INT_REGEX = Regex("""\b([+\-]?\d+)\b""")

/**
 * Returns a list of all decimal integers in this file, using the specified [charset].
 *
 * Integers must be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE]. If integers in the file may be outside this range,
 * use [readLongs] or [readBigIntegers] instead.
 */
fun File.readInts(charset: Charset = Charsets.UTF_8): List<Int> = readNumbers(charset, String::toInt)

/**
 * Returns a list of all decimal integers in this file, using the specified [charset].
 *
 * Integers must be in the range [Long.MIN_VALUE]..[Long.MAX_VALUE]. If integers in the file may be outside this range,
 * use [readBigIntegers] instead. If integers are guaranteed to be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE],
 * [readInts] may be used instead.
 */
fun File.readLongs(charset: Charset = Charsets.UTF_8): List<Long> = readNumbers(charset, String::toLong)

/**
 * Returns a list of all decimal integers in this file, using the specified [charset].
 *
 * Integers may be unbounded in size and will be stored as [BigInteger] values. If integers in the file are guaranteed
 * to be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE], [readInts] may be used instead. If integers are guaranteed to be
 * in the range [Long.MIN_VALUE]..[Long.MAX_VALUE], [readLongs] may be used instead.
 */
fun File.readBigIntegers(charset: Charset = Charsets.UTF_8): List<BigInteger> {
    return readNumbers(charset, String::toBigInteger)
}

/**
 * Returns a list of all decimal integers in this file, using the specified [charset] and the given function to
 * [convert] strings to their numeric equivalents.
 */
private inline fun <T : Number> File.readNumbers(charset: Charset, crossinline convert: (String) -> T): List<T> {
    return DECIMAL_INT_REGEX.findAll(readText(charset)).map { convert(it.value) }.toList()
}
