package com.adventofcode.curtislb.year2019.day01.fuel

/**
 * Determines the amount of fuel required to launch an object of a given mass.
 *
 * Note that this calculation does not take into account the additional mass of the fuel itself. Use the method
 * [calculateTotalFuel] instead for this purpose.
 *
 * @param mass The integer mass of the object to be launched.
 * @return The integer mass of fuel needed to launch this object alone.
 */
fun calculateFuel(mass: Int): Int = (mass / 3) - 2

/**
 * Determines the total amount of fuel required to launch an object of a given mass.
 *
 * In addition to the mass of the object itself, this calculation also takes into account the additional mass of the
 * fuel, so that the final combined mass of the object and added fuel can be launched.
 *
 * @param mass The integer mass of the object to be launched.
 * @return The total integer mass of fuel needed to launch this object.
 */
fun calculateTotalFuel(mass: Int): Int {
    var fuel = 0
    var remainingMass = mass
    while (remainingMass > 0) {
        remainingMass = calculateFuel(remainingMass).coerceAtLeast(0)
        fuel += remainingMass
    }
    return fuel
}
