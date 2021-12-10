rootProject.name = "AdventOfCode"

// Common utility libraries
include(
    ":common:collection",
    ":common:graph",
    ":common:grid",
    ":common:heap",
    ":common:intcode",
    ":common:io",
    ":common:math",
    ":common:parse",
    ":common:range",
    ":common:search",
    ":common:simulation",
    ":common:testing"
)

////////////////////////////////////////////////////////////////////////////////////////////////////
// Advent of Code 2019

// Day 1: The Tyranny of the Rocket Equation
include(":year2019:day01:fuel", ":year2019:day01:part1", ":year2019:day01:part2")

// Day 2: 1202 Program Alarm
include(":year2019:day02:part1", ":year2019:day02:part2")

// Day 3: Crossed Wires
include(":year2019:day03:part1", ":year2019:day03:part2", ":year2019:day03:wire")

// Day 4: Secure Container
include(":year2019:day04:part1", ":year2019:day04:part2", ":year2019:day04:password")

// Day 5: Sunny with a Chance of Asteroids
include(":year2019:day05:part1", ":year2019:day05:part2")

// Day 6: Universal Orbit Map
include(":year2019:day06:orbits", ":year2019:day06:part1", ":year2019:day06:part2")

// Day 7: Amplification Circuit
include(":year2019:day07:amplifier", ":year2019:day07:part1", ":year2019:day07:part2")

// Day 8: Space Image Format
include(":year2019:day08:image", ":year2019:day08:part1", ":year2019:day08:part2")

// Day 9: Sensor Boost
include(":year2019:day09:part1", ":year2019:day09:part2")

// Day 10: Monitoring Station
include(":year2019:day10:asteroid", ":year2019:day10:part1", ":year2019:day10:part2")

// Day 11: Space Police
include(":year2019:day11:painting", ":year2019:day11:part1", ":year2019:day11:part2")

// Day 12: The N-Body Problem
include(":year2019:day12:body", ":year2019:day12:part1", ":year2019:day12:part2")

// Day 13: Care Package
include(":year2019:day13:game", ":year2019:day13:part1", ":year2019:day13:part2")

// Day 14: Space Stoichiometry
include(":year2019:day14:chemistry", ":year2019:day14:part1", ":year2019:day14:part2")

// Day 15: Oxygen System
include(":year2019:day15:part1", ":year2019:day15:part2", ":year2019:day15:repair")

// Day 16: Flawed Frequency Transmission
include(":year2019:day16:fft", ":year2019:day16:part1", ":year2019:day16:part2")

// Day 17: Set and Forget
include(":year2019:day17:part1", ":year2019:day17:part2", ":year2019:day17:scaffold")

// Day 18: Many-Worlds Interpretation
include(":year2019:day18:part1", ":year2019:day18:part2", ":year2019:day18:vault")

// Day 19: Tractor Beam
include(":year2019:day19:drone", ":year2019:day19:part1", ":year2019:day19:part2")

// Day 20: Donut Maze
include(":year2019:day20:maze", ":year2019:day20:part1", ":year2019:day20:part2")

// Day 21: Springdroid Adventure
include(":year2019:day21:part1", ":year2019:day21:part2", ":year2019:day21:spring")

// Day 22: Slam Shuffle
include(":year2019:day22:part1", ":year2019:day22:part2", ":year2019:day22:shuffle")

// Day 23: Category Six
include(":year2019:day23:network", ":year2019:day23:part1", ":year2019:day23:part2")

// Day 24: Planet of Discord
include(":year2019:day24:bugs", ":year2019:day24:part1", ":year2019:day24:part2")

// Day 25: Cryostasis
include(":year2019:day25:part1", ":year2019:day25:rescue")

////////////////////////////////////////////////////////////////////////////////////////////////////
// Advent of Code 2020

// Day 1: Report Repair
include(":year2020:day01:part1", ":year2020:day01:part2")

// Day 2: Password Philosophy
include(":year2020:day02:part1", ":year2020:day02:part2", ":year2020:day02:password")

// Day 3: Toboggan Trajectory
include(":year2020:day03:part1", ":year2020:day03:part2", ":year2020:day03:trees")

// Day 4: Passport Processing
include(":year2020:day04:part1", ":year2020:day04:part2", ":year2020:day04:passport")

// Day 5: Binary Boarding
include(":year2020:day05:boarding", ":year2020:day05:part1", ":year2020:day05:part2")

// Day 6: Custom Customs
include(":year2020:day06:part1", ":year2020:day06:part2")

// Day 7: Handy Haversacks
include(":year2020:day07:baggage", ":year2020:day07:part1", ":year2020:day07:part2")

// Day 8: Handheld Halting
include(":year2020:day08:bootcode", ":year2020:day08:part1", ":year2020:day08:part2")

// Day 9: Encoding Error
include(":year2020:day09:encryption", ":year2020:day09:part1", ":year2020:day09:part2")

// Day 10: Adapter Array
include(":year2020:day10:joltage", ":year2020:day10:part1", ":year2020:day10:part2")

// Day 11: Seating System
include(":year2020:day11:part1", ":year2020:day11:part2", ":year2020:day11:seating")

// Day 12: Rain Risk
include(":year2020:day12:navigation", ":year2020:day12:part1", ":year2020:day12:part2")

// Day 13: Shuttle Search
include(":year2020:day13:bus", ":year2020:day13:part1", ":year2020:day13:part2")

// Day 14: Docking Data
include(":year2020:day14:bitmask", ":year2020:day14:part1", ":year2020:day14:part2")

// Day 15: Rambunctious Recitation
include(":year2020:day15:memory", ":year2020:day15:part1", ":year2020:day15:part2")

// Day 16: Ticket Translation
include(":year2020:day16:part1", ":year2020:day16:part2", ":year2020:day16:ticket")

// Day 17: Conway Cubes
include(":year2020:day17:conway", ":year2020:day17:part1", ":year2020:day17:part2")

// Day 18: Operation Order
include(":year2020:day18:expression", ":year2020:day18:part1", ":year2020:day18:part2")

// Day 19: Monster Messages
include(":year2020:day19:part1", ":year2020:day19:part2", ":year2020:day19:rule")

// Day 20: Jurassic Jigsaw
include(":year2020:day20:image", ":year2020:day20:part1", ":year2020:day20:part2")

// Day 21: Allergen Assessment
include(":year2020:day21:food", ":year2020:day21:part1", ":year2020:day21:part2")

// Day 22: Crab Combat
include(":year2020:day22:part1", ":year2020:day22:part2")

// Day 23: Crab Cups
include(":year2020:day23:cups", ":year2020:day23:part1", ":year2020:day23:part2")

// Day 24: Lobby Layout
include(":year2020:day24:hexagon", ":year2020:day24:part1", ":year2020:day24:part2")

// Day 25: Combo Breaker
include(":year2020:day25:part1")

////////////////////////////////////////////////////////////////////////////////////////////////////
// Advent of Code 2021

// Day 1: Sonar Sweep
include(":year2021:day01:part1", ":year2021:day01:part2")

// Day 2: Dive!
include(":year2021:day02:part1", ":year2021:day02:part2", ":year2021:day02:submarine")

// Day 3: Binary Diagnostic
include(":year2021:day03:diagnostic", ":year2021:day03:part1", ":year2021:day03:part2")

// Day 4: Giant Squid
include(":year2021:day04:bingo", ":year2021:day04:part1", ":year2021:day04:part2")

// Day 5: Hydrothermal Venture
include(":year2021:day05:part1", ":year2021:day05:part2")
