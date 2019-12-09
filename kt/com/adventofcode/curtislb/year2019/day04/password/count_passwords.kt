package com.adventofcode.curtislb.year2019.day04.password

/**
 * TODO
 */
fun countValidPasswords(validator: PasswordValidator): Int {
    val nextDigits = validator.nextDigits
    return if (nextDigits.isEmpty()) {
        if (validator.isValid) 1 else 0
    } else {
        nextDigits.sumBy { countValidPasswords(validator.addDigit(it)) }
    }
}
