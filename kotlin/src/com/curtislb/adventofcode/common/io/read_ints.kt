package com.curtislb.adventofcode.common.io

import java.io.File
import java.math.BigInteger
import java.nio.charset.Charset

private val DECIMAL_INT_REGEX = Regex("""\b([+\-]?\d+)\b""")

private inline fun <T : Number> File.readNumbers(charset: Charset, crossinline convert: (String) -> T): Sequence<T> {
    return DECIMAL_INT_REGEX.findAll(readText(charset)).map { convert(it.value) }
}

fun File.readInts(charset: Charset = Charsets.UTF_8): Sequence<Int> = readNumbers(charset, String::toInt)

fun File.readLongs(charset: Charset = Charsets.UTF_8): Sequence<Long> = readNumbers(charset, String::toLong)

fun File.readBigIntegers(charset: Charset = Charsets.UTF_8): Sequence<BigInteger> {
    return readNumbers(charset, String::toBigInteger)
}
