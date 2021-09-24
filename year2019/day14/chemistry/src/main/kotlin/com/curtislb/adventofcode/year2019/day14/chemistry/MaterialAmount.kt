package com.curtislb.adventofcode.year2019.day14.chemistry

/**
 * A given [amount] of a [material] that may be consumed or produced in a reaction.
 *
 * @throws IllegalArgumentException If [material] contains one or more spaces.
 */
data class MaterialAmount(val material: String, val amount: Long) {
    init {
        require(!material.contains(' ')) { "Material string must not contain spaces: $material" }
    }

    override fun toString(): String = "$amount $material"

    companion object {
        /**
         * Returns a [MaterialAmount] from a [string] of the form `"$amount $material"`.
         */
        fun from(string: String): MaterialAmount {
            val (amountString, material) = string.trim().split(' ')
            return MaterialAmount(material, amountString.toLong())
        }
    }
}
