package com.adventofcode.curtislb.year2019.day14.chemistry

import com.adventofcode.curtislb.common.math.nextMultipleAtLeast

/**
 * TODO
 */
data class Reaction(val reactants: List<Material>, val product: Material) {
    /**
     * TODO
     */
    fun getRequiredMaterials(requiredProductAmount: Int): List<Material> {
        println("$this (need $requiredProductAmount)")
        val ratio = if (product.amount >= requiredProductAmount) {
            1
        } else {
            product.amount.nextMultipleAtLeast(requiredProductAmount) / product.amount
        }
        val result = reactants.map { Material(it.name, it.amount * ratio) }
        println(result)
        return result
    }

    override fun toString(): String = "${reactants.joinToString(separator = ", ") { it.toString() }} => $product"

    companion object {
        /**
         * TODO
         */
        fun fromString(reactionString: String): Reaction {
            val (reactantsString, productString) = reactionString.split("=>")
            val reactants = reactantsString.trim().split(',').map { Material.fromString(it) }
            val product = Material.fromString(productString)
            return Reaction(reactants, product)
        }
    }
}
