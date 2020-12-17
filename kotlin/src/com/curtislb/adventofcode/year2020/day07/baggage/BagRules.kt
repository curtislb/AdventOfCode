package com.curtislb.adventofcode.year2020.day07.baggage

/**
 * TODO
 */
class BagRules(ruleStrings: List<String>) {
    /**
     * TODO
     */
    private val bagContents: Map<String, List<BagCount>>

    /**
     * TODO
     */
    private val bagContainers: Map<String, List<String>>

    init {
        val bagContentsMap = mutableMapOf<String, List<BagCount>>()
        val bagContainersMap = mutableMapOf<String, MutableList<String>>()
        ruleStrings.forEach { ruleString ->
            val tokens = ruleString.trimEnd('.').replace(REMOVAL_REGEX, "").split("contain").map { it.trim() }
            require(tokens.size == 2) { "Malformed rule string: $ruleString" }

            val (container, contentsString) = tokens
            val contents = if (contentsString.isBlank()) {
                emptyList()
            } else {
                contentsString.split(',').map { BagCount.from(it) }
            }

            bagContentsMap[container] = contents
            contents.forEach { bagCount ->
                bagContainersMap.getOrPut(bagCount.bagType) { mutableListOf() }.add(container)
            }
        }
        bagContents = bagContentsMap
        bagContainers = bagContainersMap
    }

    /**
     * TODO
     */
    fun countTotalBags(bagCount: BagCount): Int {
        return bagCount.count * (1 + (bagContents[bagCount.bagType]?.sumBy(::countTotalBags) ?: 0))
    }

    /**
     * TODO
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
         * TODO
         */
        private val REMOVAL_REGEX = Regex("""( *bags? *)|(no other)""")
    }
}
