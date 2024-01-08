package com.curtislb.adventofcode.year2023.day15.hash

/**
 * An operation that may be performed on a lens as part of the HASHMAP process.
 */
sealed interface Operation {
    /**
     * A HASHMAP operation that corresponds to removing a lens (of any focal length) from its
     * assigned box.
     */
    data object Dash : Operation

    /**
     * A HASHMAP operation that corresponds to placing a lens with the specified focal length in its
     * assigned box, replacing any existing lens with the same label in the box.
     *
     * @property focalLength The focal length of the lens to place in its assigned box.
     *
     * @constructor Creates a new instance of the [Equals] operation with the given [focalLength].
     */
    data class Equals(val focalLength: Int) : Operation
}
