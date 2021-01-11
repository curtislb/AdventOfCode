package com.curtislb.adventofcode.common.io

import java.io.File
import java.math.BigInteger
import java.nio.charset.Charset

private val DECIMAL_INT_REGEX = Regex("""\b([+\-]?\d+)\b""")

fun File.readInts(charset: Charset = Charsets.UTF_8): List<Int> = readNumbers(charset, String::toInt)

fun File.readLongs(charset: Charset = Charsets.UTF_8): List<Long> = readNumbers(charset, String::toLong)

fun File.readBigIntegers(charset: Charset = Charsets.UTF_8): List<BigInteger> {
    return readNumbers(charset, String::toBigInteger)
}

private inline fun <T : Number> File.readNumbers(charset: Charset, crossinline convert: (String) -> T): List<T> {
    return DECIMAL_INT_REGEX.findAll(readText(charset)).toList().map { convert(it.value) }
}
