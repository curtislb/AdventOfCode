package com.curtislb.adventofcode.common.io

import com.curtislb.adventofcode.common.parse.toBigIntegers
import com.curtislb.adventofcode.common.parse.toInts
import com.curtislb.adventofcode.common.parse.toLongs
import java.io.File
import java.math.BigInteger
import java.nio.charset.Charset

/**
 * Returns a list of all decimal integers in this file, using the specified [charset].
 *
 * Integers must be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE]. If integers in the file may be outside this range,
 * use [readLongs] or [readBigIntegers] instead.
 */
fun File.readInts(charset: Charset = Charsets.UTF_8): List<Int> = readText(charset).toInts()

/**
 * Returns a list of all decimal integers in this file, using the specified [charset].
 *
 * Integers must be in the range [Long.MIN_VALUE]..[Long.MAX_VALUE]. If integers in the file may be outside this range,
 * use [readBigIntegers] instead. If integers are guaranteed to be in the range [Int.MIN_VALUE]..[Int.MAX_VALUE],
 * [readInts] may be used instead.
 */
fun File.readLongs(charset: Charset = Charsets.UTF_8): List<Long> = readText(charset).toLongs()

/**
 * Returns a list of all decimal integers in this file, using the specified [charset].
 *
 * Integers may be unbounded and will be stored as [BigInteger] values. If integers in the file are guaranteed to be in
 * the range [Int.MIN_VALUE]..[Int.MAX_VALUE], [readInts] may be used instead. If integers are guaranteed to be in the
 * range [Long.MIN_VALUE]..[Long.MAX_VALUE], [readLongs] may be used instead.
 */
fun File.readBigIntegers(charset: Charset = Charsets.UTF_8): List<BigInteger> = readText(charset).toBigIntegers()
