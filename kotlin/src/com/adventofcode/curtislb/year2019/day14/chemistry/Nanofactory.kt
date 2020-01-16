package com.adventofcode.curtislb.year2019.day14.chemistry

import com.adventofcode.curtislb.common.collection.Counter
import com.adventofcode.curtislb.common.math.Fraction
import java.io.File

/**
 * A factory that can perform a number of chemical reactions in order to transform materials.
 * @param file A [File] in which each line represents a [Reaction] that can be performed by this [Nanofactory].
 */
class Nanofactory(file: File) {
    /**
     * A [Map] from each material that can be produced by this [Nanofactory] to the [Reaction] that will produce it.
     */
    private val reactions: Map<String, Reaction>

    init {
        val reactionsBuilder = mutableMapOf<String, Reaction>()
        file.forEachLine { line ->
            val reaction = Reaction.fromString(line)
            val material = reaction.product.material
            if (material in reactionsBuilder) {
                throw IllegalArgumentException("Found more than one reaction to produce $material.")
            }
            reactionsBuilder[material] = reaction
        }
        reactions = reactionsBuilder
    }

    /**
     * Determines the raw material quantities required to produce certain amounts of other materials.
     * @param rawMaterials A [Set] of input materials (of arbitrary quantity) that do not need to be produced.
     * @param products A [Map] from each desired material to the amount of it that should be produced.
     * @return If all [products] can be produced starting from [rawMaterials], returns a [Map] from each raw material to
     *  the amount of it that is required. Otherwise, returns `null`.
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
