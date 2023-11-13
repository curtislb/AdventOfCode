package com.curtislb.adventofcode.common.number

import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests functions related to numerical sequences.
 */
class SequencesTest {
    @Test
    fun triangleNumber_ofInt_nEqualsZero() {
        assertThat(triangleNumber(0)).isEqualTo(0)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsOne() {
        assertThat(triangleNumber(1)).isEqualTo(1)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsTwo() {
        assertThat(triangleNumber(2)).isEqualTo(3)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsThree() {
        assertThat(triangleNumber(3)).isEqualTo(6)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsFour() {
        assertThat(triangleNumber(4)).isEqualTo(10)
    }

    @Test
    fun triangleNumber_ofInt_nIsLargePositiveNumber() {
        assertThat(triangleNumber(614)).isEqualTo(188805)
    }

    @Test
    fun triangleNumber_ofInt_nIsMaxPossibleNumber() {
        assertThat(triangleNumber(65535)).isEqualTo(2147450880)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsNegativeOne() {
        assertThat(triangleNumber(-1)).isEqualTo(0)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsNegativeTwo() {
        assertThat(triangleNumber(-2)).isEqualTo(1)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsNegativeThree() {
        assertThat(triangleNumber(-3)).isEqualTo(3)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsNegativeFour() {
        assertThat(triangleNumber(-4)).isEqualTo(6)
    }

    @Test
    fun triangleNumber_ofInt_nEqualsNegativeFive() {
        assertThat(triangleNumber(-5)).isEqualTo(10)
    }

    @Test
    fun triangleNumber_ofInt_nIsLargeNegativeNumber() {
        assertThat(triangleNumber(-409)).isEqualTo(83436)
    }

    @Test
    fun triangleNumber_ofInt_nIsMinPossibleNumber() {
        assertThat(triangleNumber(-65536)).isEqualTo(2147450880)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsZero() {
        assertThat(triangleNumber(0L)).isEqualTo(0L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsOne() {
        assertThat(triangleNumber(1L)).isEqualTo(1L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsTwo() {
        assertThat(triangleNumber(2L)).isEqualTo(3L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsThree() {
        assertThat(triangleNumber(3L)).isEqualTo(6L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsFour() {
        assertThat(triangleNumber(4L)).isEqualTo(10L)
    }

    @Test
    fun triangleNumber_ofLong_nIsLargePositiveNumber() {
        assertThat(triangleNumber(22444L)).isEqualTo(251877790L)
    }

    @Test
    fun triangleNumber_ofLong_nIsMaxPossibleNumber() {
        assertThat(triangleNumber(4294967295L)).isEqualTo(9223372034707292160L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsNegativeOne() {
        assertThat(triangleNumber(-1L)).isEqualTo(0L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsNegativeTwo() {
        assertThat(triangleNumber(-2L)).isEqualTo(1L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsNegativeThree() {
        assertThat(triangleNumber(-3L)).isEqualTo(3L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsNegativeFour() {
        assertThat(triangleNumber(-4L)).isEqualTo(6L)
    }

    @Test
    fun triangleNumber_ofLong_nEqualsNegativeFive() {
        assertThat(triangleNumber(-5L)).isEqualTo(10L)
    }

    @Test
    fun triangleNumber_ofLong_nIsLargeNegativeNumber() {
        assertThat(triangleNumber(-58485L)).isEqualTo(1710218370L)
    }

    @Test
    fun triangleNumber_ofLong_nIsMinPossibleNumber() {
        assertThat(triangleNumber(-4294967296L)).isEqualTo(9223372034707292160L)
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsZero() {
        assertThat(triangleNumber(BigInteger.ZERO)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsOne() {
        assertThat(triangleNumber(BigInteger.ONE)).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsTwo() {
        assertThat(triangleNumber(BigInteger.TWO)).isEqualTo(BigInteger.valueOf(3))
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsThree() {
        assertThat(triangleNumber(BigInteger.valueOf(3))).isEqualTo(BigInteger.valueOf(6))
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsFour() {
        assertThat(triangleNumber(BigInteger.valueOf(4))).isEqualTo(BigInteger.valueOf(10))
    }

    @Test
    fun triangleNumber_ofBigInteger_nIsLargePositiveNumber() {
        assertThat(triangleNumber(BigInteger.valueOf(224771112251L)))
            .isEqualTo(BigInteger("25261026451388206699626"))
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsNegativeOne() {
        assertThat(triangleNumber(BigInteger.valueOf(-1))).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsNegativeTwo() {
        assertThat(triangleNumber(BigInteger.valueOf(-2))).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsNegativeThree() {
        assertThat(triangleNumber(BigInteger.valueOf(-3))).isEqualTo(BigInteger.valueOf(3))
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsNegativeFour() {
        assertThat(triangleNumber(BigInteger.valueOf(-4))).isEqualTo(BigInteger.valueOf(6))
    }

    @Test
    fun triangleNumber_ofBigInteger_nEqualsNegativeFive() {
        assertThat(triangleNumber(BigInteger.valueOf(-5))).isEqualTo(BigInteger.TEN)
    }

    @Test
    fun triangleNumber_ofBigInteger_nIsLargeNegativeNumber() {
        assertThat(triangleNumber(BigInteger.valueOf(-517679211409L)))
            .isEqualTo(BigInteger("133995882962263218276936"))
    }
    
    @Test
    fun triangleNumberInverse_ofInt_zerothNumber() {
        assertThat(triangleNumberInverse(0)).isEqualTo(0)
    }

    @Test
    fun triangleNumberInverse_ofInt_firstNumber() {
        assertThat(triangleNumberInverse(1)).isEqualTo(1)
    }

    @Test
    fun triangleNumberInverse_ofInt_secondNumber() {
        assertThat(triangleNumberInverse(3)).isEqualTo(2)
    }

    @Test
    fun triangleNumberInverse_ofInt_thirdNumber() {
        assertThat(triangleNumberInverse(6)).isEqualTo(3)
    }

    @Test
    fun triangleNumberInverse_ofInt_fourthNumber() {
        assertThat(triangleNumberInverse(10)).isEqualTo(4)
    }

    @Test
    fun triangleNumberInverse_ofInt_largeNumber() {
        assertThat(triangleNumberInverse(365940)).isEqualTo(855)
    }

    @Test
    fun triangleNumberInverse_ofInt_maxPossibleNumber() {
        assertThat(triangleNumberInverse(2147450880)).isEqualTo(65535)
    }

    @Test
    fun triangleNumberInverse_ofLong_zerothNumber() {
        assertThat(triangleNumberInverse(0L)).isEqualTo(0L)
    }

    @Test
    fun triangleNumberInverse_ofLong_firstNumber() {
        assertThat(triangleNumberInverse(1L)).isEqualTo(1L)
    }

    @Test
    fun triangleNumberInverse_ofLong_secondNumber() {
        assertThat(triangleNumberInverse(3L)).isEqualTo(2L)
    }

    @Test
    fun triangleNumberInverse_ofLong_thirdNumber() {
        assertThat(triangleNumberInverse(6L)).isEqualTo(3L)
    }

    @Test
    fun triangleNumberInverse_ofLong_fourthNumber() {
        assertThat(triangleNumberInverse(10L)).isEqualTo(4L)
    }

    @Test
    fun triangleNumberInverse_ofLong_largeNumber() {
        assertThat(triangleNumberInverse(64620L)).isEqualTo(359L)
    }

    @Test
    fun triangleNumberInverse_ofLong_maxPossibleNumber() {
        assertThat(triangleNumberInverse(9223372034707292160L)).isEqualTo(4294967295L)
    }

    @Test
    fun triangleNumberInverse_ofBigInteger_nEqualsZero() {
        assertThat(triangleNumberInverse(BigInteger.ZERO)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun triangleNumberInverse_ofBigInteger_nEqualsOne() {
        assertThat(triangleNumberInverse(BigInteger.ONE)).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun triangleNumberInverse_ofBigInteger_nEqualsTwo() {
        assertThat(triangleNumberInverse(BigInteger.valueOf(3))).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun triangleNumberInverse_ofBigInteger_nEqualsThree() {
        assertThat(triangleNumberInverse(BigInteger.valueOf(6))).isEqualTo(BigInteger.valueOf(3))
    }

    @Test
    fun triangleNumberInverse_ofBigInteger_nEqualsFour() {
        assertThat(triangleNumberInverse(BigInteger.valueOf(10))).isEqualTo(BigInteger.valueOf(4))
    }

    @Test
    fun triangleNumberInverse_ofBigInteger_nIsLargePositiveNumber() {
        assertThat(triangleNumberInverse(BigInteger("7375637916843480255525")))
            .isEqualTo(BigInteger.valueOf(121454830425L))
    }
}
