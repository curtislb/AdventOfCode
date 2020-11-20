package com.curtislb.adventofcode.year2019.day14.chemistry

/**
 * A chemical reaction that consumes one or more [reactants] to create a [product].
 */
data class Reaction(val reactants: List<MaterialAmount>, val product: MaterialAmount) {
    override fun toString(): String = "${reactants.joinToString()} => $product"

    companion object {
        /**
         * Returns a [Reaction] from a [reactionString] of the form `"$reactants => $product"`.
         */
        fun from(reactionString: String): Reaction {
            val (reactantsString, productString) = reactionString.split("=>")
            val reactants = reactantsString.trim().split(',').map { MaterialAmount.from(it) }
            return Reaction(reactants, MaterialAmount.from(productString))
        }
    }
}
