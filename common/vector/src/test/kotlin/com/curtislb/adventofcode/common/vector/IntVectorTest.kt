package com.curtislb.adventofcode.common.vector

import com.curtislb.adventofcode.common.testing.isApproximately
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [IntVector].
 */
class IntVectorTest {
    @Test
    fun dimension_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.dimension).isEqualTo(0)
    }

    @Test
    fun dimension_withOneComponent() {
        val vector = intVectorOf(7)
        assertThat(vector.dimension).isEqualTo(1)
    }

    @Test
    fun dimension_withMultipleComponents() {
        val vector = intVectorOf(75, 83, -77, -80, -46)
        assertThat(vector.dimension).isEqualTo(5)
    }

    @Test
    fun getX_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.x }
    }

    @Test
    fun getX_withOneComponent() {
        val vector = intVectorOf(27)
        assertThat(vector.x).isEqualTo(27)
    }

    @Test
    fun getX_withTwoComponents() {
        val vector = intVectorOf(-16, -9)
        assertThat(vector.x).isEqualTo(-16)
    }

    @Test
    fun setX_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.x = 0 }
    }

    @Test
    fun setX_withOneComponent() {
        val vector = intVectorOf(0)
        vector.x = 2
        assertThat(vector).isEqualTo(intVectorOf(2))
    }

    @Test
    fun setX_withTwoComponents() {
        val vector = intVectorOf(38, 54)
        vector.x = 56
        assertThat(vector).isEqualTo(intVectorOf(56, 54))
    }

    @Test
    fun getY_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.y }
    }

    @Test
    fun getY_withOneComponent() {
        val vector = intVectorOf(4)
        assertThrows<IndexOutOfBoundsException> { vector.y }
    }

    @Test
    fun getY_withTwoComponents() {
        val vector = intVectorOf(80, -78)
        assertThat(vector.y).isEqualTo(-78)
    }

    @Test
    fun getY_withThreeComponents() {
        val vector = intVectorOf(-69, 50, 53)
        assertThat(vector.y).isEqualTo(50)
    }

    @Test
    fun setY_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.y = 2 }
    }

    @Test
    fun setY_withOneComponent() {
        val vector = intVectorOf(-78)
        assertThrows<IndexOutOfBoundsException> { vector.y = 11 }
    }

    @Test
    fun setY_withTwoComponents() {
        val vector = intVectorOf(-19, 79)
        vector.y = 99
        assertThat(vector).isEqualTo(intVectorOf(-19, 99))
    }

    @Test
    fun setY_withThreeComponents() {
        val vector = intVectorOf(64, 35, -27)
        vector.y = -26
        assertThat(vector).isEqualTo(intVectorOf(64, -26, -27))
    }

    @Test
    fun getZ_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.z }
    }

    @Test
    fun getZ_withOneComponent() {
        val vector = intVectorOf(-9)
        assertThrows<IndexOutOfBoundsException> { vector.z }
    }

    @Test
    fun getZ_withTwoComponents() {
        val vector = intVectorOf(-23, 83)
        assertThrows<IndexOutOfBoundsException> { vector.z }
    }

    @Test
    fun getZ_withThreeComponents() {
        val vector = intVectorOf(-91, 31, 44)
        assertThat(vector.z).isEqualTo(44)
    }

    @Test
    fun getZ_withFourComponents() {
        val vector = intVectorOf(-40, 65, 59, -51)
        assertThat(vector.z).isEqualTo(59)
    }

    @Test
    fun setZ_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector.z = 1 }
    }

    @Test
    fun setZ_withOneComponent() {
        val vector = intVectorOf(97)
        assertThrows<IndexOutOfBoundsException> { vector.z = 6 }
    }

    @Test
    fun setZ_withTwoComponents() {
        val vector = intVectorOf(-90, 2)
        assertThrows<IndexOutOfBoundsException> { vector.z = -5 }
    }

    @Test
    fun setZ_withThreeComponents() {
        val vector = intVectorOf(-77, -62, 23)
        vector.z = 66
        assertThat(vector).isEqualTo(intVectorOf(-77, -62, 66))
    }

    @Test
    fun setZ_withFourComponents() {
        val vector = intVectorOf(-62, 61, -92, -87)
        vector.z = -49
        assertThat(vector).isEqualTo(intVectorOf(-62, 61, -49, -87))
    }

    @Test
    fun get_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector[0] }
    }

    @Test
    fun get_indexInBounds() {
        val vector = intVectorOf(69, -60, -99, 13, 39)
        assertThat(vector[3]).isEqualTo(13)
    }

    @Test
    fun get_indexTooSmall() {
        val vector = intVectorOf(-51, -48, -71, 53, 46)
        assertThrows<IndexOutOfBoundsException> { vector[-1] }
    }

    @Test
    fun get_indexTooLarge() {
        val vector = intVectorOf(-40, 8, -91, 51, -90)
        assertThrows<IndexOutOfBoundsException> { vector[5] }
    }

    @Test
    fun set_whenEmpty() {
        val vector = intVectorOf()
        assertThrows<IndexOutOfBoundsException> { vector[0] = -9 }
    }

    @Test
    fun set_indexInBounds() {
        val vector = intVectorOf(47, 61, 59, -85, -12)
        vector[4] = 23
        assertThat(vector).isEqualTo(intVectorOf(47, 61, 59, -85, 23))
    }

    @Test
    fun set_indexTooSmall() {
        val vector = intVectorOf(71, 16, 30, 58, -68)
        assertThrows<IndexOutOfBoundsException> { vector[-1] = -49 }
    }

    @Test
    fun set_indexTooLarge() {
        val vector = intVectorOf(21, -65, -9, -83, 71)
        assertThrows<IndexOutOfBoundsException> { vector[5] = 80 }
    }

    @Test
    fun plus_bothEmpty() {
        val left = intVectorOf()
        val right = intVectorOf()
        assertThat(left + right).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun plus_leftEmpty() {
        val left = intVectorOf()
        val right = intVectorOf(1)
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_rightEmpty() {
        val left = intVectorOf(1)
        val right = intVectorOf()
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_leftDimensionSmaller() {
        val left = intVectorOf(86, -25)
        val right = intVectorOf(62, 49, -74)
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_rightDimensionSmaller() {
        val left = intVectorOf(-7, 98, -95)
        val right = intVectorOf(16, -47)
        assertThrows<IllegalArgumentException> { left + right }
    }

    @Test
    fun plus_bothSameDimension() {
        val left = intVectorOf(47, 61, 59, -85, -12)
        val right = intVectorOf(-75, 26, 2, 23, -17)
        assertThat(left + right)
            .isEqualTo(intVectorOf(-28, 87, 61, -62, -29))
            .isNotSameAs(left)
            .isNotSameAs(right)
    }

    @Test
    fun minus_bothEmpty() {
        val left = intVectorOf()
        val right = intVectorOf()
        assertThat(left - right).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun minus_leftEmpty() {
        val left = intVectorOf()
        val right = intVectorOf(1)
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_rightEmpty() {
        val left = intVectorOf(1)
        val right = intVectorOf()
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_leftDimensionSmaller() {
        val left = intVectorOf(17, 33)
        val right = intVectorOf(-52, -3, 36)
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_rightDimensionSmaller() {
        val left = intVectorOf(-23, -63, -69)
        val right = intVectorOf(58, 51)
        assertThrows<IllegalArgumentException> { left - right }
    }

    @Test
    fun minus_bothSameDimension() {
        val left = intVectorOf(70, 82, 63, 15, 53)
        val right = intVectorOf(70, 50, 69, -28, 92)
        assertThat(left - right)
            .isEqualTo(intVectorOf(0, 32, -6, 43, -39))
            .isNotSameAs(left)
            .isNotSameAs(right)
    }

    @Test
    fun unaryMinus_whenEmpty() {
        val vector = intVectorOf()
        assertThat(-vector).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun unaryMinus_whenNotEmpty() {
        val vector = intVectorOf(79, 0, 75, -99, -3)
        assertThat(-vector).isEqualTo(intVectorOf(-79, 0, -75, 99, 3))
    }

    @Test
    fun dot_bothEmpty() {
        val left = intVectorOf()
        val right = intVectorOf()
        assertThat(left dot right).isEqualTo(0L)
    }

    @Test
    fun dot_leftEmpty() {
        val left = intVectorOf()
        val right = intVectorOf(1)
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_rightEmpty() {
        val left = intVectorOf(1)
        val right = intVectorOf()
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_leftDimensionSmaller() {
        val left = intVectorOf(96, -91)
        val right = intVectorOf(75, -78, -32)
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_rightDimensionSmaller() {
        val left = intVectorOf(-48, -52, -94)
        val right = intVectorOf(32, -16)
        assertThrows<IllegalArgumentException> { left dot right }
    }

    @Test
    fun dot_bothSameDimension() {
        val left = intVectorOf(21, -8, -18, 19, 0)
        val right = intVectorOf(-14, 24, -15, 25, 4)
        assertThat(left dot right).isEqualTo(259L)
    }

    @Test
    fun cross_leftDimensionTooSmall() {
        val left = intVectorOf(-18, -10)
        val right = intVectorOf(-5, -23, 24)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_leftDimensionTooLarge() {
        val left = intVectorOf(-6, 16, -8, -12)
        val right = intVectorOf(-10, -3, 6)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_rightDimensionTooSmall() {
        val left = intVectorOf(23, 20, -16)
        val right = intVectorOf(-5, 18)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_rightDimensionTooLarge() {
        val left = intVectorOf(-15, -17, 17)
        val right = intVectorOf(-7, 19, 20, -20)
        assertThrows<IllegalArgumentException> { left cross right }
    }

    @Test
    fun cross_bothThreeDimensional() {
        val left = intVectorOf(-22, 24, 15)
        val right = intVectorOf(10, -21, -9)
        assertThat(left cross right)
            .isEqualTo(intVectorOf(99, -48, 222))
            .isNotSameAs(left)
            .isNotSameAs(right)
    }

    @Test
    fun add_bothEmpty() {
        val vector = intVectorOf()
        val other = intVectorOf()
        vector.add(other)
        assertThat(vector).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun add_thisEmpty() {
        val vector = intVectorOf()
        val other = intVectorOf(1)
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_otherEmpty() {
        val vector = intVectorOf(1)
        val other = intVectorOf()
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_thisDimensionSmaller() {
        val vector = intVectorOf(19, 7)
        val other = intVectorOf(-15, -25, 15)
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_otherDimensionSmaller() {
        val vector = intVectorOf(11, 15, 6)
        val other = intVectorOf(15, -21)
        assertThrows<IllegalArgumentException> { vector.add(other) }
    }

    @Test
    fun add_bothSameDimension() {
        val vector = intVectorOf(-17, -3, 35, 4, 47)
        val other = intVectorOf(87, 20, 45, -79, -57)
        vector.add(other)
        assertThat(vector).isEqualTo(intVectorOf(70, 17, 80, -75, -10))
    }

    @Test
    fun subtract_bothEmpty() {
        val vector = intVectorOf()
        val other = intVectorOf()
        vector.subtract(other)
        assertThat(vector).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun subtract_thisEmpty() {
        val vector = intVectorOf()
        val other = intVectorOf(1)
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_otherEmpty() {
        val vector = intVectorOf(1)
        val other = intVectorOf()
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_thisDimensionSmaller() {
        val vector = intVectorOf(-40, 90)
        val other = intVectorOf(92, -98, 95)
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_otherDimensionSmaller() {
        val vector = intVectorOf(23, -3, -20)
        val other = intVectorOf(8, 38)
        assertThrows<IllegalArgumentException> { vector.subtract(other) }
    }

    @Test
    fun subtract_bothSameDimension() {
        val vector = intVectorOf(40, 72, -56, -31, 94)
        val other = intVectorOf(-31, 9, 65, 9, -46)
        vector.subtract(other)
        assertThat(vector).isEqualTo(intVectorOf(71, 63, -121, -40, 140))
    }

    @Test
    fun componentSum_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.componentSum()).isEqualTo(0)
    }

    @Test
    fun componentSum_whenNotEmpty() {
        val vector = intVectorOf(-41, 13, -69, 76, 42)
        assertThat(vector.componentSum()).isEqualTo(21)
    }

    @Test
    fun componentSumOf_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.componentSumOf { it - 5 }).isEqualTo(0)
    }

    @Test
    fun componentSumOf_whenNotEmpty() {
        val vector = intVectorOf(-4, -61, 53, 34, 33)
        assertThat(vector.componentSumOf { it - 5 }).isEqualTo(30)
    }

    @Test
    fun magnitude_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.magnitude()).isApproximately(0.0)
    }

    @Test
    fun magnitude_withSinglePositiveComponent() {
        val vector = intVectorOf(8)
        assertThat(vector.magnitude()).isApproximately(8.0)
    }

    @Test
    fun magnitude_withSingleNegativeComponent() {
        val vector = intVectorOf(-53)
        assertThat(vector.magnitude()).isApproximately(53.0)
    }

    @Test
    fun magnitude_withMultipleComponents() {
        val vector = intVectorOf(70, 75, -37, 79, -12)
        assertThat(vector.magnitude()).isApproximately(135.199852)
    }

    @Test
    fun neighbors_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.neighbors()).isEmpty()
    }

    @Test
    fun neighbors_withOneComponent() {
        val vector = intVectorOf(10)
        assertThat(vector.neighbors())
            .noneMatch { it === vector }
            .containsExactlyInAnyOrder(intVectorOf(9), intVectorOf(11))
    }

    @Test
    fun neighbors_withTwoComponents() {
        val vector = intVectorOf(-47, 52)
        assertThat(vector.neighbors())
            .noneMatch { it === vector }
            .containsExactlyInAnyOrder(
                intVectorOf(-46, 51),
                intVectorOf(-46, 52),
                intVectorOf(-46, 53),
                intVectorOf(-47, 51),
                intVectorOf(-47, 53),
                intVectorOf(-48, 51),
                intVectorOf(-48, 52),
                intVectorOf(-48, 53)
            )
    }

    @Test
    fun neighbors_withThreeComponents() {
        val vector = intVectorOf(87, 1, -60)
        assertThat(vector.neighbors())
            .noneMatch { it === vector }
            .containsExactlyInAnyOrder(
                intVectorOf(86, 0, -61),
                intVectorOf(86, 0, -60),
                intVectorOf(86, 0, -59),
                intVectorOf(86, 1, -61),
                intVectorOf(86, 1, -60),
                intVectorOf(86, 1, -59),
                intVectorOf(86, 2, -61),
                intVectorOf(86, 2, -60),
                intVectorOf(86, 2, -59),
                intVectorOf(87, 0, -61),
                intVectorOf(87, 0, -60),
                intVectorOf(87, 0, -59),
                intVectorOf(87, 1, -61),
                intVectorOf(87, 1, -59),
                intVectorOf(87, 2, -61),
                intVectorOf(87, 2, -60),
                intVectorOf(87, 2, -59),
                intVectorOf(88, 0, -61),
                intVectorOf(88, 0, -60),
                intVectorOf(88, 0, -59),
                intVectorOf(88, 1, -61),
                intVectorOf(88, 1, -60),
                intVectorOf(88, 1, -59),
                intVectorOf(88, 2, -61),
                intVectorOf(88, 2, -60),
                intVectorOf(88, 2, -59),
            )
    }

    @Test
    fun copy_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.copy()).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun copy_whenNotEmpty() {
        val vector = intVectorOf(-33, 92, 30, 0,-45)
        assertThat(vector.copy()).isEqualTo(vector).isNotSameAs(vector)
    }

    @Test
    fun toArray_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.toArray()).isEmpty()
    }

    @Test
    fun toArray_whenNotEmpty() {
        val vector = intVectorOf(54, -23, 9, -9, 0)
        val array = vector.toArray()
        vector.subtract(vector)
        assertThat(array).containsExactly(54, -23, 9, -9, 0)
    }

    @Test
    fun toLongVector_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.toLongVector()).isEqualTo(LongVector.EMPTY)
    }

    @Test
    fun toLongVector_whenNotEmpty() {
        val intVector = intVectorOf(-73, 1, -17, 14, 26)
        val longVector = intVector.toLongVector()
        intVector.subtract(intVector)
        assertThat(longVector).isEqualTo(longVectorOf(-73, 1, -17, 14, 26))
    }

    @Test
    fun toString_whenEmpty() {
        val vector = intVectorOf()
        assertThat(vector.toString()).isEqualTo("<>")
    }

    @Test
    fun toString_whenNotEmpty() {
        val vector = intVectorOf(43, 0, 80, -41, 38)
        assertThat(vector.toString()).isEqualTo("<43, 0, 80, -41, 38>")
    }

    @Test
    fun equals_nullVector() {
        val vector = intVectorOf(1, 2, 3)
        val other: IntVector? = null
        assertThat(vector).isNotEqualTo(other)
    }

    @Test
    fun equals_differentDimensions() {
        val vector = intVectorOf(1, 2, 3)
        val other = intVectorOf(1, 2, 3, 4)
        assertThat(vector).isNotEqualTo(other)
    }

    @Test
    fun equals_differentComponents() {
        val vector = intVectorOf(1, 2, 3)
        val other = intVectorOf(3, 2, 1)
        assertThat(vector).isNotEqualTo(other)
    }

    @Test
    fun hashCode_whenEmpty() {
        val hashMap = hashMapOf(intVectorOf() to "foo")
        assertThat(hashMap[intVectorOf()]).isEqualTo("foo")
        assertThat(hashMap[intVectorOf(0)]).isNull()
    }

    @Test
    fun hashCode_whenNotEmpty() {
        val hashMap = hashMapOf(intVectorOf(65, -54, 0, -51, 68) to "bar")
        assertThat(hashMap[intVectorOf(65, -54, 0, -51, 68)]).isEqualTo("bar")
        assertThat(hashMap[intVectorOf(65, -54, 0, -51)]).isNull()
        assertThat(hashMap[intVectorOf(65, -51, 0, -54, 68)]).isNull()
        assertThat(hashMap[intVectorOf(65, -54, 0, -51, 68, 68)]).isNull()
    }

    @Test
    fun pseudoConstructor_noInit_negativeDimension() {
        assertThrows<IllegalArgumentException> { IntVector(-1) }
    }

    @Test
    fun pseudoConstructor_noInit_zeroDimension() {
        val vector = IntVector(0)
        assertThat(vector).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun pseudoConstructor_noInit_positiveDimension() {
        val vector = IntVector(3)
        assertThat(vector).isEqualTo(intVectorOf(0, 0, 0))
    }

    @Test
    fun pseudoConstructor_withInit_negativeDimension() {
        assertThrows<IllegalArgumentException> { IntVector(-1) { it } }
    }

    @Test
    fun pseudoConstructor_withInit_zeroDimension() {
        val vector = IntVector(0) { it }
        assertThat(vector).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun pseudoConstructor_withInit_positiveDimension() {
        val vector = IntVector(3) { (it + 1) * 2 - 3 }
        assertThat(vector).isEqualTo(intVectorOf(-1, 1, 3))
    }

    @Test
    fun toVector_emptyArray() {
        val array = intArrayOf()
        assertThat(array.toVector()).isEqualTo(IntVector.EMPTY)
    }

    @Test
    fun toVector_nonEmptyArray() {
        val array = intArrayOf(89, 23, 17, 34, -43)
        val vector = array.toVector()
        array.fill(0)
        assertThat(vector).isEqualTo(intVectorOf(89, 23, 17, 34, -43))
    }
}
