package com.curtislb.adventofcode.common.geometry

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [Cuboid] class.
 */
class CuboidTest {
    @Test
    fun volume_whenEmpty() {
        val cuboid = Cuboid.EMPTY
        assertThat(cuboid.volume).isEqualTo(0L)
    }

    @Test
    fun volume_withSingleCube() {
        val range = 42..42
        val cuboid = Cuboid(xRange = range, yRange = range, zRange = range)
        assertThat(cuboid.volume).isEqualTo(1L)
    }

    @Test
    fun volume_withMultipleCubes() {
        val xRange = -32..22
        val yRange = -86..-45
        val zRange = 5..7
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid.volume).isEqualTo(6930L)
    }

    @Test
    fun isEmpty_whenEmpty() {
        val cuboid = Cuboid.EMPTY
        assertThat(cuboid.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_whenAllRangesEmpty() {
        val xRange = IntRange.EMPTY
        val yRange = IntRange.EMPTY
        val zRange = IntRange.EMPTY
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_whenXRangeEmpty() {
        val xRange = IntRange.EMPTY
        val yRange = 0..2
        val zRange = 0..2
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_whenYRangeEmpty() {
        val xRange = 0..2
        val yRange = IntRange.EMPTY
        val zRange = 0..2
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_whenZRangeEmpty() {
        val xRange = 0..2
        val yRange = 0..2
        val zRange = IntRange.EMPTY
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_withSingleCube() {
        val range = 0..0
        val cuboid = Cuboid(xRange = range, yRange = range, zRange = range)
        assertThat(cuboid.isEmpty()).isFalse
    }

    @Test
    fun isEmpty_withMultipleCubes() {
        val xRange = -52..65
        val yRange = -80..1
        val zRange = 0..23
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid.isEmpty()).isFalse
    }

    @Test
    fun contains_bothEmpty() {
        val cuboid = Cuboid.EMPTY
        val other = Cuboid.EMPTY
        assertThat(other in cuboid).isTrue
    }

    @Test
    fun contains_thisEmpty() {
        val cuboid = Cuboid.EMPTY
        val other = Cuboid(xRange = 0..0, yRange = 0..0, zRange = 0..0)
        assertThat(other in cuboid).isFalse
    }

    @Test
    fun contains_otherEmpty() {
        val cuboid = Cuboid(xRange = 0..0, yRange = 0..0, zRange = 0..0)
        val other = Cuboid.EMPTY
        assertThat(other in cuboid).isTrue
    }

    @Test
    fun contains_self() {
        val xRange = -37..6
        val yRange = -55..31
        val zRange = 49..92
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid in cuboid).isTrue
    }

    @Test
    fun contains_otherIsSubset() {
        val cuboid = Cuboid(xRange = -2..2, yRange = 5..10, zRange = -12..-2)
        val other = Cuboid(xRange = -1..1, yRange = 6..9, zRange = -11..-3)
        assertThat(other in cuboid).isTrue
    }

    @Test
    fun contains_otherIsSuperset() {
        val cuboid = Cuboid(xRange = -1..1, yRange = 6..9, zRange = -11..-3)
        val other = Cuboid(xRange = -2..2, yRange = 5..10, zRange = -12..-2)
        assertThat(other in cuboid).isFalse
    }

    @Test
    fun overlap_bothEmpty() {
        val cuboid = Cuboid.EMPTY
        val other = Cuboid.EMPTY
        assertThat(cuboid overlap other).isEqualTo(Cuboid.EMPTY)
    }

    @Test
    fun overlap_thisEmpty() {
        val cuboid = Cuboid.EMPTY
        val other = Cuboid(xRange = -84..-45, yRange = -66..66, zRange = -15..92)
        assertThat(cuboid overlap other).isEqualTo(Cuboid.EMPTY)
    }

    @Test
    fun overlap_otherEmpty() {
        val cuboid = Cuboid(xRange = -49..9, yRange = -84..39, zRange = 10..58)
        val other = Cuboid.EMPTY
        assertThat(cuboid overlap other).isEqualTo(Cuboid.EMPTY)
    }

    @Test
    fun overlap_disjointXRange() {
        val yRange = -4..71
        val zRange = 69..92
        val cuboid = Cuboid(xRange = -1..0, yRange = yRange, zRange = zRange)
        val other = Cuboid(xRange = 1..2, yRange = yRange, zRange = zRange)
        assertThat(cuboid overlap other).isEqualTo(Cuboid.EMPTY)
    }

    @Test
    fun overlap_disjointYRange() {
        val xRange = -65..32
        val zRange = -76..-2
        val cuboid = Cuboid(xRange = xRange, yRange = 1..2, zRange = zRange)
        val other = Cuboid(xRange = xRange, yRange = -1..0, zRange = zRange)
        assertThat(cuboid overlap other).isEqualTo(Cuboid.EMPTY)
    }

    @Test
    fun overlap_disjointZRange() {
        val xRange = -94..-33
        val yRange = 21..89
        val cuboid = Cuboid(xRange = xRange, yRange = yRange, zRange = 1..2)
        val other = Cuboid(xRange = xRange, yRange = yRange, zRange = 3..3)
        assertThat(cuboid overlap other).isEqualTo(Cuboid.EMPTY)
    }

    @Test
    fun overlap_self() {
        val xRange = 19..39
        val yRange = 40..55
        val zRange = -83..-52
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid overlap cuboid).isEqualTo(cuboid)
    }

    @Test
    fun overlap_otherIsSubset() {
        val cuboid = Cuboid(xRange = -2..2, yRange = 5..10, zRange = -12..-2)
        val other = Cuboid(xRange = -1..1, yRange = 6..9, zRange = -11..-3)
        assertThat(cuboid overlap other).isEqualTo(other)
    }

    @Test
    fun overlap_otherIsSuperset() {
        val cuboid = Cuboid(xRange = -1..1, yRange = 6..9, zRange = -11..-3)
        val other = Cuboid(xRange = -2..2, yRange = 5..10, zRange = -12..-2)
        assertThat(cuboid overlap other).isEqualTo(cuboid)
    }

    @Test
    fun overlap_singleCubeOverlap() {
        val cuboid = Cuboid(xRange = 0..5, yRange = 1..9, zRange = 2..8)
        val other = Cuboid(xRange = -6..0, yRange = 0..1, zRange = -4..2)
        assertThat(cuboid overlap other)
            .isEqualTo(Cuboid(xRange = 0..0, yRange = 1..1, zRange = 2..2))
    }

    @Test
    fun overlap_multipleCubeOverlap() {
        val cuboid = Cuboid(xRange = -85..-42, yRange = -91..76, zRange = 37..75)
        val other = Cuboid(xRange = -65..-4, yRange = -13..67, zRange = -35..68)
        assertThat(cuboid overlap other)
            .isEqualTo(Cuboid(xRange = -65..-42, yRange = -13..67, zRange = 37..68))
    }

    @Test
    fun forEachVoxel_whenEmpty() {
        val cuboid = Cuboid.EMPTY

        val voxels = mutableListOf<List<Int>>()
        cuboid.forEachVoxel { x, y, z -> voxels.add(listOf(x, y, z)) }

        assertThat(voxels).isEmpty()
    }

    @Test
    fun forEachVoxel_whenXRangeEmpty() {
        val xRange = IntRange.EMPTY
        val yRange = 1..2
        val zRange = -1..1
        val cuboid = Cuboid(xRange, yRange, zRange)

        val voxels = mutableListOf<List<Int>>()
        cuboid.forEachVoxel { x, y, z -> voxels.add(listOf(x, y, z)) }

        assertThat(voxels).isEmpty()
    }

    @Test
    fun forEachVoxel_whenYRangeEmpty() {
        val xRange = 1..2
        val yRange = IntRange.EMPTY
        val zRange = -1..1
        val cuboid = Cuboid(xRange, yRange, zRange)

        val voxels = mutableListOf<List<Int>>()
        cuboid.forEachVoxel { x, y, z -> voxels.add(listOf(x, y, z)) }

        assertThat(voxels).isEmpty()
    }

    @Test
    fun forEachVoxel_whenZRangeEmpty() {
        val xRange = -1..1
        val yRange = 1..2
        val zRange = IntRange.EMPTY
        val cuboid = Cuboid(xRange, yRange, zRange)

        val voxels = mutableListOf<List<Int>>()
        cuboid.forEachVoxel { x, y, z -> voxels.add(listOf(x, y, z)) }

        assertThat(voxels).isEmpty()
    }

    @Test
    fun forEachVoxel_withSingleCube() {
        val range = -23..-23
        val cuboid = Cuboid(xRange = range, yRange = range, zRange = range)

        val voxels = mutableListOf<List<Int>>()
        cuboid.forEachVoxel { x, y, z -> voxels.add(listOf(x, y, z)) }

        assertThat(voxels).containsExactly(listOf(-23, -23, -23))
    }

    @Test
    fun forEachVoxel_withMultipleCubes() {
        val xRange = 2..3
        val yRange = 8..8
        val zRange = -1..1
        val cuboid = Cuboid(xRange, yRange, zRange)

        val voxels = mutableListOf<List<Int>>()
        cuboid.forEachVoxel { x, y, z -> voxels.add(listOf(x, y, z)) }

        assertThat(voxels).containsExactlyInAnyOrder(
            listOf(2, 8, -1),
            listOf(2, 8, 0),
            listOf(2, 8, 1),
            listOf(3, 8, -1),
            listOf(3, 8, 0),
            listOf(3, 8, 1)
        )
    }

    @Test
    fun toString_whenEmpty() {
        val cuboid = Cuboid.EMPTY
        assertThat(cuboid.toString())
            .isEqualTo("Cuboid(xRange = 1..0, yRange = 1..0, zRange = 1..0)")
    }

    @Test
    fun toString_whenNotEmpty() {
        val xRange = -78..56
        val yRange = 7..76
        val zRange = -24..12
        val cuboid = Cuboid(xRange, yRange, zRange)
        assertThat(cuboid.toString())
            .isEqualTo("Cuboid(xRange = -78..56, yRange = 7..76, zRange = -24..12)")
    }

    @Test
    fun equals_nullCuboid() {
        val cuboid = Cuboid(xRange = 0..0, yRange = 0..0, zRange = 0..0)
        val other: Cuboid? = null
        assertThat(cuboid).isNotEqualTo(other)
    }

    @Test
    fun equals_thisEmpty() {
        val cuboid = Cuboid.EMPTY
        val other = Cuboid(xRange = 0..0, yRange = 0..0, zRange = 0..0)
        assertThat(cuboid).isNotEqualTo(other)
    }

    @Test
    fun equals_otherEmpty() {
        val cuboid = Cuboid(xRange = 0..0, yRange = 0..0, zRange = 0..0)
        val other = Cuboid.EMPTY
        assertThat(cuboid).isNotEqualTo(other)
    }

    @Test
    fun equals_differentXRange() {
        val yRange = -4..71
        val zRange = 69..92
        val cuboid = Cuboid(xRange = 0..2, yRange = yRange, zRange = zRange)
        val other = Cuboid(xRange = 1..2, yRange = yRange, zRange = zRange)
        assertThat(cuboid).isNotEqualTo(other)
    }

    @Test
    fun equals_differentYRange() {
        val xRange = -4..71
        val zRange = 69..92
        val cuboid = Cuboid(xRange = xRange, yRange = 0..2, zRange = zRange)
        val other = Cuboid(xRange = xRange, yRange = 1..2, zRange = zRange)
        assertThat(cuboid).isNotEqualTo(other)
    }

    @Test
    fun equals_differentZRange() {
        val xRange = -4..71
        val yRange = 69..92
        val cuboid = Cuboid(xRange = xRange, yRange = yRange, zRange = 0..2)
        val other = Cuboid(xRange = xRange, yRange = yRange, zRange = 1..2)
        assertThat(cuboid).isNotEqualTo(other)
    }

    @Test
    fun hashCode_whenEmpty() {
        val hashMap = hashMapOf(Cuboid.EMPTY to "foo")
        assertThat(hashMap[Cuboid.EMPTY] == "foo")
        assertThat(hashMap[Cuboid(xRange = 0..0, yRange = 0..0, zRange = 0..0)]).isNull()
    }

    @Test
    fun hashCode_whenNotEmpty() {
        val xRange = 8..76
        val yRange = -69..17
        val zRange = -53..-33
        val hashMap = hashMapOf(Cuboid(xRange, yRange, zRange) to "bar")
        assertThat(hashMap[Cuboid(xRange, yRange, zRange)]).isEqualTo("bar")
        assertThat(hashMap[Cuboid(xRange = 1..2, yRange = 2..3, zRange = 3..4)]).isNull()
    }
}
