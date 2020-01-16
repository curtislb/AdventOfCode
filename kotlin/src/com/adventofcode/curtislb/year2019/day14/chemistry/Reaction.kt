package com.adventofcode.curtislb.year2019.day14.chemistry

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
         * Creates a [Reaction] from the given [String] representation.
         * @param reactionString A [String] representation of a [Reaction].
         * @return A new [Reaction] corresponding to [reactionString].
         */
        fun fromString(reactionString: String): Reaction {
            val (reactantsString, productString) = reactionString.split("=>")
            val reactantsBuilder = mutableMapOf<String, Long>()
            reactantsString.trim().split(',').forEach { reactantString ->
                val reactant = MaterialAmount.fromString(reactantString)
                reactantsBuilder[reactant.material] = reactant.amount
            }
            return Reaction(reactantsBuilder, MaterialAmount.fromString(productString))
        }
    }
}
