package com.adventofcode.curtislb.year2019.day04.password

import kotlin.math.max

class InRangeValidator(val minPassword: String, val maxPassword: String) : PasswordValidator {
    private var currentLength: Int = 0
    private var currentPrefix: Int = 0
    private var currentMin: Int = 0
    private var currentMax: Int = 0

    private constructor(
        minPassword: String,
        maxPassword: String,
        currentPrefix: Int,
        currentMin: Int,
        currentMax: Int
    ) : this(minPassword, maxPassword) {
        this.currentPrefix = currentPrefix
        this.currentMin = currentMin
        this.currentMax = currentMax
    }

    private val minValue: Int get() = minPassword.toInt()

    private val maxValue: Int get() = maxPassword.toInt()

    private val nextMinDigit: Int?
        get() = if (currentLength < minPassword.length) minPassword[currentLength].toInt() else null

    private val nextMaxDigit: Int?
        get() = if (currentLength < maxPassword.length) maxPassword[currentLength].toInt() else null

    override val isValid: Boolean get() = currentPrefix in minValue..maxValue

    override val nextDigits: List<Int>
        get() {
            val minDigit = nextMinDigit ?: 0
            val maxDigit = nextMaxDigit ?: -1
            return (minDigit..maxDigit).toList()
        }

    override fun addDigit(digit: Int): PasswordValidator {
        val newPrefix = currentPrefix * 10 + digit
        val newMinDigit = nextMinDigit
        val newMin = if (newMinDigit != null) minValue * 10 + newMinDigit else minValue
        val newMaxDigit = nextMaxDigit
        val newMax = if (newMaxDigit != null) maxValue * 10 + newMaxDigit else maxValue
        return InRangeValidator(minPassword, maxPassword, newPrefix, newMin, newMax)
    }
}
