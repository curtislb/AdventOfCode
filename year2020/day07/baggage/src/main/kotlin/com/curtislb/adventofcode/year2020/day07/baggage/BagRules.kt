package com.curtislb.adventofcode.year2020.day07.baggage

/**
 * A set of rules indicating the number and type of bags (see [BagCount]) that must be contained in other bags.
 *
 * @param rulesString A string representing all bag rules. Each line may end with a `'.'` and must be a rule of the form
 *  `"$container bags contain $bagCount1, ..., $bagCountN"` or `"$container bags contain no other bags"`.
 */
class BagRules(rulesString: String) {
    /**
     * A map from each bag type to the bag counts it must contain.
     */
    private val bagContents: Map<String, List<BagCount>>

    /**
     * A map from each bag type to the bag types that must contain it.
     */
    private val bagContainers: Map<String, List<String>>

    init {
        val bagContentsMap = mutableMapOf<String, List<BagCount>>()
        val bagContainersMap = mutableMapOf<String, MutableList<String>>()
        rulesString.trim().lines().forEach { ruleString ->
            // Parse the relevant tokens from each rule string.
            val tokens = ruleString.trimEnd('.').replace(REMOVAL_REGEX, "").split("contain").map { it.trim() }
            require(tokens.size == 2) { "Malformed rule string: $ruleString" }

            // Convert tokens into the containing bag type and contained bag counts.
            val (container, contentsString) = tokens
            val contents = if (contentsString.isBlank()) {
                emptyList()
            } else {
                contentsString.split(',').map { BagCount.from(it) }
            }

            // Add the container and contents to the relevant maps.
            bagContentsMap[container] = contents
            contents.forEach { bagCount ->
                bagContainersMap.getOrPut(bagCount.bagType) { mutableListOf() }.add(container)
            }
        }
        bagContents = bagContentsMap
        bagContainers = bagContainersMap
    }

    /**
     * Returns the total number of bags contained by (and including) the given [bagCount].
     */
    fun countTotalBags(bagCount: BagCount): Int {
        return bagCount.count * (1 + (bagContents[bagCount.bagType]?.sumOf(::countTotalBags) ?: 0))
    }

    /**
     * Returns all unique bag types that recursively contain the given [bagType].
     */
    fun findBagsContaining(bagType: String): Set<String> {
        val containers = mutableSetOf<String>()
        var bags = setOf(bagType)
        do {
            bags = bags.flatMap { bagContainers[it] ?: emptyList() }.toSet()
            containers.addAll(bags)
        } while (bags.isNotEmpty())
        return containers
    }

    override fun toString(): String {
        return bagContents.map { (bagType, contents) ->
            "$bagType bags contain ${if (contents.isEmpty()) "no other bags" else contents.joinToString()}."
        }.joinToString(separator = "\n")
    }

    companion object {
        /**
         * A regex for matching and removing unnecessary portions of each rule string.
         */
        private val REMOVAL_REGEX = Regex("""( *bags? *)|(no other)""")
    }
}
