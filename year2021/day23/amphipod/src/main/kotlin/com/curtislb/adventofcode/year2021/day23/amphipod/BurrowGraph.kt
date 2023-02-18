package com.curtislb.adventofcode.year2021.day23.amphipod

import com.curtislb.adventofcode.common.collection.replace
import com.curtislb.adventofcode.common.graph.WeightedGraph
import com.curtislb.adventofcode.common.range.size

/**
 * A graph with edges from each possible [Burrow] state to new [Burrow] states that result from
 * moving a single amphipod to a valid space, weighted by the energy required to move it.
 */
internal object BurrowGraph : WeightedGraph<Burrow>() {
    override fun getEdges(node: Burrow): List<Edge<Burrow>> {
        val edges = mutableListOf<Edge<Burrow>>()

        // Try moving each amphipod in the hallway to its destination room
        node.hallway.forEachIndexed { hallwayIndex, amphipod ->
            if (amphipod != null) {
                val distance = node.distanceToRoom(hallwayIndex)
                if (distance != null) {
                    val newHallway = node.hallway.replace(hallwayIndex) { null }
                    val newRooms = node.rooms.replace(amphipod.roomIndex) { it + amphipod }
                    val newState = node.copy(hallway = newHallway, rooms = newRooms)
                    val energy = distance * amphipod.energyPerStep
                    edges.add(Edge(node = newState, weight = energy))
                }
            }
        }

        // Try moving the outermost amphipod in each room to each hallway space
        node.rooms.forEachIndexed { roomIndex, amphipods ->
            val amphipod = amphipods.lastOrNull()
            if (amphipod != null) {
                for (hallwayIndex in node.hallway.indices) {
                    val distance = node.distanceToHallway(roomIndex, hallwayIndex)
                    if (distance != null) {
                        val newHallway = node.hallway.replace(hallwayIndex) { amphipod }
                        val newRooms = node.rooms.replace(roomIndex) { it.subList(0, it.lastIndex) }
                        val newState = node.copy(hallway = newHallway, rooms = newRooms)
                        val energy = distance * amphipod.energyPerStep
                        edges.add(Edge(node = newState, weight = energy))
                    }
                }
            }
        }

        return edges
    }

    /**
     * Returns the number of spaces the outermost [Amphipod] in the given [roomIndex] must move to
     * reach the given [hallwayIndex], while obeying all rules in [Burrow.energyRequiredToOrganize].
     *
     * If the given [roomIndex] is empty or the amphipod can't move to the given [hallwayIndex],
     * this function instead returns `null`.
     */
    private fun Burrow.distanceToHallway(roomIndex: Int, hallwayIndex: Int): Int? {
        val room = rooms[roomIndex]
        if (room.isEmpty() || isOutsideRoom(hallwayIndex)) {
            return null
        }

        val startIndex = Burrow.roomToHallwayIndex(roomIndex)
        val moveRange = getHallwayMoveRange(startIndex, hallwayIndex)
        if (isObstructed(moveRange)) {
            return null
        }

        val distanceOutOfRoom = roomCapacity - room.lastIndex
        return distanceOutOfRoom + moveRange.size
    }

    /**
     * Returns the number of spaces the [Amphipod] at the given [hallwayIndex] must move to reach
     * the outermost unoccupied space of its destination room, while obeying all rules in
     * [Burrow.energyRequiredToOrganize].
     *
     * If there is no amphipod at the given [hallwayIndex] or it can't move to its destination room,
     * this function instead returns `null`.
     */
    private fun Burrow.distanceToRoom(hallwayIndex: Int): Int? {
        val amphipod = hallway[hallwayIndex] ?: return null
        val targetRoom = rooms[amphipod.roomIndex]
        if (targetRoom.size == roomCapacity || targetRoom.any { it != amphipod }) {
            return null
        }

        val targetIndex = Burrow.roomToHallwayIndex(amphipod.roomIndex)
        val moveRange = getHallwayMoveRange(hallwayIndex, targetIndex)
        if (isObstructed(moveRange)) {
            return null
        }

        val distanceIntoRoom = roomCapacity - targetRoom.size
        return moveRange.size + distanceIntoRoom
    }

    /**
     * Returns if any space in the given [hallwayIndexRange] is currently occupied.
     */
    private fun Burrow.isObstructed(hallwayIndexRange: IntRange): Boolean =
        hallwayIndexRange.any { hallway[it] != null }

    /**
     * Returns if the given [hallwayIndex] represents a space that is immediately outside a room.
     */
    private fun Burrow.isOutsideRoom(hallwayIndex: Int): Boolean =
        hallwayIndex != 0 && hallwayIndex != hallway.lastIndex && hallwayIndex % 2 == 0

    /**
     * Returns an index range representing all hallway spaces from [startIndex] to [targetIndex].
     */
    private fun getHallwayMoveRange(startIndex: Int, targetIndex: Int): IntRange = when {
        startIndex < targetIndex -> (startIndex + 1)..targetIndex
        startIndex > targetIndex -> targetIndex until startIndex
        else -> IntRange.EMPTY
    }
}
