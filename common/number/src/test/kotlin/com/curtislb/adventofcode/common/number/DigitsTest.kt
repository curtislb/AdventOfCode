package com.curtislb.adventofcode.common.number

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions related to numerical digits.
 */
class DigitsTest {
    @Test
    fun digitsToInt_defaultBase_noDigits() {
        val digits = mutableListOf<Int>()
        assertThat(digits.digitsToInt()).isEqualTo(0)
    }

    @Test
    fun digitsToInt_defaultBase_singleZeroDigit() {
        val digits = mutableListOf(0)
        assertThat(digits.digitsToInt()).isEqualTo(0)
    }

    @Test
    fun digitsToInt_defaultBase_singlePositiveDigit() {
        val digits = mutableListOf(3)
        assertThat(digits.digitsToInt()).isEqualTo(3)
    }

    @Test
    fun digitsToInt_defaultBase_singleNegativeDigit() {
        val digits = mutableListOf(-1)
        assertThrows<IllegalArgumentException> { digits.digitsToInt() }
    }

    @Test
    fun digitsToInt_defaultBase_singleTooLargeDigit() {
        val digits = mutableListOf(10)
        assertThrows<IllegalArgumentException> { digits.digitsToInt() }
    }

    @Test
    fun digitsToInt_defaultBase_multipleDigits_allZero() {
        val digits = mutableListOf(0, 0, 0)
        assertThat(digits.digitsToInt()).isEqualTo(0)
    }

    @Test
    fun digitsToInt_defaultBase_multipleDigits_allValid() {
        val digits = mutableListOf(2, 0, 1)
        assertThat(digits.digitsToInt()).isEqualTo(201)
    }

    @Test
    fun digitsToInt_defaultBase_multipleDigits_oneNegative() {
        val digits = mutableListOf(2, -1, 1)
        assertThrows<IllegalArgumentException> { digits.digitsToInt() }
    }

    @Test
    fun digitsToInt_defaultBase_multipleDigits_oneTooLarge() {
        val digits = mutableListOf(2, 0, 10)
        assertThrows<IllegalArgumentException> { digits.digitsToInt() }
    }

    @Test
    fun digitsToInt_nonDefaultBase_noDigits() {
        val digits = mutableListOf<Int>()
        assertThat(digits.digitsToInt(base = 8)).isEqualTo(0)
    }

    @Test
    fun digitsToInt_nonDefaultBase_singleZeroDigit() {
        val digits = mutableListOf(0)
        assertThat(digits.digitsToInt(base = 8)).isEqualTo(0)
    }

    @Test
    fun digitsToInt_nonDefaultBase_singlePositiveDigit() {
        val digits = mutableListOf(3)
        assertThat(digits.digitsToInt(base = 8)).isEqualTo(3)
    }

    @Test
    fun digitsToInt_nonDefaultBase_singleNegativeDigit() {
        val digits = mutableListOf(-1)
        assertThrows<IllegalArgumentException> { digits.digitsToInt(base = 8) }
    }

    @Test
    fun digitsToInt_nonDefaultBase_singleTooLargeDigit() {
        val digits = mutableListOf(8)
        assertThrows<IllegalArgumentException> { digits.digitsToInt(base = 8) }
    }

    @Test
    fun digitsToInt_nonDefaultBase_multipleDigits_allZero() {
        val digits = mutableListOf(0, 0, 0)
        assertThat(digits.digitsToInt(base = 8)).isEqualTo(0)
    }

    @Test
    fun digitsToInt_nonDefaultBase_multipleDigits_allValid() {
        val digits = mutableListOf(2, 0, 1)
        assertThat(digits.digitsToInt(base = 8)).isEqualTo(129)
    }

    @Test
    fun digitsToInt_nonDefaultBase_multipleDigits_oneNegative() {
        val digits = mutableListOf(2, -1, 1)
        assertThrows<IllegalArgumentException> { digits.digitsToInt(base = 8) }
    }

    @Test
    fun digitsToInt_nonDefaultBase_multipleDigits_oneTooLarge() {
        val digits = mutableListOf(2, 0, 8)
        assertThrows<IllegalArgumentException> { digits.digitsToInt(base = 8) }
    }

    @Test
    fun digitsToLong_defaultBase_noDigits() {
        val digits = mutableListOf<Int>()
        assertThat(digits.digitsToLong()).isEqualTo(0L)
    }

    @Test
    fun digitsToLong_defaultBase_singleZeroDigit() {
        val digits = mutableListOf(0)
        assertThat(digits.digitsToLong()).isEqualTo(0L)
    }

    @Test
    fun digitsToLong_defaultBase_singlePositiveDigit() {
        val digits = mutableListOf(3)
        assertThat(digits.digitsToLong()).isEqualTo(3L)
    }

    @Test
    fun digitsToLong_defaultBase_singleNegativeDigit() {
        val digits = mutableListOf(-1)
        assertThrows<IllegalArgumentException> { digits.digitsToLong() }
    }

    @Test
    fun digitsToLong_defaultBase_singleTooLargeDigit() {
        val digits = mutableListOf(10)
        assertThrows<IllegalArgumentException> { digits.digitsToLong() }
    }

    @Test
    fun digitsToLong_defaultBase_multipleDigits_allZero() {
        val digits = mutableListOf(0, 0, 0)
        assertThat(digits.digitsToLong()).isEqualTo(0L)
    }

    @Test
    fun digitsToLong_defaultBase_multipleDigits_allValid() {
        val digits = mutableListOf(2, 0, 1)
        assertThat(digits.digitsToLong()).isEqualTo(201L)
    }

    @Test
    fun digitsToLong_defaultBase_multipleDigits_oneNegative() {
        val digits = mutableListOf(2, -1, 1)
        assertThrows<IllegalArgumentException> { digits.digitsToLong() }
    }

    @Test
    fun digitsToLong_defaultBase_multipleDigits_oneTooLarge() {
        val digits = mutableListOf(2, 0, 10)
        assertThrows<IllegalArgumentException> { digits.digitsToLong() }
    }

    @Test
    fun digitsToLong_nonDefaultBase_noDigits() {
        val digits = mutableListOf<Int>()
        assertThat(digits.digitsToLong(base = 8)).isEqualTo(0L)
    }

    @Test
    fun digitsToLong_nonDefaultBase_singleZeroDigit() {
        val digits = mutableListOf(0)
        assertThat(digits.digitsToLong(base = 8)).isEqualTo(0L)
    }

    @Test
    fun digitsToLong_nonDefaultBase_singlePositiveDigit() {
        val digits = mutableListOf(3)
        assertThat(digits.digitsToLong(base = 8)).isEqualTo(3L)
    }

    @Test
    fun digitsToLong_nonDefaultBase_singleNegativeDigit() {
        val digits = mutableListOf(-1)
        assertThrows<IllegalArgumentException> { digits.digitsToLong(base = 8) }
    }

    @Test
    fun digitsToLong_nonDefaultBase_singleTooLargeDigit() {
        val digits = mutableListOf(8)
        assertThrows<IllegalArgumentException> { digits.digitsToLong(base = 8) }
    }

    @Test
    fun digitsToLong_nonDefaultBase_multipleDigits_allZero() {
        val digits = mutableListOf(0, 0, 0)
        assertThat(digits.digitsToLong(base = 8)).isEqualTo(0L)
    }

    @Test
    fun digitsToLong_nonDefaultBase_multipleDigits_allValid() {
        val digits = mutableListOf(2, 0, 1)
        assertThat(digits.digitsToLong(base = 8)).isEqualTo(129L)
    }

    @Test
    fun digitsToLong_nonDefaultBase_multipleDigits_oneNegative() {
        val digits = mutableListOf(2, -1, 1)
        assertThrows<IllegalArgumentException> { digits.digitsToLong(base = 8) }
    }

    @Test
    fun digitsToLong_nonDefaultBase_multipleDigits_oneTooLarge() {
        val digits = mutableListOf(2, 0, 8)
        assertThrows<IllegalArgumentException> { digits.digitsToLong(base = 8) }
    }

    @Test
    fun countDigits_ofInt_negativeNumber_defaultBase() {
        assertThrows<IllegalArgumentException> { (-1).countDigits() }
    }

    @Test
    fun countDigits_ofInt_negativeNumber_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { (-1).countDigits(base = 2) }
    }

    @Test
    fun countDigits_ofInt_zero_defaultBase() {
        assertThat(0.countDigits()).isEqualTo(1)
    }

    @Test
    fun countDigits_ofInt_zero_nonDefaultBase() {
        assertThat(0.countDigits(base = 2)).isEqualTo(1)
    }

    @Test
    fun countDigits_ofInt_singleDigit_defaultBase() {
        assertThat(2.countDigits()).isEqualTo(1)
    }

    @Test
    fun countDigits_ofInt_singleDigit_nonDefaultBase() {
        assertThat(2.countDigits(base = 8)).isEqualTo(1)
    }

    @Test
    fun countDigits_ofInt_twoDigits_defaultBase() {
        assertThat(10.countDigits()).isEqualTo(2)
    }

    @Test
    fun countDigits_ofInt_twoDigits_nonDefaultBase() {
        assertThat(0b11.countDigits(base = 2)).isEqualTo(2)
    }

    @Test
    fun countDigits_ofInt_multipleDigits_defaultBase() {
        assertThat(81053026850.countDigits()).isEqualTo(11)
    }

    @Test
    fun countDigits_ofInt_multipleDigits_nonDefaultBase() {
        assertThat(81053026850.countDigits(base = 16)).isEqualTo(10)
    }

    @Test
    fun countDigits_ofInt_negativeBase() {
        assertThrows<IllegalArgumentException> { 1.countDigits(base = -1) }
    }

    @Test
    fun countDigits_ofInt_baseZero() {
        assertThrows<IllegalArgumentException> { 1.countDigits(base = 0) }
    }

    @Test
    fun countDigits_ofInt_baseOne() {
        assertThrows<IllegalArgumentException> { 1.countDigits(base = 1) }
    }

    @Test
    fun countDigits_ofLong_negativeNumber_defaultBase() {
        assertThrows<IllegalArgumentException> { (-1L).countDigits() }
    }

    @Test
    fun countDigits_ofLong_negativeNumber_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { (-1L).countDigits(base = 2) }
    }

    @Test
    fun countDigits_ofLong_zero_defaultBase() {
        assertThat(0L.countDigits()).isEqualTo(1)
    }

    @Test
    fun countDigits_ofLong_zero_nonDefaultBase() {
        assertThat(0L.countDigits(base = 2)).isEqualTo(1)
    }

    @Test
    fun countDigits_ofLong_singleDigit_defaultBase() {
        assertThat(2L.countDigits()).isEqualTo(1)
    }

    @Test
    fun countDigits_ofLong_singleDigit_nonDefaultBase() {
        assertThat(2L.countDigits(base = 8)).isEqualTo(1)
    }

    @Test
    fun countDigits_ofLong_twoDigits_defaultBase() {
        assertThat(10L.countDigits()).isEqualTo(2)
    }

    @Test
    fun countDigits_ofLong_twoDigits_nonDefaultBase() {
        assertThat(3L.countDigits(base = 2)).isEqualTo(2)
    }

    @Test
    fun countDigits_ofLong_multipleDigits_defaultBase() {
        assertThat(81053026850.countDigits()).isEqualTo(11)
    }

    @Test
    fun countDigits_ofLong_multipleDigits_nonDefaultBase() {
        assertThat(81053026850L.countDigits(base = 16)).isEqualTo(10)
    }

    @Test
    fun countDigits_ofLong_negativeBase() {
        assertThrows<IllegalArgumentException> { 1L.countDigits(base = -1) }
    }

    @Test
    fun countDigits_ofLong_baseZero() {
        assertThrows<IllegalArgumentException> { 1L.countDigits(base = 0) }
    }

    @Test
    fun countDigits_ofLong_baseOne() {
        assertThrows<IllegalArgumentException> { 1L.countDigits(base = 1) }
    }

    @Test
    fun bigEndianDigit_ofZero_positionZero_defaultBase() {
        assertThat(0.bigEndianDigit(0)).isEqualTo(0)
    }

    @Test
    fun bigEndianDigit_ofZero_positionZero_nonDefaultBase() {
        assertThat(0.bigEndianDigit(0, base = 2)).isEqualTo(0)
    }

    @Test
    fun bigEndianDigit_ofZero_positionOne_defaultBase() {
        assertThrows<IllegalArgumentException> { 0.bigEndianDigit(1) }
    }

    @Test
    fun bigEndianDigit_ofZero_positionOne_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 0.bigEndianDigit(1, base = 2) }
    }

    @Test
    fun bigEndianDigit_ofZero_negativePosition_defaultBase() {
        assertThrows<IllegalArgumentException> { 0.bigEndianDigit(-1) }
    }

    @Test
    fun bigEndianDigit_ofZero_negativePosition_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 0.bigEndianDigit(-1, base = 2) }
    }

    @Test
    fun bigEndianDigit_ofOne_positionZero_defaultBase() {
        assertThat(1.bigEndianDigit(0)).isEqualTo(1)
    }

    @Test
    fun bigEndianDigit_ofOne_positionZero_nonDefaultBase() {
        assertThat(1.bigEndianDigit(0, base = 8)).isEqualTo(1)
    }

    @Test
    fun bigEndianDigit_ofOne_positionOne_defaultBase() {
        assertThrows<IllegalArgumentException> { 1.bigEndianDigit(1) }
    }

    @Test
    fun bigEndianDigit_ofOne_positionOne_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 1.bigEndianDigit(1, base = 8) }
    }

    @Test
    fun bigEndianDigit_ofOne_negativePosition_defaultBase() {
        assertThrows<IllegalArgumentException> { 1.bigEndianDigit(-1) }
    }

    @Test
    fun bigEndianDigit_ofOne_negativePosition_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 1.bigEndianDigit(-1, base = 8) }
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_positionZero_defaultBase() {
        assertThat(859.bigEndianDigit(0)).isEqualTo(8)
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_positionZero_nonDefaultBase() {
        assertThat(859.bigEndianDigit(0, base = 256)).isEqualTo(3)
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_positionOne_defaultBase() {
        assertThat(859.bigEndianDigit(1)).isEqualTo(5)
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_positionOne_nonDefaultBase() {
        assertThat(859.bigEndianDigit(1, base = 256)).isEqualTo(91)
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_positionTwo_defaultBase() {
        assertThat(859.bigEndianDigit(2)).isEqualTo(9)
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_positionTwo_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 859.bigEndianDigit(2, base = 256) }
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_negativePosition_defaultBase() {
        assertThrows<IllegalArgumentException> { 859.bigEndianDigit(-1) }
    }

    @Test
    fun bigEndianDigit_ofMultiDigitNumber_negativePosition_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 859.bigEndianDigit(-1, base = 256) }
    }

    @Test
    fun bigEndianDigit_ofNegativeNumber() {
        assertThrows<IllegalArgumentException> { (-1).bigEndianDigit(0, base = 2) }
    }

    @Test
    fun bigEndianDigit_negativeBase() {
        assertThrows<IllegalArgumentException> { 1.bigEndianDigit(0, base = -1) }
    }

    @Test
    fun bigEndianDigit_baseZero() {
        assertThrows<IllegalArgumentException> { 1.bigEndianDigit(0, base = 0) }
    }

    @Test
    fun bigEndianDigit_baseOne() {
        assertThrows<IllegalArgumentException> { 1.bigEndianDigit(0, base = 1) }
    }

    @Test
    fun littleEndianDigit_ofZero_positionZero_defaultBase() {
        assertThat(0.littleEndianDigit(0)).isEqualTo(0)
    }

    @Test
    fun littleEndianDigit_ofZero_positionZero_nonDefaultBase() {
        assertThat(0.littleEndianDigit(0, base = 2)).isEqualTo(0)
    }

    @Test
    fun littleEndianDigit_ofZero_positionOne_defaultBase() {
        assertThat(0.littleEndianDigit(1)).isEqualTo(0)
    }

    @Test
    fun littleEndianDigit_ofZero_positionOne_nonDefaultBase() {
        assertThat(0.littleEndianDigit(1, base = 2)).isEqualTo(0)
    }

    @Test
    fun littleEndianDigit_ofZero_negativePosition_defaultBase() {
        assertThrows<IllegalArgumentException> { 0.littleEndianDigit(-1) }
    }

    @Test
    fun littleEndianDigit_ofZero_negativePosition_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 0.littleEndianDigit(-1, base = 2) }
    }

    @Test
    fun littleEndianDigit_ofOne_positionZero_defaultBase() {
        assertThat(1.littleEndianDigit(0)).isEqualTo(1)
    }

    @Test
    fun littleEndianDigit_ofOne_positionZero_nonDefaultBase() {
        assertThat(1.littleEndianDigit(0, base = 8)).isEqualTo(1)
    }

    @Test
    fun littleEndianDigit_ofOne_positionOne_defaultBase() {
        assertThat(1.littleEndianDigit(1)).isEqualTo(0)
    }

    @Test
    fun littleEndianDigit_ofOne_positionOne_nonDefaultBase() {
        assertThat(1.littleEndianDigit(1, base = 8)).isEqualTo(0)
    }

    @Test
    fun littleEndianDigit_ofOne_negativePosition_defaultBase() {
        assertThrows<IllegalArgumentException> { 1.littleEndianDigit(-1) }
    }

    @Test
    fun littleEndianDigit_ofOne_negativePosition_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 1.littleEndianDigit(-1, base = 8) }
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_positionZero_defaultBase() {
        assertThat(859.littleEndianDigit(0)).isEqualTo(9)
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_positionZero_nonDefaultBase() {
        assertThat(859.littleEndianDigit(0, base = 256)).isEqualTo(91)
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_positionOne_defaultBase() {
        assertThat(859.littleEndianDigit(1)).isEqualTo(5)
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_positionOne_nonDefaultBase() {
        assertThat(859.littleEndianDigit(1, base = 256)).isEqualTo(3)
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_positionTwo_defaultBase() {
        assertThat(859.littleEndianDigit(2)).isEqualTo(8)
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_positionTwo_nonDefaultBase() {
        assertThat(859.littleEndianDigit(2, base = 256)).isEqualTo(0)
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_negativePosition_defaultBase() {
        assertThrows<IllegalArgumentException> { 859.littleEndianDigit(-1) }
    }

    @Test
    fun littleEndianDigit_ofMultiDigitNumber_negativePosition_nonDefaultBase() {
        assertThrows<IllegalArgumentException> { 859.littleEndianDigit(-1, base = 256) }
    }

    @Test
    fun littleEndianDigit_ofNegativeNumber() {
        assertThrows<IllegalArgumentException> { (-1).littleEndianDigit(0, base = 2) }
    }

    @Test
    fun littleEndianDigit_negativeBase() {
        assertThrows<IllegalArgumentException> { 1.littleEndianDigit(0, base = -1) }
    }

    @Test
    fun littleEndianDigit_baseZero() {
        assertThrows<IllegalArgumentException> { 1.littleEndianDigit(0, base = 0) }
    }

    @Test
    fun littleEndianDigit_baseOne() {
        assertThrows<IllegalArgumentException> { 1.littleEndianDigit(0, base = 1) }
    }
}
