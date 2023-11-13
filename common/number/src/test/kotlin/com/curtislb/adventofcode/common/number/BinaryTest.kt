package com.curtislb.adventofcode.common.number

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions related to binary numbers.
 */
class BinaryTest {
    @Test
    fun clearBit_negativeInt() {
        assertThrows<IllegalArgumentException> { (-1).clearBit(0) }
    }

    @Test
    fun clearBit_zeroInt_negativeIndex() {
        assertThrows<IllegalArgumentException> { 0.clearBit(-1) }
    }

    @Test
    fun clearBit_zeroInt_zeroIndex() {
        assertThat(0.clearBit(0)).isEqualTo(0)
    }

    @Test
    fun clearBit_zeroInt_positiveIndex_inRange() {
        assertThat(0.clearBit(30)).isEqualTo(0)
    }

    @Test
    fun clearBit_zeroInt_positiveIndex_outOfRange() {
        assertThrows<IllegalArgumentException> { 0.clearBit(31) }
    }

    @Test
    fun clearBit_positiveInt_negativeIndex() {
        assertThrows<IllegalArgumentException> { 42.clearBit(-1) }
    }

    @Test
    fun clearBit_positiveInt_indexOfZeroBit() {
        assertThat(42.clearBit(2)).isEqualTo(42)
    }

    @Test
    fun clearBit_positiveInt_indexOfOneBit() {
        assertThat(42.clearBit(3)).isEqualTo(34)
    }

    @Test
    fun clearBit_positiveInt_tooLargeIndex() {
        assertThrows<IllegalArgumentException> { 42.clearBit(32) }
    }

    @Test
    fun clearBit_negativeLong() {
        assertThrows<IllegalArgumentException> { (-1L).clearBit(0) }
    }

    @Test
    fun clearBit_zeroLong_negativeIndex() {
        assertThrows<IllegalArgumentException> { 0L.clearBit(-1) }
    }

    @Test
    fun clearBit_zeroLong_zeroIndex() {
        assertThat(0L.clearBit(0)).isEqualTo(0L)
    }

    @Test
    fun clearBit_zeroLong_positiveIndex_inRange() {
        assertThat(0L.clearBit(62)).isEqualTo(0L)
    }

    @Test
    fun clearBit_zeroLong_positiveIndex_outOfRange() {
        assertThrows<IllegalArgumentException> { 0L.clearBit(63) }
    }

    @Test
    fun clearBit_positiveLong_negativeIndex() {
        assertThrows<IllegalArgumentException> { 42L.clearBit(-1) }
    }

    @Test
    fun clearBit_positiveLong_indexOfZeroBit() {
        assertThat(42L.clearBit(2)).isEqualTo(42L)
    }

    @Test
    fun clearBit_positiveLong_indexOfOneBit() {
        assertThat(42L.clearBit(3)).isEqualTo(34L)
    }

    @Test
    fun clearBit_positiveLong_tooLargeIndex() {
        assertThrows<IllegalArgumentException> { 42L.clearBit(64) }
    }

    @Test
    fun setBit_negativeInt() {
        assertThrows<IllegalArgumentException> { (-1).setBit(0) }
    }

    @Test
    fun setBit_zeroInt_negativeIndex() {
        assertThrows<IllegalArgumentException> { 0.setBit(-1) }
    }

    @Test
    fun setBit_zeroInt_zeroIndex() {
        assertThat(0.setBit(0)).isEqualTo(1)
    }

    @Test
    fun setBit_zeroInt_positiveIndex_inRange() {
        assertThat(0.setBit(30)).isEqualTo(1_073_741_824)
    }

    @Test
    fun setBit_zeroInt_positiveIndex_outOfRange() {
        assertThrows<IllegalArgumentException> { 0.setBit(31) }
    }

    @Test
    fun setBit_positiveInt_negativeIndex() {
        assertThrows<IllegalArgumentException> { 42.setBit(-1) }
    }

    @Test
    fun setBit_positiveInt_indexOfZeroBit() {
        assertThat(42.setBit(2)).isEqualTo(46)
    }

    @Test
    fun setBit_positiveInt_indexOfOneBit() {
        assertThat(42.setBit(3)).isEqualTo(42)
    }

    @Test
    fun setBit_positiveInt_tooLargeIndex() {
        assertThrows<IllegalArgumentException> { 42.setBit(32) }
    }

    @Test
    fun setBit_negativeLong() {
        assertThrows<IllegalArgumentException> { (-1L).setBit(0) }
    }

    @Test
    fun setBit_zeroLong_negativeIndex() {
        assertThrows<IllegalArgumentException> { 0L.setBit(-1) }
    }

    @Test
    fun setBit_zeroLong_zeroIndex() {
        assertThat(0L.setBit(0)).isEqualTo(1L)
    }

    @Test
    fun setBit_zeroLong_positiveIndex_inRange() {
        assertThat(0L.setBit(62)).isEqualTo(4_611_686_018_427_387_904L)
    }

    @Test
    fun setBit_zeroLong_positiveIndex_outOfRange() {
        assertThrows<IllegalArgumentException> { 0L.setBit(63) }
    }

    @Test
    fun setBit_positiveLong_negativeIndex() {
        assertThrows<IllegalArgumentException> { 42L.setBit(-1) }
    }

    @Test
    fun setBit_positiveLong_indexOfZeroBit() {
        assertThat(42L.setBit(2)).isEqualTo(46L)
    }

    @Test
    fun setBit_positiveLong_indexOfOneBit() {
        assertThat(42L.setBit(3)).isEqualTo(42L)
    }

    @Test
    fun setBit_positiveLong_tooLargeIndex() {
        assertThrows<IllegalArgumentException> { 42L.setBit(64) }
    }

    @Test
    fun testBit_negativeInt() {
        assertThrows<IllegalArgumentException> { (-1).testBit(0) }
    }

    @Test
    fun testBit_zeroInt_negativeIndex() {
        assertThrows<IllegalArgumentException> { 0.testBit(-1) }
    }

    @Test
    fun testBit_zeroInt_zeroIndex() {
        assertThat(0.testBit(0)).isFalse()
    }

    @Test
    fun testBit_zeroInt_positiveIndex_in32BitRange() {
        assertThat(0.testBit(23)).isFalse()
    }

    @Test
    fun testBit_zeroInt_positiveIndex_outOf32BitRange() {
        assertThat(0.testBit(42)).isFalse()
    }

    @Test
    fun testBit_positiveInt_negativeIndex() {
        assertThrows<IllegalArgumentException> { 42.testBit(-1) }
    }

    @Test
    fun testBit_positiveInt_indexOfZeroBit() {
        assertThat(42.testBit(2)).isFalse()
    }

    @Test
    fun testBit_positiveInt_indexOfOneBit() {
        assertThat(42.testBit(3)).isTrue()
    }

    @Test
    fun testBit_positiveInt_indexOutOf32BitRange() {
        assertThat(42.testBit(33)).isFalse()
    }
}
