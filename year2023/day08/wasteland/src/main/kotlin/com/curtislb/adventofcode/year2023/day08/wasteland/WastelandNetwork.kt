package com.curtislb.adventofcode.year2023.day08.wasteland

import com.curtislb.adventofcode.common.io.forEachSection
import java.io.File

/**
 * A network of nodes and a list of instructions for navigating through the haunted wasteland.
 *
 * @property instructions A list of instructions that must be followed in order to move through the
 *  wasteland.
 * @property nodePaths A map from each node in the network to a pair of nodes that can be reached by
 *  following a left or right [Instruction] (respectively) from that node.
 *
 * @constructor Creates a new instance of [WastelandNetwork] with the given [instructions] and
 *  [nodePaths].
 */
class WastelandNetwork(
    private val instructions: List<Instruction>,
    private val nodePaths: Map<String, Pair<String, String>>
) {
    /**
     * All unique nodes in the network.
     */
    val nodes: Set<String>
        get() = nodePaths.keys

    /**
     * Returns the number of steps needed to reach a node for which the [isGoal] function returns
     * `true`, starting from the given [source] node.
     *
     * @throws IllegalArgumentException If [source] is not a node in the network.
     * @throws IllegalStateException If there are no paths from [source] or any intermediary node.
     */
    fun distance(source: String, isGoal: (node: String) -> Boolean): Long {
        require(source in nodePaths) { "Source node must be in the network: $source" }

        var node = source
        var index = 0
        var stepCount = 0L
        while (!isGoal(node)) {
            // Follow the current left/right instruction
            val paths = nodePaths[node] ?: error("No paths from node: $node")
            node = when (instructions[index]) {
                Instruction.LEFT -> paths.first
                Instruction.RIGHT -> paths.second
            }

            // Advance the instruction index and step count
            index = (index + 1) % instructions.size
            stepCount++
        }

        return stepCount
    }

    companion object {
        /**
         * A regex used to parse the available paths for each network node.
         */
        private val PATHS_REGEX = Regex("""(\w+) = \((\w+), (\w+)\)""")

        /**
         * Returns a [WastelandNetwork] with instructions and node paths read from the given [file].
         *
         * The [file] must have the following format, where `instructions` is an ordered sequence of
         * characters representing [Instruction]s, and each `node` line represents a node in the
         * network and the nodes that can be reached by traveling `left` or `right` from it:
         *
         * ```
         * $instructions
         *
         * $node1 = ($left1, $right1)
         * $node2 = ($left2, $right2)
         * ...
         * $nodeN = ($leftN, $rightN)
         * ```
         *
         * @throws IllegalArgumentException If [file] has the wrong format.
         */
        fun fromFile(file: File): WastelandNetwork {
            val instructions = mutableListOf<Instruction>()
            val nodePaths = mutableMapOf<String, Pair<String, String>>()
            file.forEachSection { lines ->
                if (instructions.isEmpty()) {
                    // Read instructions from the first line
                    for (char in lines[0]) {
                        instructions.add(Instruction.fromChar(char))
                    }
                } else {
                    // Read node paths from subsequent lines
                    for (line in lines) {
                        val matchResult = PATHS_REGEX.matchEntire(line)
                        require(matchResult != null) { "Malformed node paths: $line" }
                        val (node, left, right) = matchResult.destructured
                        nodePaths[node] = Pair(left, right)
                    }
                }
            }
            return WastelandNetwork(instructions, nodePaths)
        }
    }
}
