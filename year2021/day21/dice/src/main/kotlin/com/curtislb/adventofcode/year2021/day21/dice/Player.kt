package com.curtislb.adventofcode.year2021.day21.dice

/**
 * Information about a single player in a dice-rolling game.
 *
 * @param space Index of the player's current space on the game board.
 * @param score The player's current score.
 */
data class Player(val space: Int, val score: Int = 0)
