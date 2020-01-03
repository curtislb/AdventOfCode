package com.adventofcode.curtislb.year2019.day14.chemistry

import java.io.File

/**
 * TODO
 */
class Nanofactory(file: File) {
    /**
     * TODO
     */
    private val reactionsMap: Map<String, List<Reaction>>

    init {
        val mapBuilder = mutableMapOf<String, MutableList<Reaction>>()
        file.forEachLine {
            val reaction = Reaction.fromString(it)
            mapBuilder.getOrPut(reaction.product.name) { mutableListOf() }.add(reaction)
        }
        val entries = mapBuilder.map { (productName, reactions) -> Pair(productName, reactions.toList()) }
        reactionsMap = mapOf(*(entries.toTypedArray()))
    }

    fun getRequiredMaterials(rawMaterials: Set<String>, product: Material): Set<Material>? {
        var materials = setOf(product)
        while (materials.map { it.name }.toSet() != rawMaterials) {
            val newMaterials = Material.combineByName(materials.flatMap {
                reactionsMap[it.name]?.getOrNull(0)?.getRequiredMaterials(it.amount) ?: listOf(it)
            }).toSet()
            if (newMaterials == materials) {
                break
            }
            materials = newMaterials
        }
        return if (materials.map { it.name }.toSet() == rawMaterials) materials else null
    }

    override fun toString(): String {
        return reactionsMap.entries.joinToString(separator = "\n") { (_, reactions) ->
            reactions.joinToString(separator = "\n") { it.toString() }
        }
    }
}
