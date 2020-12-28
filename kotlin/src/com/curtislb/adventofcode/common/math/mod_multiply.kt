package com.curtislb.adventofcode.common.math

fun Long.modMultiply(multiplicand: Long, modulo: Long): Long {
    return ((this % modulo) * (multiplicand % modulo)) % modulo
}
