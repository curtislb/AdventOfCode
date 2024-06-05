package com.curtislb.adventofcode.year2023.day22.bricks

import com.curtislb.adventofcode.common.collection.arrayQueueOf

/**
 * A brick that has settled into a vertical stack, with other bricks directly above/below it.
 *
 * @property id An integer ID that uniquely identifies the brick. Used to check equality.
 *
 * @constructor Creates a new instance of [SettledBrick] with the given [id].
 */
class SettledBrick(val id: Int) {
    /**
     * The set of bricks directly above this one in the stack.
     */
    val bricksAbove: MutableSet<SettledBrick> = mutableSetOf()

    /**
     * The set of bricks directly below this one in the stack.
     */
    val bricksBelow: MutableSet<SettledBrick> = mutableSetOf()

    /**
     * Returns `true` is this brick can be safely disintegrated without causing any of the bricks
     * above it to fall.
     */
    fun isSafeToRemove(): Boolean = bricksAbove.all { it.bricksBelow.size > 1 }

    /**
     * Returns the total number of bricks that would fall if this brick were disintegrated.
     */
    fun countSupportedBricks(): Int {
        // Mark this brick as one that would "fall" if it were disintegrated
        val supportedBricks = mutableSetOf(this)

        // Use BFS to find bricks above this one, marking any that would fall
        val brickQueue = arrayQueueOf(this)
        while (brickQueue.isNotEmpty()) {
            val brick = brickQueue.poll()
            for (brickAbove in brick.bricksAbove) {
                if (brickAbove.bricksBelow.all { it in supportedBricks }) {
                    supportedBricks.add(brickAbove)
                    brickQueue.offer(brickAbove)
                }
            }
        }

        // Exclude this brick from the final count
        return supportedBricks.size - 1
    }

    override fun toString(): String = "SettledBrick(id=$id)"

    override fun equals(other: Any?): Boolean = other is SettledBrick && id == other.id

    override fun hashCode(): Int = id.hashCode()
}
