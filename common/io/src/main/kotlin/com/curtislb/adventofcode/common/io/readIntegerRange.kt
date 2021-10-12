package com.curtislb.adventofcode.common.io

import com.curtislb.adventofcode.common.parse.toBigIntegerRange
import com.curtislb.adventofcode.common.parse.toIntRange
import com.curtislb.adventofcode.common.parse.toLongRange
import com.curtislb.adventofcode.common.range.BigIntegerRange
import java.io.File
import java.nio.charset.Charset

/**
 * Returns the contents of this file as an integer range with start and end (inclusive) values.
 *
 * TODO
 */
fun File.readIntRange(charset: Charset = Charsets.UTF_8): IntRange = readText(charset).toIntRange()

/**
 * Returns the contents of this file as an integer range with start and end (inclusive) values.
 *
 * TODO
 */
fun File.readLongRange(charset: Charset = Charsets.UTF_8): LongRange =
    readText(charset).toLongRange()

/**
 * Returns the contents of this file as an integer range with start and end (inclusive) values.
 *
 * TODO
 */
fun File.readBigIntegerRange(charset: Charset = Charsets.UTF_8): BigIntegerRange =
    readText(charset).toBigIntegerRange()
