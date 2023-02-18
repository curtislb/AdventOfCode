package com.curtislb.adventofcode.year2021.day23.amphipod

import com.curtislb.adventofcode.common.number.triangleNumber
import kotlin.math.abs

/**
 * A burrow that contains [Amphipod]s and consists of a single hallway connecting one or more rooms.
 *
 * @param hallway A list of all spaces in the hallway, each of which is represented by the type of
 *  [Amphipod] that occupies that space or `null` if the space is empty.
 * @param rooms A list of rooms in the burrow, each of which is represented by a list of [Amphipod]s
 *  that occupy the room, ordered from innermost to outermost.
*  @param roomCapacity The maximum number of [Amphipod]s that can occupy a single room at one time.
 */
data class Burrow(
    val hallway: List<Amphipod?>,
    val rooms: List<List<Amphipod>>,
    val roomCapacity: Int
) {
    override fun toString() = buildString {
        // Add the top row of wall spaces
        append("${"#".repeat(hallway.size + 2)}\n")

        // Add hallway spaces, including any occupied by amphipods
        val hallwayString = hallway.joinToString(separator = "") { it?.symbol?.toString() ?: "." }
        append("#${hallwayString}#\n")

        // Add room spaces (including amphipods) and surrounding walls
        for (indexInRoom in (0 until roomCapacity).reversed()) {
            // The first row of rooms has extra wall spaces on the left and right
            val prefix: String
            val postfix: String
            if (indexInRoom == roomCapacity - 1) {
                prefix = "###"
                postfix = "###\n"
            } else {
                prefix = "  #"
                postfix = "#\n"
            }

            append(
                rooms.joinToString(prefix = prefix, separator = "#", postfix = postfix) { room ->
                    room.getOrNull(indexInRoom)?.symbol?.toString() ?: "."
                }
            )
        }

        // Add the bottom row of wall spaces
        append("  ${"#".repeat(hallway.size - 2)}")
    }

    /**
     * Returns the minimum total energy required for amphipods in the burrow to move into their
     * assigned rooms, while obeying the following restrictions:
     *
     * - Amphipods can move up, down, left, or right so long as they are moving into an unoccupied
     *   open space.
     * - Amphipods will never stop on the space immediately outside any room. They can move into
     *   that space so long as they immediately continue moving.
     * - Amphipods will never move from the hallway into a room unless that room is their
     *   destination room and that room contains no amphipods which do not also have that room as
     *   their own destination. If an amphipod's starting room is not its destination room, it can
     *   stay in that room until it leaves the room.
     * - Once an amphipod stops moving in the hallway, it will stay in that spot until it can move
     *   into a room.
     */
    fun energyRequiredToOrganize(): Long? = BurrowGraph.aStarDistance(
        source = this,
        heuristic = { it.manhattanEnergyCost() },
        isGoal = { it.isOrganized() }
    )

    /**
     * Returns `true` if all [Amphipod]s in the burrow are in their assigned rooms.
     */
    private fun isOrganized(): Boolean {
        // Check if any amphipods are in the hallway
        if (hallway.any { it != null }) {
            return false
        }

        // Check if any room space is empty or contains the wrong type of amphipod
        rooms.forEachIndexed { index, amphipods ->
            if (amphipods.size < roomCapacity || amphipods.any { it.roomIndex != index }) {
                return false
            }
        }

        return true
    }

    /**
     * Returns the Manhattan distance from each [Amphipod] in the burrow to its final destination
     * space, multiplied by its [Amphipod.energyPerStep] value.
     */
    private fun manhattanEnergyCost(): Long {
        // Calculate energy for each amphipod to move to the innermost space of its room
        val hallwayEnergy = hallway.indices.sumOf { hallwayManhattanEnergyCost(it) }
        val roomEnergy = rooms.indices.sumOf { roomManhattanEnergyCost(it) }

        // Subtract the over-counted energy cost for each room
        val excessDistancePerRoom = triangleNumber(roomCapacity - 1)
        val excessEnergy = Amphipod.values().sumOf { it.energyPerStep } * excessDistancePerRoom
        return hallwayEnergy + roomEnergy - excessEnergy
    }

    /**
     * Returns the Manhattan distance from the [Amphipod] located at the given [hallwayIndex] to the
     * innermost space of its destination room, multiplied by its [Amphipod.energyPerStep] value.
     *
     * If there is no amphipod located at the given [hallwayIndex], this function instead returns 0.
     */
    private fun hallwayManhattanEnergyCost(hallwayIndex: Int): Long {
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
    private fun roomManhattanEnergyCost(roomIndex: Int): Long {
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

    companion object {
        /**
         * Regex used to parse the hallway row in the string representation of a [Burrow].
         */
        private val HALLWAY_REGEX = Regex("""#([.A-Z]+)#""")

        /**
         * Regex that matches an empty or occupied space in the string representation of a [Burrow].
         */
        private val SPACE_REGEX = Regex("""[.A-Z]""")

        /**
         * Returns the [Burrow] represented by the given [string].
         */
        fun fromString(string: String): Burrow {
            val lines = string.trim().lines()

            // Parse the hallway row, and add each space to a list
            val hallwayMatchResult = HALLWAY_REGEX.matchEntire(lines[1])
            require(hallwayMatchResult != null) { "Invalid burrow string:\n$string" }
            val hallwayString = hallwayMatchResult.groupValues[1]
            val hallway = hallwayString.map { if (it == '.') null else Amphipod.fromChar(it) }

            // Parse rooms, and add all amphipods to appropriate room lists
            val roomsCount = (hallway.size - 3) / 2
            val rooms = List(roomsCount) { mutableListOf<Amphipod>() }
            for (lineIndex in (2 until lines.lastIndex).reversed()) {
                val spaces = SPACE_REGEX.findAll(lines[lineIndex]).map { it.value[0] }.toList()
                require(spaces.size == roomsCount) { "Invalid burrow string:\n$string" }
                for (roomIndex in rooms.indices) {
                    val space = spaces[roomIndex]
                    if (space != '.') {
                        rooms[roomIndex].add(Amphipod.fromChar(space))
                    }
                }
            }

            val roomCapacity = lines.size - 3
            return Burrow(hallway, rooms, roomCapacity)
        }

        /**
         * Returns the hallway index of the space immediately outside the given [roomIndex].
         */
        internal fun roomToHallwayIndex(roomIndex: Int): Int = (roomIndex + 1) * 2
    }
}
