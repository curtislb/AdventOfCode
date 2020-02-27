/*
TODO: Problem description goes here
*/

package com.curtislb.adventofcode.year2019.day23.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day23.network.Network
import com.curtislb.adventofcode.year2019.day23.network.packet.BasePacketListener
import com.curtislb.adventofcode.year2019.day23.network.packet.Packet
import java.math.BigInteger

/**
 * The path to the input file for this puzzle.
 */
private val INPUT_PATH = pathToInput(year = 2019, day = 23)

/**
 * The number of computers in the network.
 */
private const val COMPUTER_COUNT = 50

// Answer: 13286
fun main() {
    // Listen for the first repeated Y value sent by the NAT.
    val natPacketYValues = mutableSetOf<BigInteger>()
    var repeatedYValue: BigInteger? = null
    val natPacketListener = object : BasePacketListener() {
        override fun onPacketSent(packet: Packet): Boolean {
            return if (packet.y !in natPacketYValues) {
                natPacketYValues.add(packet.y)
                false // Don't stop the network.
            } else {
                repeatedYValue = packet.y
                true // Stop the network.
            }
        }
    }

    val network = Network(INPUT_PATH.toFile(), COMPUTER_COUNT, natPacketListener)
    network.run()
    println(repeatedYValue)
}
