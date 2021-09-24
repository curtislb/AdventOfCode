package com.curtislb.adventofcode.year2019.day14.chemistry

/**
 * A chemical reaction that consumes one or more [reactants] to create a [product].
 *
 * @throws IllegalArgumentException If the list of [reactants] is empty.
 */
data class Reaction(val reactants: List<MaterialAmount>, val product: MaterialAmount) {
    init {
        require(reactants.isNotEmpty()) { "Reactant list must be non-empty: $reactants" }
    }

    override fun toString(): String = "${reactants.joinToString()} => $product"

    companion object {
        /**
         * Returns a [Reaction] from a [string] of the form `"$reactants => $product"`.
         */
        fun from(string: String): Reaction {
            val (reactantsString, productString) = string.split("=>")
            val reactants = reactantsString.trim().split(',').map { MaterialAmount.from(it) }
            return Reaction(reactants, MaterialAmount.from(productString))
        }
    }
}
