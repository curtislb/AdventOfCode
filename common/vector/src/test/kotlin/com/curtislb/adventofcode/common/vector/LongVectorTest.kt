package com.curtislb.adventofcode.common.vector

import com.curtislb.adventofcode.common.testing.isApproximately
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [LongVector] class.
 */
class LongVectorTest {
    @Test
    fun dimension_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.dimension).isEqualTo(0)
    }

    @Test
    fun dimension_withOneComponent() {
        val vector = longVectorOf(7)
        assertThat(vector.dimension).isEqualTo(1)
    }

    @Test
    fun dimension_withMultipleComponents() {
        val vector = longVectorOf(75, 83, -77, -80, -46)
        assertThat(vector.dimension).isEqualTo(5)
    }

    @Test
    fun getX_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.x }
    }

    @Test
    fun getX_withOneComponent() {
        val vector = longVectorOf(27)
        assertThat(vector.x).isEqualTo(27)
    }

    @Test
    fun getX_withTwoComponents() {
        val vector = longVectorOf(-16, -9)
        assertThat(vector.x).isEqualTo(-16)
    }

    @Test
    fun setX_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.x = 0 }
    }

    @Test
    fun setX_withOneComponent() {
        val vector = longVectorOf(0)
        vector.x = 2
        assertThat(vector).isEqualTo(longVectorOf(2))
    }

    @Test
    fun setX_withTwoComponents() {
        val vector = longVectorOf(38, 54)
        vector.x = 56
        assertThat(vector).isEqualTo(longVectorOf(56, 54))
    }

    @Test
    fun getY_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.y }
    }

    @Test
    fun getY_withOneComponent() {
        val vector = longVectorOf(4)
        assertThrows<IndexOutOfBoundsException> { vector.y }
    }

    @Test
    fun getY_withTwoComponents() {
        val vector = longVectorOf(80, -78)
        assertThat(vector.y).isEqualTo(-78)
    }

    @Test
    fun getY_withThreeComponents() {
        val vector = longVectorOf(-69, 50, 53)
        assertThat(vector.y).isEqualTo(50)
    }

    @Test
    fun setY_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.y = 2 }
    }

    @Test
    fun setY_withOneComponent() {
        val vector = longVectorOf(-78)
        assertThrows<IndexOutOfBoundsException> { vector.y = 11 }
    }

    @Test
    fun setY_withTwoComponents() {
        val vector = longVectorOf(-19, 79)
        vector.y = 99
        assertThat(vector).isEqualTo(longVectorOf(-19, 99))
    }

    @Test
    fun setY_withThreeComponents() {
        val vector = longVectorOf(64, 35, -27)
        vector.y = -26
        assertThat(vector).isEqualTo(longVectorOf(64, -26, -27))
    }

    @Test
    fun getZ_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.z }
    }

    @Test
    fun getZ_withOneComponent() {
        val vector = longVectorOf(-9)
        assertThrows<IndexOutOfBoundsException> { vector.z }
    }

    @Test
    fun getZ_withTwoComponents() {
        val vector = longVectorOf(-23, 83)
        assertThrows<IndexOutOfBoundsException> { vector.z }
    }

    @Test
    fun getZ_withThreeComponents() {
        val vector = longVectorOf(-91, 31, 44)
        assertThat(vector.z).isEqualTo(44)
    }

    @Test
    fun getZ_withFourComponents() {
        val vector = longVectorOf(-40, 65, 59, -51)
        assertThat(vector.z).isEqualTo(59)
    }

    @Test
    fun setZ_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.z = 1 }
    }

    @Test
    fun setZ_withOneComponent() {
        val vector = longVectorOf(97)
        assertThrows<IndexOutOfBoundsException> { vector.z = 6 }
    }

    @Test
    fun setZ_withTwoComponents() {
        val vector = longVectorOf(-90, 2)
        assertThrows<IndexOutOfBoundsException> { vector.z = -5 }
    }

    @Test
    fun setZ_withThreeComponents() {
        val vector = longVectorOf(-77, -62, 23)
        vector.z = 66
        assertThat(vector).isEqualTo(longVectorOf(-77, -62, 66))
    }

    @Test
    fun setZ_withFourComponents() {
        val vector = longVectorOf(-62, 61, -92, -87)
        vector.z = -49
        assertThat(vector).isEqualTo(longVectorOf(-62, 61, -49, -87))
    }

    @Test
    fun get_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector[0] }
    }

    @Test
    fun get_indexInBounds() {
        val vector = longVectorOf(69, -60, -99, 13, 39)
        assertThat(vector[3]).isEqualTo(13)
    }

    @Test
    fun get_indexTooSmall() {
        val vector = longVectorOf(-51, -48, -71, 53, 46)
        assertThrows<IndexOutOfBoundsException> { vector[-1] }
    }

    @Test
    fun get_indexTooLarge() {
        val vector = longVectorOf(-40, 8, -91, 51, -90)
        assertThrows<IndexOutOfBoundsException> { vector[5] }
    }

    @Test
    fun set_whenEmpty() {
        val vector = longVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector[0] = -9 }
    }

    @Test
    fun set_indexInBounds() {
        val vector = longVectorOf(47, 61, 59, -85, -12)
        vector[4] = 23
        assertThat(vector).isEqualTo(longVectorOf(47, 61, 59, -85, 23))
    }

    @Test
    fun set_indexTooSmall() {
        val vector = longVectorOf(71, 16, 30, 58, -68)
        assertThrows<IndexOutOfBoundsException> { vector[-1] = -49 }
    }

    @Test
    fun set_indexTooLarge() {
        val vector = longVectorOf(21, -65, -9, -83, 71)
        assertThrows<IndexOutOfBoundsException> { vector[5] = 80 }
    }

    @Test
    fun plus_bothEmpty() {
        val left = longVectorOf()
        val right = longVectorOf()
        assertThat(left + right).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun plus_leftEmpty() {
        val left = longVectorOf()
        val right = longVectorOf(1)
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_rightEmpty() {
        val left = longVectorOf(1)
        val right = longVectorOf()
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_leftDimensionSmaller() {
        val left = longVectorOf(86, -25)
        val right = longVectorOf(62, 49, -74)
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_rightDimensionSmaller() {
        val left = longVectorOf(-7, 98, -95)
        val right = longVectorOf(16, -47)
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_bothSameDimension() {
        val left = longVectorOf(47, 61, 59, -85, -12)
        val right = longVectorOf(-75, 26, 2, 23, -17)
        assertThat(left + right)
            .isEqualTo(longVectorOf(-28, 87, 61, -62, -29))
            .isNotSameAs(left)
            .isNotSameAs(right)
    }

    @Test
    fun minus_bothEmpty() {
        val left = longVectorOf()
        val right = longVectorOf()
        assertThat(left - right).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun minus_leftEmpty() {
        val left = longVectorOf()
        val right = longVectorOf(1)
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_rightEmpty() {
        val left = longVectorOf(1)
        val right = longVectorOf()
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_leftDimensionSmaller() {
        val left = longVectorOf(17, 33)
        val right = longVectorOf(-52, -3, 36)
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_rightDimensionSmaller() {
        val left = longVectorOf(-23, -63, -69)
        val right = longVectorOf(58, 51)
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_bothSameDimension() {
        val left = longVectorOf(70, 82, 63, 15, 53)
        val right = longVectorOf(70, 50, 69, -28, 92)
        assertThat(left - right)
            .isEqualTo(longVectorOf(0, 32, -6, 43, -39))
            .isNotSameAs(left)
            .isNotSameAs(right)
    }

    @Test
    fun unaryMinus_whenEmpty() {
        val vector = longVectorOf()
        assertThat(-vector).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun unaryMinus_whenNotEmpty() {
        val vector = longVectorOf(79, 0, 75, -99, -3)
        assertThat(-vector).isEqualTo(longVectorOf(-79, 0, -75, 99, 3))
    }

    @Test
    fun dot_bothEmpty() {
        val left = longVectorOf()
        val right = longVectorOf()
        assertThat(left dot right).isEqualTo(0L)
    }

    @Test
    fun dot_leftEmpty() {
        val left = longVectorOf()
        val right = longVectorOf(1)
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_rightEmpty() {
        val left = longVectorOf(1)
        val right = longVectorOf()
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_leftDimensionSmaller() {
        val left = longVectorOf(96, -91)
        val right = longVectorOf(75, -78, -32)
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_rightDimensionSmaller() {
        val left = longVectorOf(-48, -52, -94)
        val right = longVectorOf(32, -16)
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_bothSameDimension() {
        val left = longVectorOf(21, -8, -18, 19, 0)
        val right = longVectorOf(-14, 24, -15, 25, 4)
        assertThat(left dot right).isEqualTo(259L)
    }

    @Test
    fun cross_leftDimensionTooSmall() {
        val left = longVectorOf(-18, -10)
        val right = longVectorOf(-5, -23, 24)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_leftDimensionTooLarge() {
        val left = longVectorOf(-6, 16, -8, -12)
        val right = longVectorOf(-10, -3, 6)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_rightDimensionTooSmall() {
        val left = longVectorOf(23, 20, -16)
        val right = longVectorOf(-5, 18)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_rightDimensionTooLarge() {
        val left = longVectorOf(-15, -17, 17)
        val right = longVectorOf(-7, 19, 20, -20)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_bothThreeDimensional() {
        val left = longVectorOf(-22, 24, 15)
        val right = longVectorOf(10, -21, -9)
        assertThat(left cross right)
            .isEqualTo(longVectorOf(99, -48, 222))
            .isNotSameAs(left)
            .isNotSameAs(right)
    }

    @Test
    fun add_bothEmpty() {
        val vector = longVectorOf()
        val other = longVectorOf()
        vector.add(other)
        assertThat(vector).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun add_thisEmpty() {
        val vector = longVectorOf()
        val other = longVectorOf(1)
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_otherEmpty() {
        val vector = longVectorOf(1)
        val other = longVectorOf()
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_thisDimensionSmaller() {
        val vector = longVectorOf(19, 7)
        val other = longVectorOf(-15, -25, 15)
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_otherDimensionSmaller() {
        val vector = longVectorOf(11, 15, 6)
        val other = longVectorOf(15, -21)
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_bothSameDimension() {
        val vector = longVectorOf(-17, -3, 35, 4, 47)
        val other = longVectorOf(87, 20, 45, -79, -57)
        vector.add(other)
        assertThat(vector).isEqualTo(longVectorOf(70, 17, 80, -75, -10))
    }

    @Test
    fun subtract_bothEmpty() {
        val vector = longVectorOf()
        val other = longVectorOf()
        vector.subtract(other)
        assertThat(vector).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun subtract_thisEmpty() {
        val vector = longVectorOf()
        val other = longVectorOf(1)
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_otherEmpty() {
        val vector = longVectorOf(1)
        val other = longVectorOf()
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_thisDimensionSmaller() {
        val vector = longVectorOf(-40, 90)
        val other = longVectorOf(92, -98, 95)
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_otherDimensionSmaller() {
        val vector = longVectorOf(23, -3, -20)
        val other = longVectorOf(8, 38)
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_bothSameDimension() {
        val vector = longVectorOf(40, 72, -56, -31, 94)
        val other = longVectorOf(-31, 9, 65, 9, -46)
        vector.subtract(other)
        assertThat(vector).isEqualTo(longVectorOf(71, 63, -121, -40, 140))
    }

    @Test
    fun componentSum_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.componentSum()).isEqualTo(0)
    }

    @Test
    fun componentSum_whenNotEmpty() {
        val vector = longVectorOf(-41, 13, -69, 76, 42)
        assertThat(vector.componentSum()).isEqualTo(21)
    }

    @Test
    fun componentSumOf_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.componentSumOf { it - 5 }).isEqualTo(0)
    }

    @Test
    fun componentSumOf_whenNotEmpty() {
        val vector = longVectorOf(-4, -61, 53, 34, 33)
        assertThat(vector.componentSumOf { it - 5 }).isEqualTo(30)
    }

    @Test
    fun magnitude_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.magnitude()).isApproximately(0.0)
    }

    @Test
    fun magnitude_withSinglePositiveComponent() {
        val vector = longVectorOf(8)
        assertThat(vector.magnitude()).isApproximately(8.0)
    }

    @Test
    fun magnitude_withSingleNegativeComponent() {
        val vector = longVectorOf(-53)
        assertThat(vector.magnitude()).isApproximately(53.0)
    }

    @Test
    fun magnitude_withMultipleComponents() {
        val vector = longVectorOf(70, 75, -37, 79, -12)
        assertThat(vector.magnitude()).isApproximately(135.199852)
    }

    @Test
    fun neighbors_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.neighbors()).isEmpty()
    }

    @Test
    fun neighbors_withOneComponent() {
        val vector = longVectorOf(10)
        assertThat(vector.neighbors())
            .noneMatch { it === vector }
            .containsExactlyInAnyOrder(longVectorOf(9), longVectorOf(11))
    }

    @Test
    fun neighbors_withTwoComponents() {
        val vector = longVectorOf(-47, 52)
        assertThat(vector.neighbors())
            .noneMatch { it === vector }
            .containsExactlyInAnyOrder(
                longVectorOf(-46, 51),
                longVectorOf(-46, 52),
                longVectorOf(-46, 53),
                longVectorOf(-47, 51),
                longVectorOf(-47, 53),
                longVectorOf(-48, 51),
                longVectorOf(-48, 52),
                longVectorOf(-48, 53)
            )
    }

    @Test
    fun neighbors_withThreeComponents() {
        val vector = longVectorOf(87, 1, -60)
        assertThat(vector.neighbors())
            .noneMatch { it === vector }
            .containsExactlyInAnyOrder(
                longVectorOf(86, 0, -61),
                longVectorOf(86, 0, -60),
                longVectorOf(86, 0, -59),
                longVectorOf(86, 1, -61),
                longVectorOf(86, 1, -60),
                longVectorOf(86, 1, -59),
                longVectorOf(86, 2, -61),
                longVectorOf(86, 2, -60),
                longVectorOf(86, 2, -59),
                longVectorOf(87, 0, -61),
                longVectorOf(87, 0, -60),
                longVectorOf(87, 0, -59),
                longVectorOf(87, 1, -61),
                longVectorOf(87, 1, -59),
                longVectorOf(87, 2, -61),
                longVectorOf(87, 2, -60),
                longVectorOf(87, 2, -59),
                longVectorOf(88, 0, -61),
                longVectorOf(88, 0, -60),
                longVectorOf(88, 0, -59),
                longVectorOf(88, 1, -61),
                longVectorOf(88, 1, -60),
                longVectorOf(88, 1, -59),
                longVectorOf(88, 2, -61),
                longVectorOf(88, 2, -60),
                longVectorOf(88, 2, -59),
            )
    }

    @Test
    fun copy_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.copy()).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun copy_whenNotEmpty() {
        val vector = longVectorOf(-33, 92, 30, 0,-45)
        assertThat(vector.copy()).isEqualTo(vector).isNotSameAs(vector)
    }

    @Test
    fun toArray_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.toArray()).isEmpty()
    }

    @Test
    fun toArray_whenNotEmpty() {
        val vector = longVectorOf(54, -23, 9, -9, 0)
        val array = vector.toArray()
        vector.subtract(vector)
        assertThat(array).containsExactly(54, -23, 9, -9, 0)
    }

    @Test
    fun toIntVector_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.toIntVector()).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun toIntVector_whenNotEmpty() {
        val longVector = longVectorOf(-73, 1, -17, 14, 26)
        val intVector = longVector.toIntVector()
        longVector.subtract(longVector)
        assertThat(intVector).isEqualTo(intVectorOf(-73, 1, -17, 14, 26))
    }

    @Test
    fun toString_whenEmpty() {
        val vector = longVectorOf()
        assertThat(vector.toString()).isEqualTo("<>")
    }

    @Test
    fun toString_whenNotEmpty() {
        val vector = longVectorOf(43, 0, 80, -41, 38)
        assertThat(vector.toString()).isEqualTo("<43, 0, 80, -41, 38>")
    }

    @Test
    fun equals_nullVector() {
        val vector = longVectorOf(1, 2, 3)
        val other: LongVector? = null
        assertThat(vector).isNotEqualTo(other)
    }

    @Test
    fun equals_differentDimensions() {
        val vector = longVectorOf(1, 2, 3)
        val other = longVectorOf(1, 2, 3, 4)
        assertThat(vector).isNotEqualTo(other)
    }

    @Test
    fun equals_differentComponents() {
        val vector = longVectorOf(1, 2, 3)
        val other = longVectorOf(3, 2, 1)
        assertThat(vector).isNotEqualTo(other)
    }

    @Test
    fun hashCode_whenEmpty() {
        val hashMap = hashMapOf(longVectorOf() to "foo")
        assertThat(hashMap[longVectorOf()]).isEqualTo("foo")
        assertThat(hashMap[longVectorOf(0)]).isNull()
    }

    @Test
    fun hashCode_whenNotEmpty() {
        val hashMap = hashMapOf(longVectorOf(65, -54, 0, -51, 68) to "bar")
        assertThat(hashMap[longVectorOf(65, -54, 0, -51, 68)]).isEqualTo("bar")
        assertThat(hashMap[longVectorOf(65, -54, 0, -51)]).isNull()
        assertThat(hashMap[longVectorOf(65, -51, 0, -54, 68)]).isNull()
        assertThat(hashMap[longVectorOf(65, -54, 0, -51, 68, 68)]).isNull()
    }

    @Test
    fun constructor_noInit_negativeDimension() {
        assertThrows<IllegalArgumentException> { LongVector(-1) }
    }

    @Test
    fun constructor_noInit_zeroDimension() {
        val vector = LongVector(0)
        assertThat(vector).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun constructor_noInit_positiveDimension() {
        val vector = LongVector(3)
        assertThat(vector).isEqualTo(longVectorOf(0, 0, 0))
    }

    @Test
    fun constructor_withInit_negativeDimension() {
        assertThrows<IllegalArgumentException> { LongVector(-1) { it.toLong() } }
    }

    @Test
    fun constructor_withInit_zeroDimension() {
        val vector = LongVector(0) { it.toLong() }
        assertThat(vector).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun constructor_withInit_positiveDimension() {
        val vector = LongVector(3) { (it + 1L) * 2L - 3L }
        assertThat(vector).isEqualTo(longVectorOf(-1, 1, 3))
    }

    @Test
    fun toVector_emptyArray() {
        val array = longArrayOf()
        assertThat(array.toVector()).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun toVector_nonEmptyArray() {
        val array = longArrayOf(89, 23, 17, 34, -43)
        val vector = array.toVector()
        array.fill(0)
        assertThat(vector).isEqualTo(longVectorOf(89, 23, 17, 34, -43))
    }
}
