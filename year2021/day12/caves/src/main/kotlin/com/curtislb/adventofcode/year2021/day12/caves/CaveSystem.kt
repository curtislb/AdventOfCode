package com.curtislb.adventofcode.year2021.day12.caves

/**
 * A system of large and small caves, connected by undirected edges (representing passages).
 *
 * @param cavesString A string representing the cave system. Each line must be of the form
 *  `"${caveA}-${caveB}"`, indicating that an edge exists between `caveA` and `caveB`.
 *
 * @throws IllegalArgumentException If `cavesString` does not match the required format.
 */
class CaveSystem(cavesString: String) {
    /**
     * A map from each cave to all caves that are connected to it by an edge.
     */
    private val edges: Map<String, Set<String>> = mutableMapOf<String, MutableSet<String>>().apply {
        for (line in cavesString.trim().lines()) {
            val matchResult = EDGE_REGEX.matchEntire(line.trim())
            require(matchResult != null) { "Malformed edge string: $line" }

            // Add an edge from caveA to caveB, and vice versa
            val (caveA, caveB) = matchResult.destructured
            getOrPut(caveA) { mutableSetOf() }.add(caveB)
            getOrPut(caveB) { mutableSetOf() }.add(caveA)
        }
    }

    /**
     * Returns the number of valid paths through the cave system from [startCave] to [endCave].
     *
     * A path is valid if it visits [startCave] and [endCave] exactly once each and visits any other
     * previously-visited small cave at most [maxSmallRepeatCount] times. Other large caves can be
     * visited any number of times along a valid path.
     *
     * A cave is small if the first character of its name is a lowercase letter. All other caves are
     * large.
     *
     * @throws IllegalArgumentException If either of [startCave] or [endCave] is a large cave, or if
     *  [maxSmallRepeatCount] is negative.
     */
    fun countPaths(startCave: String, endCave: String, maxSmallRepeatCount: Int = 0): Int {
        require(startCave.isSmallCave()) { "Start cave must be a small cave: $startCave" }
        require(endCave.isSmallCave()) { "End cave must be a small cave: $endCave" }
        require(maxSmallRepeatCount >= 0) {
            "Maximum repeat count must be non-negative: $maxSmallRepeatCount"
        }

        return countPathsInternal(
            startCave,
            endCave,
            maxSmallRepeatCount,
            originalStartCave = startCave,
            visitedSmallCaves = mutableSetOf()
        )
    }

    /**
     * Recursive helper function for [countPaths].
     */
    private fun countPathsInternal(
        cave: String,
        endCave: String,
        maxSmallRepeatCount: Int,
        originalStartCave: String,
        visitedSmallCaves: MutableSet<String>
    ): Int =
        if (cave == endCave) {
            // Base case: There's only one valid path from a cave to itself
            1
        } else {
            // Mark this cave as visited if it isn't already
            val isFirstVisit = if (cave.isSmallCave()) {
                visitedSmallCaves.add(cave)
            } else {
                false
            }

            // Count the number of paths from this cave
            val pathCount = edges.getOrDefault(cave, emptySet()).sumOf { nextCave ->
                when {
                    // A valid path can't revisit the original start cave
                    nextCave == originalStartCave -> 0

                    // Count all paths from the next cave if it's unvisited
                    nextCave !in visitedSmallCaves ->
                        countPathsInternal(
                            nextCave,
                            endCave,
                            maxSmallRepeatCount,
                            originalStartCave,
                            visitedSmallCaves
                        )

                    // Count paths from the next (visited) cave if it can be repeated
                    nextCave in visitedSmallCaves && maxSmallRepeatCount > 0 ->
                        countPathsInternal(
                            nextCave,
                            endCave,
                            maxSmallRepeatCount - 1,
                            originalStartCave,
                            visitedSmallCaves
                        )

                    // The next cave is visited and can't be repeated
                    else -> 0
                }
            }

            // Un-mark this cave as visited before returning (if needed)
            if (isFirstVisit) {
                visitedSmallCaves.remove(cave)
            }

            pathCount
        }

    companion object {
        /**
         * A regex matching an undirected edge between two caves.
         */
        private val EDGE_REGEX = Regex("""([a-zA-Z]+)-([a-zA-Z]+)""")

        /**
         * Checks if this string name corresponds to a small cave, rather than a large one.
         *
         * See [countPaths] for the definitions of small and large caves.
         */
        private fun String.isSmallCave(): Boolean = first().isLowerCase()
    }
}
