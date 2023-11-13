package com.curtislb.adventofcode.common.number

import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions related to multiples and divisors.
 */
class MultiplesTest {
    @Test
    fun greatestCommonDivisor_ofInts_oneAndOne() {
        assertThat(greatestCommonDivisor(1, 1)).isEqualTo(1)
    }

    @Test
    fun greatestCommonDivisor_ofInts_oneAndTwo() {
        assertThat(greatestCommonDivisor(1, 2)).isEqualTo(1)
    }

    @Test
    fun greatestCommonDivisor_ofInts_twoAndOne() {
        assertThat(greatestCommonDivisor(2, 1)).isEqualTo(1)
    }

    @Test
    fun greatestCommonDivisor_ofInts_twoAndTwo() {
        assertThat(greatestCommonDivisor(2, 2)).isEqualTo(2)
    }

    @Test
    fun greatestCommonDivisor_ofInts_twoAndThree() {
        assertThat(greatestCommonDivisor(2, 3)).isEqualTo(1)
    }

    @Test
    fun greatestCommonDivisor_ofInts_twoAndFour() {
        assertThat(greatestCommonDivisor(2, 4)).isEqualTo(2)
    }

    @Test
    fun greatestCommonDivisor_ofInts_largeCoprimeNumbers() {
        assertThat(greatestCommonDivisor(452713601, 662853842)).isEqualTo(1)
    }

    @Test
    fun greatestCommonDivisor_ofInts_largeNonCoprimeNumbers() {
        assertThat(greatestCommonDivisor(452713601, 662853843)).isEqualTo(3581)
    }

    @Test
    fun greatestCommonDivisor_ofInts_firstNumberZero() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 1) }
    }

    @Test
    fun greatestCommonDivisor_ofInts_secondNumberZero() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1, 0) }
    }

    @Test
    fun greatestCommonDivisor_ofInts_bothNumbersZero() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 0) }
    }

    @Test
    fun greatestCommonDivisor_ofInts_firstNumberNegative() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(-1, 1) }
    }

    @Test
    fun greatestCommonDivisor_ofInts_secondNumberNegative() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1, -1) }
    }

    @Test
    fun greatestCommonDivisor_ofInts_bothNumbersNegative() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(-1, -1) }
    }

    @Test
    fun greatestCommonDivisor_ofLongs_oneAndOne() {
        assertThat(greatestCommonDivisor(1L, 1L)).isEqualTo(1L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_oneAndTwo() {
        assertThat(greatestCommonDivisor(1L, 2L)).isEqualTo(1L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_twoAndOne() {
        assertThat(greatestCommonDivisor(2L, 1L)).isEqualTo(1L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_twoAndTwo() {
        assertThat(greatestCommonDivisor(2L, 2L)).isEqualTo(2L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_twoAndThree() {
        assertThat(greatestCommonDivisor(2L, 3L)).isEqualTo(1L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_twoAndFour() {
        assertThat(greatestCommonDivisor(2L, 4L)).isEqualTo(2L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_largeCoprimeNumbers() {
        assertThat(greatestCommonDivisor(452713601L, 662853842L)).isEqualTo(1L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_largeNonCoprimeNumbers() {
        assertThat(greatestCommonDivisor(452713601L, 662853843L)).isEqualTo(3581L)
    }

    @Test
    fun greatestCommonDivisor_ofLongs_firstNumberZero() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 1L) }
    }

    @Test
    fun greatestCommonDivisor_ofLongs_secondNumberZero() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1L, 0L) }
    }

    @Test
    fun greatestCommonDivisor_ofLongs_bothNumbersZero() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 0L) }
    }

    @Test
    fun greatestCommonDivisor_ofLongs_firstNumberNegative() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(-1L, 1L) }
    }

    @Test
    fun greatestCommonDivisor_ofLongs_secondNumberNegative() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1L, -1L) }
    }

    @Test
    fun greatestCommonDivisor_ofLongs_bothNumbersNegative() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(-1L, -1L) }
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_oneAndOne() {
        assertThat(leastCommonMultiple(1, 1)).isEqualTo(1)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_oneAndTwo() {
        assertThat(leastCommonMultiple(1, 2)).isEqualTo(2)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_twoAndOne() {
        assertThat(leastCommonMultiple(2, 1)).isEqualTo(2)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_twoAndTwo() {
        assertThat(leastCommonMultiple(2, 2)).isEqualTo(2)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_twoAndThree() {
        assertThat(leastCommonMultiple(2, 3)).isEqualTo(6)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_twoAndFour() {
        assertThat(leastCommonMultiple(2, 4)).isEqualTo(4)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_largeCoprimeNumbers() {
        assertThat(leastCommonMultiple(201, 172)).isEqualTo(34572)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_largeNonCoprimeNumbers() {
        assertThat(leastCommonMultiple(720, 920)).isEqualTo(16560)
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_firstNumberZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 1) }
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_secondNumberZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1, 0) }
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_bothNumbersZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 0) }
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_firstNumberNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, 1) }
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_secondNumberNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1, -1) }
    }

    @Test
    fun leastCommonMultiple_ofTwoInts_bothNumbersNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, -1) }
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_oneAndOne() {
        assertThat(leastCommonMultiple(1L, 1L)).isEqualTo(1L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_oneAndTwo() {
        assertThat(leastCommonMultiple(1L, 2L)).isEqualTo(2L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_twoAndOne() {
        assertThat(leastCommonMultiple(2L, 1L)).isEqualTo(2L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_twoAndTwo() {
        assertThat(leastCommonMultiple(2L, 2L)).isEqualTo(2L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_twoAndThree() {
        assertThat(leastCommonMultiple(2L, 3L)).isEqualTo(6L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_twoAndFour() {
        assertThat(leastCommonMultiple(2L, 4L)).isEqualTo(4L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_largeCoprimeNumbers() {
        assertThat(leastCommonMultiple(319993L, 703909L)).isEqualTo(225245952637L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_largeNonCoprimeNumbers() {
        assertThat(leastCommonMultiple(55250154L, 21071889L)).isEqualTo(20608307442L)
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_firstNumberZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 1L) }
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_secondNumberZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L) }
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_bothNumbersZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L) }
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_firstNumberNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 1L) }
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_secondNumberNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L) }
    }

    @Test
    fun leastCommonMultiple_ofTwoLongs_bothNumbersNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L) }
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_allOnes() {
        assertThat(leastCommonMultiple(1L, 1L, 1L)).isEqualTo(1L)
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_twoAndOnes() {
        assertThat(leastCommonMultiple(2L, 1L, 1L)).isEqualTo(2L)
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_allCoprime() {
        assertThat(leastCommonMultiple(8L, 9L, 7L)).isEqualTo(504L)
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_notCoprime() {
        assertThat(leastCommonMultiple(2L, 64L, 8L)).isEqualTo(64L)
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_oneZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 1L, 2L) }
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_twoZeros() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L, 0L) }
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_allZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L, 0L) }
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_oneNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 1L, 2L) }
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_twoNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L, -1L) }
    }

    @Test
    fun leastCommonMultiple_ofThreeLongs_allNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L, -1L) }
    }

    @Test
    fun leastCommonMultiple_ofSeveralLongs_allCoprime() {
        assertThat(leastCommonMultiple(4L, 7L, 2L, 5L, 3L)).isEqualTo(420L)
    }

    @Test
    fun leastCommonMultiple_ofSeveralLongs_notCoprime() {
        assertThat(leastCommonMultiple(540330L, 424130L, 465962L, 357896L))
            .isEqualTo(4871660667720L)
    }

    @Test
    fun leastCommonMultiple_ofSeveralLongs_withZero() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 0L, 4L) }
    }

    @Test
    fun leastCommonMultiple_ofSeveralLongs_withNegative() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 4L, -1L) }
    }

    @Test
    fun nextMultipleAbove_withInts_oneAndZero() {
        assertThat(1.nextMultipleAbove(0)).isEqualTo(1)
    }

    @Test
    fun nextMultipleAbove_withInts_oneAndOne() {
        assertThat(1.nextMultipleAbove(1)).isEqualTo(2)
    }

    @Test
    fun nextMultipleAbove_withInts_oneAndTwo() {
        assertThat(1.nextMultipleAbove(2)).isEqualTo(3)
    }

    @Test
    fun nextMultipleAbove_withInts_twoAndZero() {
        assertThat(2.nextMultipleAbove(0)).isEqualTo(2)
    }

    @Test
    fun nextMultipleAbove_withInts_twoAndOne() {
        assertThat(2.nextMultipleAbove(1)).isEqualTo(2)
    }

    @Test
    fun nextMultipleAbove_withInts_twoAndTwo() {
        assertThat(2.nextMultipleAbove(2)).isEqualTo(4)
    }

    @Test
    fun nextMultipleAbove_withInts_twoAndThree() {
        assertThat(2.nextMultipleAbove(3)).isEqualTo(4)
    }

    @Test
    fun nextMultipleAbove_withInts_smallAndLarge() {
        assertThat(73.nextMultipleAbove(820)).isEqualTo(876)
    }

    @Test
    fun nextMultipleAbove_withInts_largeAndSmall() {
        assertThat(365.nextMultipleAbove(42)).isEqualTo(365)
    }

    @Test
    fun nextMultipleAbove_withInts_bothLarge() {
        assertThat(9448487.nextMultipleAbove(589545477)).isEqualTo(595254681)
    }

    @Test
    fun nextMultipleAbove_withInts_thisZero() {
        assertThrows<IllegalArgumentException> { 0.nextMultipleAbove(1) }
    }

    @Test
    fun nextMultipleAbove_withInts_bothZero() {
        assertThrows<IllegalArgumentException> { 0.nextMultipleAbove(0) }
    }

    @Test
    fun nextMultipleAbove_withInts_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAbove(1) }
    }

    @Test
    fun nextMultipleAbove_withInts_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1.nextMultipleAbove(-1) }
    }

    @Test
    fun nextMultipleAbove_withInts_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAbove(-2) }
    }

    @Test
    fun nextMultipleAbove_withLongs_oneAndZero() {
        assertThat(1L.nextMultipleAbove(0L)).isEqualTo(1L)
    }

    @Test
    fun nextMultipleAbove_withLongs_oneAndOne() {
        assertThat(1L.nextMultipleAbove(1L)).isEqualTo(2L)
    }

    @Test
    fun nextMultipleAbove_withLongs_oneAndTwo() {
        assertThat(1L.nextMultipleAbove(2L)).isEqualTo(3L)
    }

    @Test
    fun nextMultipleAbove_withLongs_twoAndZero() {
        assertThat(2L.nextMultipleAbove(0L)).isEqualTo(2L)
    }

    @Test
    fun nextMultipleAbove_withLongs_twoAndOne() {
        assertThat(2L.nextMultipleAbove(1L)).isEqualTo(2L)
    }

    @Test
    fun nextMultipleAbove_withLongs_twoAndTwo() {
        assertThat(2L.nextMultipleAbove(2L)).isEqualTo(4L)
    }

    @Test
    fun nextMultipleAbove_withLongs_twoAndThree() {
        assertThat(2L.nextMultipleAbove(3L)).isEqualTo(4L)
    }

    @Test
    fun nextMultipleAbove_withLongs_smallAndLarge() {
        assertThat(73L.nextMultipleAbove(820L)).isEqualTo(876L)
    }

    @Test
    fun nextMultipleAbove_withLongs_largeAndSmall() {
        assertThat(365L.nextMultipleAbove(42L)).isEqualTo(365L)
    }

    @Test
    fun nextMultipleAbove_withLongs_bothLarge() {
        assertThat(9448487L.nextMultipleAbove(589545477L)).isEqualTo(595254681L)
    }

    @Test
    fun nextMultipleAbove_withLongs_thisZero() {
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAbove(1L) }
    }

    @Test
    fun nextMultipleAbove_withLongs_bothZero() {
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAbove(0L) }
    }

    @Test
    fun nextMultipleAbove_withLongs_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAbove(1L) }
    }

    @Test
    fun nextMultipleAbove_withLongs_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1L.nextMultipleAbove(-1L) }
    }

    @Test
    fun nextMultipleAbove_withLongs_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAbove(-2L) }
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_oneAndZero() {
        assertThat(BigInteger.ONE.nextMultipleAbove(BigInteger.ZERO)).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_oneAndOne() {
        assertThat(BigInteger.ONE.nextMultipleAbove(BigInteger.ONE)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_oneAndTwo() {
        assertThat(BigInteger.ONE.nextMultipleAbove(BigInteger.TWO))
            .isEqualTo(BigInteger.valueOf(3))
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_twoAndZero() {
        assertThat(BigInteger.TWO.nextMultipleAbove(BigInteger.ZERO)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_twoAndOne() {
        assertThat(BigInteger.TWO.nextMultipleAbove(BigInteger.ONE)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_twoAndTwo() {
        assertThat(BigInteger.TWO.nextMultipleAbove(BigInteger.TWO))
            .isEqualTo(BigInteger.valueOf(4))
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_twoAndThree() {
        assertThat(BigInteger.TWO.nextMultipleAbove(BigInteger.valueOf(3)))
            .isEqualTo(BigInteger.valueOf(4))
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_smallAndLarge() {
        assertThat(BigInteger.valueOf(73).nextMultipleAbove(BigInteger.valueOf(820)))
            .isEqualTo(BigInteger.valueOf(876))
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_largeAndSmall() {
        assertThat(BigInteger.valueOf(365).nextMultipleAbove(BigInteger.valueOf(42)))
            .isEqualTo(BigInteger.valueOf(365))
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_bothLarge() {
        assertThat(BigInteger.valueOf(9448487).nextMultipleAbove(BigInteger.valueOf(589545477)))
            .isEqualTo(BigInteger.valueOf(595254681))
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_thisZero() {
        assertThrows<IllegalArgumentException> { BigInteger.ZERO.nextMultipleAbove(BigInteger.ONE) }
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_bothZero() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAbove(BigInteger.ZERO)
        }
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_thisNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).nextMultipleAbove(BigInteger.ONE)
        }
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_argumentNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.nextMultipleAbove(BigInteger.valueOf(-1))
        }
    }

    @Test
    fun nextMultipleAbove_withBigIntegers_bothNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).nextMultipleAbove(BigInteger.valueOf(-2))
        }
    }

    @Test
    fun nextMultipleAtLeast_withInts_oneAndZero() {
        assertThat(1.nextMultipleAtLeast(0)).isEqualTo(0)
    }

    @Test
    fun nextMultipleAtLeast_withInts_oneAndOne() {
        assertThat(1.nextMultipleAtLeast(1)).isEqualTo(1)
    }

    @Test
    fun nextMultipleAtLeast_withInts_oneAndTwo() {
        assertThat(1.nextMultipleAtLeast(2)).isEqualTo(2)
    }

    @Test
    fun nextMultipleAtLeast_withInts_twoAndZero() {
        assertThat(2.nextMultipleAtLeast(0)).isEqualTo(0)
    }

    @Test
    fun nextMultipleAtLeast_withInts_twoAndOne() {
        assertThat(2.nextMultipleAtLeast(1)).isEqualTo(2)
    }

    @Test
    fun nextMultipleAtLeast_withInts_twoAndTwo() {
        assertThat(2.nextMultipleAtLeast(2)).isEqualTo(2)
    }

    @Test
    fun nextMultipleAtLeast_withInts_twoAndThree() {
        assertThat(2.nextMultipleAtLeast(3)).isEqualTo(4)
    }

    @Test
    fun nextMultipleAtLeast_withInts_smallAndLarge() {
        assertThat(73.nextMultipleAtLeast(820)).isEqualTo(876)
    }

    @Test
    fun nextMultipleAtLeast_withInts_largeAndSmall() {
        assertThat(365.nextMultipleAtLeast(42)).isEqualTo(365)
    }

    @Test
    fun nextMultipleAtLeast_withInts_bothLarge() {
        assertThat(9448487.nextMultipleAtLeast(589545477)).isEqualTo(595254681)
    }

    @Test
    fun nextMultipleAtLeast_withInts_thisZero() {
        assertThrows<IllegalArgumentException> { 0.nextMultipleAtLeast(1) }
    }

    @Test
    fun nextMultipleAtLeast_withInts_bothZero() {
        assertThrows<IllegalArgumentException> { 0.nextMultipleAtLeast(0) }
    }

    @Test
    fun nextMultipleAtLeast_withInts_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAtLeast(1) }
    }

    @Test
    fun nextMultipleAtLeast_withInts_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1.nextMultipleAtLeast(-1) }
    }

    @Test
    fun nextMultipleAtLeast_withInts_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAtLeast(-2) }
    }

    @Test
    fun nextMultipleAtLeast_withLongs_oneAndZero() {
        assertThat(1L.nextMultipleAtLeast(0L)).isEqualTo(0L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_oneAndOne() {
        assertThat(1L.nextMultipleAtLeast(1L)).isEqualTo(1L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_oneAndTwo() {
        assertThat(1L.nextMultipleAtLeast(2L)).isEqualTo(2L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_twoAndZero() {
        assertThat(2L.nextMultipleAtLeast(0L)).isEqualTo(0L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_twoAndOne() {
        assertThat(2L.nextMultipleAtLeast(1L)).isEqualTo(2L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_twoAndTwo() {
        assertThat(2L.nextMultipleAtLeast(2L)).isEqualTo(2L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_twoAndThree() {
        assertThat(2L.nextMultipleAtLeast(3L)).isEqualTo(4L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_smallAndLarge() {
        assertThat(73L.nextMultipleAtLeast(820L)).isEqualTo(876L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_largeAndSmall() {
        assertThat(365L.nextMultipleAtLeast(42L)).isEqualTo(365L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_bothLarge() {
        assertThat(9448487L.nextMultipleAtLeast(589545477L)).isEqualTo(595254681L)
    }

    @Test
    fun nextMultipleAtLeast_withLongs_thisZero() {
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAtLeast(1L) }
    }

    @Test
    fun nextMultipleAtLeast_withLongs_bothZero() {
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAtLeast(0L) }
    }

    @Test
    fun nextMultipleAtLeast_withLongs_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAtLeast(1L) }
    }

    @Test
    fun nextMultipleAtLeast_withLongs_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1L.nextMultipleAtLeast(-1L) }
    }

    @Test
    fun nextMultipleAtLeast_withLongs_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAtLeast(-2L) }
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_oneAndZero() {
        assertThat(BigInteger.ONE.nextMultipleAtLeast(BigInteger.ZERO)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_oneAndOne() {
        assertThat(BigInteger.ONE.nextMultipleAtLeast(BigInteger.ONE)).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_oneAndTwo() {
        assertThat(BigInteger.ONE.nextMultipleAtLeast(BigInteger.TWO)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_twoAndZero() {
        assertThat(BigInteger.TWO.nextMultipleAtLeast(BigInteger.ZERO)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_twoAndOne() {
        assertThat(BigInteger.TWO.nextMultipleAtLeast(BigInteger.ONE)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_twoAndTwo() {
        assertThat(BigInteger.TWO.nextMultipleAtLeast(BigInteger.TWO)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_twoAndThree() {
        assertThat(BigInteger.TWO.nextMultipleAtLeast(BigInteger.valueOf(3)))
            .isEqualTo(BigInteger.valueOf(4))
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_smallAndLarge() {
        assertThat(BigInteger.valueOf(73).nextMultipleAtLeast(BigInteger.valueOf(820)))
            .isEqualTo(BigInteger.valueOf(876))
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_largeAndSmall() {
        assertThat(BigInteger.valueOf(365).nextMultipleAtLeast(BigInteger.valueOf(42)))
            .isEqualTo(BigInteger.valueOf(365))
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_bothLarge() {
        assertThat(BigInteger.valueOf(9448487).nextMultipleAtLeast(BigInteger.valueOf(589545477)))
            .isEqualTo(BigInteger.valueOf(595254681))
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_thisZero() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAtLeast(BigInteger.ONE)
        }
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_bothZero() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAtLeast(BigInteger.ZERO)
        }
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_thisNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).nextMultipleAtLeast(BigInteger.ONE)
        }
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_argumentNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.nextMultipleAtLeast(BigInteger.valueOf(-1))
        }
    }

    @Test
    fun nextMultipleAtLeast_withBigIntegers_bothNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).nextMultipleAtLeast(BigInteger.valueOf(-2))
        }
    }

    @Test
    fun prevMultipleAtMost_withInts_oneAndZero() {
        assertThat(1.prevMultipleAtMost(0)).isEqualTo(0)
    }

    @Test
    fun prevMultipleAtMost_withInts_oneAndOne() {
        assertThat(1.prevMultipleAtMost(1)).isEqualTo(1)
    }

    @Test
    fun prevMultipleAtMost_withInts_oneAndTwo() {
        assertThat(1.prevMultipleAtMost(2)).isEqualTo(2)
    }

    @Test
    fun prevMultipleAtMost_withInts_twoAndZero() {
        assertThat(2.prevMultipleAtMost(0)).isEqualTo(0)
    }

    @Test
    fun prevMultipleAtMost_withInts_twoAndOne() {
        assertThat(2.prevMultipleAtMost(1)).isEqualTo(0)
    }

    @Test
    fun prevMultipleAtMost_withInts_twoAndTwo() {
        assertThat(2.prevMultipleAtMost(2)).isEqualTo(2)
    }

    @Test
    fun prevMultipleAtMost_withInts_twoAndThree() {
        assertThat(2.prevMultipleAtMost(3)).isEqualTo(2)
    }

    @Test
    fun prevMultipleAtMost_withInts_smallAndLarge() {
        assertThat(73.prevMultipleAtMost(820)).isEqualTo(803)
    }

    @Test
    fun prevMultipleAtMost_withInts_largeAndSmall() {
        assertThat(365.prevMultipleAtMost(42)).isEqualTo(0)
    }

    @Test
    fun prevMultipleAtMost_withInts_bothLarge() {
        assertThat(9448487.prevMultipleAtMost(589545477)).isEqualTo(585806194)
    }

    @Test
    fun prevMultipleAtMost_withInts_thisZero() {
        assertThrows<IllegalArgumentException> { 0.prevMultipleAtMost(1) }
    }

    @Test
    fun prevMultipleAtMost_withInts_bothZero() {
        assertThrows<IllegalArgumentException> { 0.prevMultipleAtMost(0) }
    }

    @Test
    fun prevMultipleAtMost_withInts_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1).prevMultipleAtMost(1) }
    }

    @Test
    fun prevMultipleAtMost_withInts_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1.prevMultipleAtMost(-1) }
    }

    @Test
    fun prevMultipleAtMost_withInts_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1).prevMultipleAtMost(-2) }
    }

    @Test
    fun prevMultipleAtMost_withLongs_oneAndZero() {
        assertThat(1L.prevMultipleAtMost(0L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_oneAndOne() {
        assertThat(1L.prevMultipleAtMost(1L)).isEqualTo(1L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_oneAndTwo() {
        assertThat(1L.prevMultipleAtMost(2L)).isEqualTo(2L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_twoAndZero() {
        assertThat(2L.prevMultipleAtMost(0L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_twoAndOne() {
        assertThat(2L.prevMultipleAtMost(1L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_twoAndTwo() {
        assertThat(2L.prevMultipleAtMost(2L)).isEqualTo(2L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_twoAndThree() {
        assertThat(2L.prevMultipleAtMost(3L)).isEqualTo(2L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_smallAndLarge() {
        assertThat(73L.prevMultipleAtMost(820L)).isEqualTo(803L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_largeAndSmall() {
        assertThat(365L.prevMultipleAtMost(42L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_bothLarge() {
        assertThat(9448487L.prevMultipleAtMost(589545477L)).isEqualTo(585806194L)
    }

    @Test
    fun prevMultipleAtMost_withLongs_thisZero() {
        assertThrows<IllegalArgumentException> { 0L.prevMultipleAtMost(1L) }
    }

    @Test
    fun prevMultipleAtMost_withLongs_bothZero() {
        assertThrows<IllegalArgumentException> { 0L.prevMultipleAtMost(0L) }
    }

    @Test
    fun prevMultipleAtMost_withLongs_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleAtMost(1L) }
    }

    @Test
    fun prevMultipleAtMost_withLongs_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1L.prevMultipleAtMost(-1L) }
    }

    @Test
    fun prevMultipleAtMost_withLongs_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleAtMost(-2L) }
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_oneAndZero() {
        assertThat(BigInteger.ONE.prevMultipleAtMost(BigInteger.ZERO)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_oneAndOne() {
        assertThat(BigInteger.ONE.prevMultipleAtMost(BigInteger.ONE)).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_oneAndTwo() {
        assertThat(BigInteger.ONE.prevMultipleAtMost(BigInteger.TWO)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_twoAndZero() {
        assertThat(BigInteger.TWO.prevMultipleAtMost(BigInteger.ZERO)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_twoAndOne() {
        assertThat(BigInteger.TWO.prevMultipleAtMost(BigInteger.ONE)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_twoAndTwo() {
        assertThat(BigInteger.TWO.prevMultipleAtMost(BigInteger.TWO)).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_twoAndThree() {
        assertThat(BigInteger.TWO.prevMultipleAtMost(BigInteger.valueOf(3)))
            .isEqualTo(BigInteger.TWO)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_smallAndLarge() {
        assertThat(BigInteger.valueOf(73).prevMultipleAtMost(BigInteger.valueOf(820)))
            .isEqualTo(BigInteger.valueOf(803))
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_largeAndSmall() {
        assertThat(BigInteger.valueOf(365).prevMultipleAtMost(BigInteger.valueOf(42)))
            .isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_bothLarge() {
        assertThat(BigInteger.valueOf(9448487).prevMultipleAtMost(BigInteger.valueOf(589545477)))
            .isEqualTo(BigInteger.valueOf(585806194))
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_thisZero() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleAtMost(BigInteger.ONE)
        }
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_bothZero() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleAtMost(BigInteger.ZERO)
        }
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_thisNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).prevMultipleAtMost(BigInteger.ONE)
        }
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_argumentNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(1).prevMultipleAtMost(BigInteger.valueOf(-1))
        }
    }

    @Test
    fun prevMultipleAtMost_withBigIntegers_bothNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).prevMultipleAtMost(BigInteger.valueOf(-2))
        }
    }

    @Test
    fun prevMultipleBelow_withInts_oneAndZero() {
        assertThat(1.prevMultipleBelow(0)).isEqualTo(-1)
    }

    @Test
    fun prevMultipleBelow_withInts_oneAndOne() {
        assertThat(1.prevMultipleBelow(1)).isEqualTo(0)
    }

    @Test
    fun prevMultipleBelow_withInts_oneAndTwo() {
        assertThat(1.prevMultipleBelow(2)).isEqualTo(1)
    }

    @Test
    fun prevMultipleBelow_withInts_twoAndZero() {
        assertThat(2.prevMultipleBelow(0)).isEqualTo(-2)
    }

    @Test
    fun prevMultipleBelow_withInts_twoAndOne() {
        assertThat(2.prevMultipleBelow(1)).isEqualTo(0)
    }

    @Test
    fun prevMultipleBelow_withInts_twoAndTwo() {
        assertThat(2.prevMultipleBelow(2)).isEqualTo(0)
    }

    @Test
    fun prevMultipleBelow_withInts_twoAndThree() {
        assertThat(2.prevMultipleBelow(3)).isEqualTo(2)
    }

    @Test
    fun prevMultipleBelow_withInts_smallAndLarge() {
        assertThat(73.prevMultipleBelow(820)).isEqualTo(803)
    }

    @Test
    fun prevMultipleBelow_withInts_largeAndSmall() {
        assertThat(365.prevMultipleBelow(42)).isEqualTo(0)
    }

    @Test
    fun prevMultipleBelow_withInts_bothLarge() {
        assertThat(9448487.prevMultipleBelow(589545477)).isEqualTo(585806194)
    }

    @Test
    fun prevMultipleBelow_withInts_thisZero() {
        assertThrows<IllegalArgumentException> { 0.prevMultipleBelow(1) }
    }

    @Test
    fun prevMultipleBelow_withInts_bothZero() {
        assertThrows<IllegalArgumentException> { 0.prevMultipleBelow(0) }
    }

    @Test
    fun prevMultipleBelow_withInts_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1).prevMultipleBelow(1) }
    }

    @Test
    fun prevMultipleBelow_withInts_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1.prevMultipleBelow(-1) }
    }

    @Test
    fun prevMultipleBelow_withInts_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1).prevMultipleBelow(-2) }
    }

    @Test
    fun prevMultipleBelow_withLongs_oneAndZero() {
        assertThat(1L.prevMultipleBelow(0L)).isEqualTo(-1L)
    }

    @Test
    fun prevMultipleBelow_withLongs_oneAndOne() {
        assertThat(1L.prevMultipleBelow(1L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleBelow_withLongs_oneAndTwo() {
        assertThat(1L.prevMultipleBelow(2L)).isEqualTo(1L)
    }

    @Test
    fun prevMultipleBelow_withLongs_twoAndZero() {
        assertThat(2L.prevMultipleBelow(0L)).isEqualTo(-2L)
    }

    @Test
    fun prevMultipleBelow_withLongs_twoAndOne() {
        assertThat(2L.prevMultipleBelow(1L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleBelow_withLongs_twoAndTwo() {
        assertThat(2L.prevMultipleBelow(2L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleBelow_withLongs_twoAndThree() {
        assertThat(2L.prevMultipleBelow(3L)).isEqualTo(2L)
    }

    @Test
    fun prevMultipleBelow_withLongs_smallAndLarge() {
        assertThat(73L.prevMultipleBelow(820L)).isEqualTo(803L)
    }

    @Test
    fun prevMultipleBelow_withLongs_largeAndSmall() {
        assertThat(365L.prevMultipleBelow(42L)).isEqualTo(0L)
    }

    @Test
    fun prevMultipleBelow_withLongs_bothLarge() {
        assertThat(9448487L.prevMultipleBelow(589545477L)).isEqualTo(585806194L)
    }

    @Test
    fun prevMultipleBelow_withLongs_thisZero() {
        assertThrows<IllegalArgumentException> { 0L.prevMultipleBelow(1L) }
    }

    @Test
    fun prevMultipleBelow_withLongs_bothZero() {
        assertThrows<IllegalArgumentException> { 0L.prevMultipleBelow(0L) }
    }

    @Test
    fun prevMultipleBelow_withLongs_thisNegative() {
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleBelow(1L) }
    }

    @Test
    fun prevMultipleBelow_withLongs_argumentNegative() {
        assertThrows<IllegalArgumentException> { 1L.prevMultipleBelow(-1L) }
    }

    @Test
    fun prevMultipleBelow_withLongs_bothNegative() {
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleBelow(-2L) }
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_oneAndZero() {
        assertThat(BigInteger.ONE.prevMultipleBelow(BigInteger.ZERO))
            .isEqualTo(BigInteger.valueOf(-1))
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_oneAndOne() {
        assertThat(BigInteger.ONE.prevMultipleBelow(BigInteger.ONE)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_oneAndTwo() {
        assertThat(BigInteger.ONE.prevMultipleBelow(BigInteger.TWO)).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_twoAndZero() {
        assertThat(BigInteger.TWO.prevMultipleBelow(BigInteger.ZERO))
            .isEqualTo(BigInteger.valueOf(-2))
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_twoAndOne() {
        assertThat(BigInteger.TWO.prevMultipleBelow(BigInteger.ONE)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_twoAndTwo() {
        assertThat(BigInteger.TWO.prevMultipleBelow(BigInteger.TWO)).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_twoAndThree() {
        assertThat(BigInteger.TWO.prevMultipleBelow(BigInteger.valueOf(3)))
            .isEqualTo(BigInteger.TWO)
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_smallAndLarge() {
        assertThat(BigInteger.valueOf(73).prevMultipleBelow(BigInteger.valueOf(820)))
            .isEqualTo(BigInteger.valueOf(803))
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_largeAndSmall() {
        assertThat(BigInteger.valueOf(365).prevMultipleBelow(BigInteger.valueOf(42)))
            .isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_bothLarge() {
        assertThat(BigInteger.valueOf(9448487).prevMultipleBelow(BigInteger.valueOf(589545477)))
            .isEqualTo(BigInteger.valueOf(585806194))
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_thisZero() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleBelow(BigInteger.ONE)
        }
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_bothZero() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleBelow(BigInteger.ZERO)
        }
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_thisNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).prevMultipleBelow(BigInteger.ONE)
        }
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_argumentNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(1).prevMultipleBelow(BigInteger.valueOf(-1))
        }
    }

    @Test
    fun prevMultipleBelow_withBigIntegers_bothNegative() {
        assertThrows<IllegalArgumentException> {
            BigInteger.valueOf(-1).prevMultipleBelow(BigInteger.valueOf(-2))
        }
    }
}
