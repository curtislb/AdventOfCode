package com.curtislb.adventofcode.year2019.day14.chemistry

import com.curtislb.adventofcode.common.collection.mapToMap

/**
 * A chemical reaction that consumes one or more [reactants] to create a [product].
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
         * Returns a [Reaction] from a [reactionString] of the form `"$reactants => $product"`.
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
