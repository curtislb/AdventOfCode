package com.adventofcode.curtislb.year2019.day14.chemistry

/**
 * The name and quantity of a material that may be consumed or produced in a reaction.
 * @param material The [String] name of this material.
 * @param amount An integer quantity of this material.
 */
data class MaterialAmount(val material: String, val amount: Long) {
    /**
     * Creates a [Pair] containing the name and amount of this material.
     * @return A new [Pair] `(material, amount)` corresponding to this [MaterialAmount].
     */
    fun toPair(): Pair<String, Long> = Pair(material, amount)

    override fun toString(): String = "$amount $material"

    companion object {
        /**
         * Creates a [MaterialAmount] from a given [String] representation.
         * @param materialAmountString A [String] of the form `"$amount $material"`.
         * @return A new [MaterialAmount] corresponding to [materialAmountString].
         */
        fun from(materialAmountString: String): MaterialAmount {
            val (amountString, material) = materialAmountString.trim().split(' ')
            return MaterialAmount(material, amountString.toLong())
        }
    }
}
