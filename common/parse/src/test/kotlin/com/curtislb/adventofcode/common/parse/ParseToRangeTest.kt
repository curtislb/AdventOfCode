package com.curtislb.adventofcode.common.parse

import com.curtislb.adventofcode.common.range.BigIntegerRange
import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions for parsing an integer range from a string.
 */
class ParseToRangeTest {
    @Test
    fun toIntRange_dashSeparator_noSigns() {
        val string = "1-55"
        assertThat(string.toIntRange()).isEqualTo(1..55)
    }

    @Test
    fun toIntRange_dashSeparator_withPositiveSigns() {
        val string = "+2-+30"
        assertThat(string.toIntRange()).isEqualTo(2..30)
    }

    @Test
    fun toIntRange_dashSeparator_withNegativeSigns() {
        val string = "-4--2"
        assertThat(string.toIntRange()).isEqualTo(-4..-2)
    }

    @Test
    fun toIntRange_dashSeparator_withMixedSigns() {
        val string = "-16-+12"
        assertThat(string.toIntRange()).isEqualTo(-16..12)
    }

    @Test
    fun toIntRange_dashSeparator_emptyRange() {
        val string = "1-0"
        assertThat(string.toIntRange()).isEmpty()
    }

    @Test
    fun toIntRange_dotsSeparator_noSigns() {
        val string = "1..55"
        assertThat(string.toIntRange()).isEqualTo(1..55)
    }

    @Test
    fun toIntRange_dotsSeparator_withPositiveSigns() {
        val string = "+2..+30"
        assertThat(string.toIntRange()).isEqualTo(2..30)
    }

    @Test
    fun toIntRange_dotsSeparator_withNegativeSigns() {
        val string = "-4..-2"
        assertThat(string.toIntRange()).isEqualTo(-4..-2)
    }

    @Test
    fun toIntRange_dotsSeparator_withMixedSigns() {
        val string = "-16..+12"
        assertThat(string.toIntRange()).isEqualTo(-16..12)
    }

    @Test
    fun toIntRange_dotsSeparator_emptyRange() {
        val string = "1..0"
        assertThat(string.toIntRange()).isEmpty()
    }

    @Test
    fun toIntRange_invalidFormat() {
        val string = "0->1"
        assertThrows<IllegalArgumentException> { string.toIntRange() }
    }
    
    @Test
    fun toLongRange_dashSeparator_noSigns() {
        val string = "1-4410859453"
        assertThat(string.toLongRange()).isEqualTo(1L..4410859453L)
    }

    @Test
    fun toLongRange_dashSeparator_withPositiveSigns() {
        val string = "+2-+30"
        assertThat(string.toLongRange()).isEqualTo(2L..30L)
    }

    @Test
    fun toLongRange_dashSeparator_withNegativeSigns() {
        val string = "-3199479251--2"
        assertThat(string.toLongRange()).isEqualTo(-3199479251L..-2L)
    }

    @Test
    fun toLongRange_dashSeparator_withMixedSigns() {
        val string = "-16-+12"
        assertThat(string.toLongRange()).isEqualTo(-16L..12L)
    }

    @Test
    fun toLongRange_dashSeparator_emptyRange() {
        val string = "1-0"
        assertThat(string.toLongRange()).isEmpty()
    }

    @Test
    fun toLongRange_dotsSeparator_noSigns() {
        val string = "1..4410859453"
        assertThat(string.toLongRange()).isEqualTo(1L..4410859453L)
    }

    @Test
    fun toLongRange_dotsSeparator_withPositiveSigns() {
        val string = "+2..+30"
        assertThat(string.toLongRange()).isEqualTo(2L..30L)
    }

    @Test
    fun toLongRange_dotsSeparator_withNegativeSigns() {
        val string = "-3199479251..-2"
        assertThat(string.toLongRange()).isEqualTo(-3199479251L..-2L)
    }

    @Test
    fun toLongRange_dotsSeparator_withMixedSigns() {
        val string = "-16..+12"
        assertThat(string.toLongRange()).isEqualTo(-16L..12L)
    }

    @Test
    fun toLongRange_dotsSeparator_emptyRange() {
        val string = "1..0"
        assertThat(string.toLongRange()).isEmpty()
    }

    @Test
    fun toLongRange_invalidFormat() {
        val string = "0->1"
        assertThrows<IllegalArgumentException> { string.toLongRange() }
    }

    @Test
    fun toBigIntegerRange_dashSeparator_noSigns() {
        val string = "1-436984209767778010090"
        assertThat(string.toBigIntegerRange()).isEqualTo(
            BigIntegerRange(BigInteger.ONE, BigInteger("436984209767778010090"))
        )
    }

    @Test
    fun toBigIntegerRange_dashSeparator_withPositiveSigns() {
        val string = "+2-+30"
        assertThat(string.toBigIntegerRange()).isEqualTo(BigIntegerRange(2, 30))
    }

    @Test
    fun toBigIntegerRange_dashSeparator_withNegativeSigns() {
        val string = "-471146431478090715875--2"
        assertThat(string.toBigIntegerRange()).isEqualTo(
            BigIntegerRange(BigInteger("-471146431478090715875"), BigInteger.valueOf(-2))
        )
    }

    @Test
    fun toBigIntegerRange_dashSeparator_withMixedSigns() {
        val string = "-16-+12"
        assertThat(string.toBigIntegerRange()).isEqualTo(BigIntegerRange(-16, 12))
    }

    @Test
    fun toBigIntegerRange_dashSeparator_emptyRange() {
        val string = "1-0"
        assertThat(string.toBigIntegerRange()).isEmpty()
    }

    @Test
    fun toBigIntegerRange_dotsSeparator_noSigns() {
        val string = "1..436984209767778010090"
        assertThat(string.toBigIntegerRange()).isEqualTo(
            BigIntegerRange(BigInteger.ONE, BigInteger("436984209767778010090"))
        )
    }

    @Test
    fun toBigIntegerRange_dotsSeparator_withPositiveSigns() {
        val string = "+2..+30"
        assertThat(string.toBigIntegerRange()).isEqualTo(BigIntegerRange(2, 30))
    }

    @Test
    fun toBigIntegerRange_dotsSeparator_withNegativeSigns() {
        val string = "-471146431478090715875..-2"
        assertThat(string.toBigIntegerRange()).isEqualTo(
            BigIntegerRange(BigInteger("-471146431478090715875"), BigInteger.valueOf(-2))
        )
    }

    @Test
    fun toBigIntegerRange_dotsSeparator_withMixedSigns() {
        val string = "-16..+12"
        assertThat(string.toBigIntegerRange()).isEqualTo(BigIntegerRange(-16, 12))
    }

    @Test
    fun toBigIntegerRange_dotsSeparator_emptyRange() {
        val string = "1..0"
        assertThat(string.toBigIntegerRange()).isEmpty()
    }

    @Test
    fun toBigIntegerRange_invalidFormat() {
        val string = "0->1"
        assertThrows<IllegalArgumentException> { string.toBigIntegerRange() }
    }
}
