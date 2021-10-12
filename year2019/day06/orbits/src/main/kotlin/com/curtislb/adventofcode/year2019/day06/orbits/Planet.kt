package com.curtislb.adventofcode.year2019.day06.orbits

/**
 * A single planet or other gravitational body in an orbital map.
 *
 * @param name A string that uniquely identifies the planet.
 * @param parent The planet orbited by this one.
 * @param children A list of all planets that orbit this one.
 */
data class Planet(
    val name: String,
    var parent: Planet? = null,
    val children: MutableList<Planet> = mutableListOf()
)
