package com.curtislb.adventofcode.year2020.day17.conway

import com.curtislb.adventofcode.common.collection.neighboringVectors

/**
 * A multidimensional cube used to power an [EnergySource].
 *
 * @param coordinates The set of spatial coordinates for this cube.
 */
class ConwayCube(private vararg val coordinates: Int) {
    /**
     * The number of spatial dimensions that define this cube.
     */
    val dimensionCount: Int = coordinates.size

    /**
     * Returns the spatial coordinate of this cube corresponding to the given index.
     */
    operator fun get(index: Int) = coordinates[index]

    /**
     * Returns all cubes adjacent to this one in one or more spatial dimensions.
     */
    fun neighbors(): List<ConwayCube> = coordinates.neighboringVectors().map { ConwayCube(*it) }

    override fun equals(other: Any?): Boolean =
        other is ConwayCube && coordinates.contentEquals(other.coordinates)

    override fun hashCode(): Int = coordinates.contentHashCode()

    override fun toString(): String = "ConwayCube(${coordinates.joinToString()})"
}
