package com.curtislb.adventofcode.year2019.day01.fuel

/**
 * Returns the amount of fuel required to launch an object of a given [mass].
 *
 * This function does not account for the mass of added fuel. For that, use [calculateTotalFuel].
 */
fun calculateFuel(mass: Int): Int = ((mass / 3) - 2).coerceAtLeast(0)

