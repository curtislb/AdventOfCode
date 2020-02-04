package com.adventofcode.curtislb.year2019.day14.chemistry

import com.adventofcode.curtislb.common.collection.mapToMap

/**
 * A chemical reaction that consumes one or more input materials to produce an output material.
 * @param reactants A [Map] from each input material to the amount of it that is consumed by this [Reaction].
 * @param product A [MaterialAmount] representing the name and amount of the material produced by this [Reaction].
 */
data class Reaction(val reactants: Map<String, Long>, val product: MaterialAmount) {
    override fun toString(): String {
        val reactantsString = reactants.entries.joinToString { (material, amount) ->
            MaterialAmount(material, amount).toString()
        }
        return "$reactantsString => $product"
    }

    companion object {
        /**
         * Creates a [Reaction] from a given [String] representation.
         * @param reactionString A [String] representation of a [Reaction].
         * @return A new [Reaction] corresponding to [reactionString].
         */
        fun from(reactionString: String): Reaction {
            val (reactantsString, productString) = reactionString.split("=>")
            val reactants = reactantsString.trim().split(',').mapToMap { reactantString ->
                MaterialAmount.from(reactantString).toPair()
            }
            return Reaction(reactants, MaterialAmount.from(productString))
        }
    }
}
