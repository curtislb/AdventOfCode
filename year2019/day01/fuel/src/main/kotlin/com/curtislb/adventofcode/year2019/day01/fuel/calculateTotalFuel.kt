package com.curtislb.adventofcode.year2019.day01.fuel

/**
 * Returns the total amount of fuel required to launch an object of a given [mass].
 *
 * This function accounts for both the mass of the object and the mass of added fuel, so that the final combined mass
 * can be launched.
 */
fun calculateTotalFuel(mass: Int): Int {
    var fuel = 0
    var remainingMass = mass
    while (remainingMass > 0) {
        remainingMass = calculateFuel(remainingMass)
        fuel += remainingMass
    }
    return fuel
}
