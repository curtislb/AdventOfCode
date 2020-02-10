package com.curtislb.adventofcode.year2019.day01.fuel

/**
 * Returns the amount of fuel required to launch an object of a given [mass].
 *
 * This calculation does not take into account the additional mass of the fuel itself. Instead, the method
 * [calculateTotalFuel] should be used for this purpose.
 */
fun calculateFuel(mass: Int): Int = (mass / 3) - 2
