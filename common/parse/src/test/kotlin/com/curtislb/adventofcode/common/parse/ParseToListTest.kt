package com.curtislb.adventofcode.common.parse

import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests functions for parsing a list of values from a string.
 */
class ParseToListTest {
    @Test
    fun parseInts_emptyString() {
        val string = ""
        assertThat(string.parseInts()).isEmpty()
    }

    @Test
    fun parseInts_positiveNumberOnly() {
        val string = "123"
        assertThat(string.parseInts()).containsExactly(123)
    }

    @Test
    fun parseInts_negativeNumberOnly() {
        val string = "-42"
        assertThat(string.parseInts()).containsExactly(-42)
    }

    @Test
    fun parseInts_positiveNumberWithPadding() {
        val string = " 123  "
        assertThat(string.parseInts()).containsExactly(123)
    }

    @Test
    fun parseInts_negativeNumberWithPadding() {
        val string = "  -42 "
        assertThat(string.parseInts()).containsExactly(-42)
    }

    @Test
    fun parseInts_positiveNumberWithJunk() {
        val string = ":{Ia,l*>sP {*123S,-yy /ES"
        assertThat(string.parseInts()).containsExactly(123)
    }

    @Test
    fun parseInts_negativeNumberWithJunk() {
        val string = "%QaJ-_ \$-42k bzNO!I~"
        assertThat(string.parseInts()).containsExactly(-42)
    }

    @Test
    fun parseInts_withCommaSeparator() {
        val string = "-43,35,12,-5,-51"
        assertThat(string.parseInts()).containsExactly(-43, 35, 12, -5, -51)
    }

    @Test
    fun parseInts_withSpaceSeparator() {
        val string = "-43 35 12 -5 -51"
        assertThat(string.parseInts()).containsExactly(-43, 35, 12, -5, -51)
    }

    @Test
    fun parseInts_withMixedSeparators() {
        val string = "-43,35 12, -5\t-51"
        assertThat(string.parseInts()).containsExactly(-43, 35, 12, -5, -51)
    }

    @Test
    fun parseInts_withMultipleLines() {
        val string = """
            -85
            31 70 65
            -62 71
        """.trimIndent()
        assertThat(string.parseInts()).containsExactly(-85, 31, 70, 65, -62, 71)
    }

    @Test
    fun parseLongs_emptyString() {
        val string = ""
        assertThat(string.parseLongs()).isEmpty()
    }

    @Test
    fun parseLongs_positiveNumberOnly() {
        val string = "9620101055"
        assertThat(string.parseLongs()).containsExactly(9620101055L)
    }

    @Test
    fun parseLongs_negativeNumberOnly() {
        val string = "-4283223259"
        assertThat(string.parseLongs()).containsExactly(-4283223259L)
    }

    @Test
    fun parseLongs_positiveNumberWithPadding() {
        val string = " 9620101055  "
        assertThat(string.parseLongs()).containsExactly(9620101055L)
    }

    @Test
    fun parseLongs_negativeNumberWithPadding() {
        val string = "  -4283223259 "
        assertThat(string.parseLongs()).containsExactly(-4283223259L)
    }

    @Test
    fun parseLongs_positiveNumberWithJunk() {
        val string = ":{Ia,l*>sP {*123S,-yy /ES"
        assertThat(string.parseLongs()).containsExactly(123L)
    }

    @Test
    fun parseLongs_negativeNumberWithJunk() {
        val string = "%QaJ-_ \$-42k bzNO!I~"
        assertThat(string.parseLongs()).containsExactly(-42L)
    }

    @Test
    fun parseLongs_withCommaSeparator() {
        val string = "-43,35,12,-5,-51"
        assertThat(string.parseLongs()).containsExactly(-43L, 35L, 12L, -5L, -51L)
    }

    @Test
    fun parseLongs_withSpaceSeparator() {
        val string = "-43 35 12 -5 -51"
        assertThat(string.parseLongs()).containsExactly(-43L, 35L, 12L, -5L, -51L)
    }

    @Test
    fun parseLongs_withMixedSeparators() {
        val string = "-43,35 12, -5\t-51"
        assertThat(string.parseLongs()).containsExactly(-43L, 35L, 12L, -5L, -51L)
    }

    @Test
    fun parseLongs_withMultipleLines() {
        val string = """
            -85
            31 70 65
            -62 71
        """.trimIndent()
        assertThat(string.parseLongs()).containsExactly(-85L, 31L, 70L, 65L, -62L, 71L)
    }

    @Test
    fun parseBigIntegers_emptyString() {
        val string = ""
        assertThat(string.parseBigIntegers()).isEmpty()
    }

    @Test
    fun parseBigIntegers_positiveNumberOnly() {
        val string = "71448012996193583636"
        assertThat(string.parseBigIntegers()).containsExactly(BigInteger("71448012996193583636"))
    }

    @Test
    fun parseBigIntegers_negativeNumberOnly() {
        val string = "-34778463088629767295"
        assertThat(string.parseBigIntegers()).containsExactly(BigInteger("-34778463088629767295"))
    }

    @Test
    fun parseBigIntegers_positiveNumberWithPadding() {
        val string = " 71448012996193583636  "
        assertThat(string.parseBigIntegers()).containsExactly(BigInteger("71448012996193583636"))
    }

    @Test
    fun parseBigIntegers_negativeNumberWithPadding() {
        val string = "  -34778463088629767295 "
        assertThat(string.parseBigIntegers()).containsExactly(BigInteger("-34778463088629767295"))
    }

    @Test
    fun parseBigIntegers_positiveNumberWithJunk() {
        val string = ":{Ia,l*>sP {*123S,-yy /ES"
        assertThat(string.parseBigIntegers()).containsExactly(BigInteger.valueOf(123))
    }

    @Test
    fun parseBigIntegers_negativeNumberWithJunk() {
        val string = "%QaJ-_ \$-42k bzNO!I~"
        assertThat(string.parseBigIntegers()).containsExactly(BigInteger.valueOf(-42))
    }

    @Test
    fun parseBigIntegers_withCommaSeparator() {
        val string = "-43,35,12,-5,-51"
        assertThat(string.parseBigIntegers()).containsExactly(
            BigInteger.valueOf(-43),
            BigInteger.valueOf(35),
            BigInteger.valueOf(12),
            BigInteger.valueOf(-5),
            BigInteger.valueOf(-51)
        )
    }

    @Test
    fun parseBigIntegers_withSpaceSeparator() {
        val string = "-43 35 12 -5 -51"
        assertThat(string.parseBigIntegers()).containsExactly(
            BigInteger.valueOf(-43),
            BigInteger.valueOf(35),
            BigInteger.valueOf(12),
            BigInteger.valueOf(-5),
            BigInteger.valueOf(-51)
        )
    }

    @Test
    fun parseBigIntegers_withMixedSeparators() {
        val string = "-43,35 12, -5\t-51"
        assertThat(string.parseBigIntegers()).containsExactly(
            BigInteger.valueOf(-43),
            BigInteger.valueOf(35),
            BigInteger.valueOf(12),
            BigInteger.valueOf(-5),
            BigInteger.valueOf(-51)
        )
    }

    @Test
    fun parseBigIntegers_withMultipleLines() {
        val string = """
            -85
            31 70 65
            -62 71
        """.trimIndent()
        assertThat(string.parseBigIntegers()).containsExactly(
            BigInteger.valueOf(-85),
            BigInteger.valueOf(31),
            BigInteger.valueOf(70),
            BigInteger.valueOf(65),
            BigInteger.valueOf(-62),
            BigInteger.valueOf(71)
        )
    }
}
