package com.adventofcode.curtislb.year2019.day14.chemistry

/**
 * TODO
 */
data class Material(val name: String, val amount: Int) {
    override fun toString() = "$amount $name"

    companion object {
        /**
         * TODO
         */
        fun fromString(materialString: String): Material {
            val (amount, name) = materialString.trim().split(' ')
            return Material(name, amount.toInt())
        }

        fun combineByName(materials: List<Material>): List<Material> {
            val materialAmounts = mutableMapOf<String, Int>()
            materials.forEach { materialAmounts[it.name] = materialAmounts.getOrDefault(it.name, 0) + it.amount }
            return materialAmounts.map { (name, amount) -> Material(name, amount) }
        }
    }
}
