package com.adventofcode.curtislb.year2019.day06.orbits

/**
 * Represents a single planet in an orbital map.
 * @param name A [String] that uniquely identifies the planet.
 * @param parent The [Planet] orbited by this one.
 * @param children A [MutableList] of all planets that orbit this one.
 */
data class Planet(val name: String, var parent: Planet? = null, val children: MutableList<Planet> = mutableListOf())
