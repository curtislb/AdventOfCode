package com.curtislb.adventofcode.year2019.day01.fuel

/**
 * Returns the total amount of fuel required to launch an object of a given [mass].
 *
 * In addition to the mass of the object itself, this calculation also takes into account the additional mass of the
 * fuel, so that the final combined mass of the object and added fuel can be launched.
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
