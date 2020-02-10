package com.curtislb.adventofcode.year2019.day14.chemistry

/**
 * A given [amount] of a [material] that may be consumed or produced in a reaction.
 */
data class MaterialAmount(val material: String, val amount: Long) {
    /**
     * Returns a [Pair] containing the name and amount of this material.
     */
    fun toPair(): Pair<String, Long> = Pair(material, amount)

    override fun toString(): String = "$amount $material"

    companion object {
        /**
         * Returns a [MaterialAmount] from a [materialAmountString] of the form `"$amount $material"`.
         */
        fun from(materialAmountString: String): MaterialAmount {
            val (amountString, material) = materialAmountString.trim().split(' ')
            return MaterialAmount(material, amountString.toLong())
        }
    }
}
