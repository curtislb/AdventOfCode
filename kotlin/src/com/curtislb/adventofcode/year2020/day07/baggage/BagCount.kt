package com.curtislb.adventofcode.year2020.day07.baggage

/**
 * TODO
 */
data class BagCount(val bagType: String, val count: Int) {
    override fun toString(): String = "$count $bagType bag${if (count != 1) "s" else ""}"

    companion object {
        /**
         * TODO
         */
        private val MATCH_REGEX = Regex("""(\d+)(\D+)""")

        /**
         * TODO
         */
        fun from(string: String): BagCount {
            val matchGroups = MATCH_REGEX.matchEntire(string.trim())?.groupValues
            require(matchGroups != null && matchGroups.size == 3) { "Malformed bag count string: $string" }
            return BagCount(matchGroups[2].trim(), matchGroups[1].trim().toInt())
        }
    }
}
