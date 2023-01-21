package com.curtislb.adventofcode.year2021.day23.amphipod

import com.curtislb.adventofcode.common.collection.replace
import com.curtislb.adventofcode.common.graph.AStar
import com.curtislb.adventofcode.common.graph.DirectedEdge
import com.curtislb.adventofcode.common.number.triangleNumber
import com.curtislb.adventofcode.common.range.size
import kotlin.math.abs

/**
 * A* search configuration for finding the minimum energy required to organize a given [Burrow].
 *
 * See [Burrow.energyRequiredToOrganize] for more details.
 */
internal object OrganizedBurrowSearch : AStar<Burrow>() {
    /**
     * Returns the Manhattan distance from each [Amphipod] in the burrow [node] to its destination
     * space, multiplied by its [Amphipod.energyPerStep] value.
     */
    override fun heuristic(node: Burrow): Long {
        // Calculate energy for each amphipod to move to the innermost space of its room
        val hallwayEnergy = node.hallway.indices.sumOf { node.hallwayManhattanEnergyCost(it) }
        val roomEnergy = node.rooms.indices.sumOf { node.roomManhattanEnergyCost(it) }

        // Subtract the over-counted energy cost for each room
        val excessDistancePerRoom = triangleNumber(node.roomCapacity - 1)
        val excessEnergy = Amphipod.values().sumOf { it.energyPerStep } * excessDistancePerRoom
        return hallwayEnergy + roomEnergy - excessEnergy
    }

    /**
     * Returns if the given burrow [node] is fully organized.
     */
    override fun isGoal(node: Burrow): Boolean {
        // Check if any amphipods are in the hallway
        if (node.hallway.any { it != null }) {
            return false
        }

        // Check if any room space is empty or contains the wrong type of amphipod
        node.rooms.forEachIndexed { index, amphipods ->
            if (amphipods.size < node.roomCapacity || amphipods.any { it.roomIndex != index }) {
                return false
            }
        }

        return true
    }

    /**
     * Returns a list of all burrow states that result from a single amphipod in the given burrow
     * [node] moving to a valid space, along with the associated energy cost.
     */
    override fun getEdges(node: Burrow): List<DirectedEdge<Burrow>> {
        val edges = mutableListOf<DirectedEdge<Burrow>>()

        node.hallway.forEachIndexed { hallwayIndex, amphipod ->
            if (amphipod != null) {
                val distance = node.distanceToRoom(hallwayIndex)
                if (distance != null) {
                    val newHallway = node.hallway.replace(hallwayIndex) { null }
                    val newRooms = node.rooms.replace(amphipod.roomIndex) { it + amphipod }
                    val newState = node.copy(hallway = newHallway, rooms = newRooms)
                    val energy = distance * amphipod.energyPerStep
                    edges.add(DirectedEdge(node = newState, weight = energy))
                }
            }
        }

        node.rooms.forEachIndexed { roomIndex, amphipods ->
            val amphipod = amphipods.lastOrNull()
            if (amphipod != null) {
                for (hallwayIndex in node.hallway.indices) {
                    val distance = node.distanceToHallway(roomIndex, hallwayIndex)
                    if (distance != null) {
                        val newHallway = node.hallway.replace(hallwayIndex) { amphipod }
                        val newRooms = node.rooms.replace(roomIndex) { room ->
                            room.subList(0, room.lastIndex)
                        }
                        val newState = node.copy(hallway = newHallway, rooms = newRooms)
                        val energy = distance * amphipod.energyPerStep
                        edges.add(DirectedEdge(node = newState, weight = energy))
                    }
                }
            }
        }

        return edges
    }

    /**
     * Returns the Manhattan distance from the [Amphipod] located at the given [hallwayIndex] to the
     * innermost space of its destination room, multiplied by its [Amphipod.energyPerStep] value.
     *
     * If there is no amphipod located at the given [hallwayIndex], this function instead returns 0.
     */
    private fun Burrow.hallwayManhattanEnergyCost(hallwayIndex: Int): Long {
        val amphipod = hallway[hallwayIndex] ?: return 0L
        val targetHallwayIndex = roomToHallwayIndex(amphipod.roomIndex)
        val hallwayDistance = abs(targetHallwayIndex - hallwayIndex)
        val manhattanDistance = hallwayDistance + roomCapacity
        return manhattanDistance.toLong() * amphipod.energyPerStep
    }

    /**
     * Returns the sum of Manhattan distances multiplied by [Amphipod.energyPerStep] values for each
     * [Amphipod] at the given [roomIndex] to move to the innermost space of its destination room.
     */
    private fun Burrow.roomManhattanEnergyCost(roomIndex: Int): Long {
        var totalEnergy = 0L
        rooms[roomIndex].forEachIndexed { indexInRoom, amphipod ->
            totalEnergy += if (amphipod.roomIndex == roomIndex) {
                // The amphipod is already in its destination room
                indexInRoom * amphipod.energyPerStep
            } else {
                // The amphipod must move out of this room and into its destination room
                val distanceOutOfRoom = roomCapacity - indexInRoom
                val startHallwayIndex = roomToHallwayIndex(roomIndex)
                val targetHallwayIndex = roomToHallwayIndex(amphipod.roomIndex)
                val hallwayDistance = abs(targetHallwayIndex - startHallwayIndex)
                val manhattanDistance = distanceOutOfRoom + hallwayDistance + roomCapacity
                manhattanDistance.toLong() * amphipod.energyPerStep
            }
        }
        return totalEnergy
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

        val startIndex = roomToHallwayIndex(roomIndex)
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

        val targetIndex = roomToHallwayIndex(amphipod.roomIndex)
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

    /**
     * Returns the hallway index of the space immediately outside the given [roomIndex].
     */
    private fun roomToHallwayIndex(roomIndex: Int): Int = (roomIndex + 1) * 2
}
