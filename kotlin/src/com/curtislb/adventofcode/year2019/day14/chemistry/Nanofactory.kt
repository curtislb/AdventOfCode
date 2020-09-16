package com.curtislb.adventofcode.year2019.day14.chemistry

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.math.Fraction
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
    private val reactions: Map<String, Reaction>

    init {
        val reactionsBuilder = mutableMapOf<String, Reaction>()
        file.forEachLine { line ->
            val reaction = Reaction.from(line)
            val material = reaction.product.material
            require(material !in reactionsBuilder) { "Material is produced by two or more reactions: $material" }
            reactionsBuilder[material] = reaction
        }
        reactions = reactionsBuilder
    }

    /**
     * Returns a map representing the amount of [rawMaterials] needed to create given quantities of [products], or
     * `null` if no amount of [rawMaterials] can be used to create [products].
     */
    fun getRequiredMaterials(rawMaterials: Set<String>, products: Map<String, Long>): Map<String, Long>? {
        val requiredMaterials = Counter(products)
        while (requiredMaterials.keysWithPositiveCount != rawMaterials) {
            val (requiredMaterial, requiredAmount) = requiredMaterials.entriesWithPositiveCount.find { (material, _) ->
                material !in rawMaterials
            } ?: break
            val reaction = reactions[requiredMaterial] ?: break
            val coefficient = Fraction(requiredAmount, reaction.product.amount).ceil()
            reaction.reactants.forEach { (material, amount) -> requiredMaterials[material] += coefficient * amount }
            requiredMaterials[reaction.product.material] -= coefficient * reaction.product.amount
        }
        requiredMaterials.clearNegativeCounts()
        return if (requiredMaterials.keysWithNonzeroCount == rawMaterials) requiredMaterials.toMap() else null
    }

    override fun toString(): String = reactions.values.joinToString(separator = "\n")
}
