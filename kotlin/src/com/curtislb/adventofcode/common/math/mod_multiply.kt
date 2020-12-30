package com.curtislb.adventofcode.common.math

fun Long.modMultiply(multiplicand: Long, modulus: Long): Long {
    return ((this % modulus) * (multiplicand % modulus)) % modulus
}
