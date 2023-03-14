package com.curtislb.adventofcode.year2019.day14.chemistry

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.number.Fraction
import java.io.File

/**
 * A factory that can perform a variety of chemical reactions in order to transform materials.
 *
 * @param file A containing each [Reaction] that can be performed by this factory, one per line.
 *
 * @throws IllegalArgumentException If any material can be produced by more than one reaction.
 */
class Nanofactory(file: File) {
    /**
     * A map from each material that this factory can produce to the reaction that produces it.
     */
    private val reactions: Map<String, Reaction> = mutableMapOf<String, Reaction>().apply {
        file.forEachLine { line ->
            val reaction = Reaction.from(line)
            val material = reaction.product.material
            require(material !in this) {
                "Material is produced by two or more reactions: $material"
            }
            this[material] = reaction
        }
    }

    /**
     * Returns a map representing the amount of [rawMaterials] needed to create given quantities of
     * [products], or `null` if no amount of [rawMaterials] can be used to create [products].
     */
    fun findRequiredMaterials(
        rawMaterials: Set<String>,
        products: List<MaterialAmount>
    ): List<MaterialAmount>? {
        val requiredMaterials = Counter<String>()
        for ((material, amount) in products) {
            requiredMaterials[material] = amount
        }

        // Work backwards from the desired products to determine the amount of raw materials needed.
        while (requiredMaterials.getPositiveKeys().toSet() != rawMaterials) {
            val (requiredMaterial, requiredAmount) =
                requiredMaterials.getPositiveEntries().find { it.key !in rawMaterials } ?: break
            val reaction = reactions[requiredMaterial] ?: break
            val coefficient = Fraction(requiredAmount, reaction.product.amount).ceil()
            for ((material, amount) in reaction.reactants) {
                requiredMaterials[material] += coefficient * amount
            }
            requiredMaterials[reaction.product.material] -= coefficient * reaction.product.amount
        }

        return if (requiredMaterials.getPositiveKeys().toSet() == rawMaterials) {
            requiredMaterials.getPositiveEntries().map { (material, amount) ->
                MaterialAmount(material, amount)
            }
        } else {
            null
        }
    }

    override fun toString(): String = reactions.values.joinToString(separator = "\n")
}
