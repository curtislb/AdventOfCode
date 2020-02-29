/*
--- Part Two ---

Packets sent to address 255 are handled by a device called a NAT (Not Always Transmitting). The NAT is responsible for
managing power consumption of the network by blocking certain packets and watching for idle periods in the computers.

If a packet would be sent to address 255, the NAT receives it instead. The NAT remembers only the last packet it
receives; that is, the data in each packet it receives overwrites the NAT's packet memory with the new packet's X and Y
values.

The NAT also monitors all computers on the network. If all computers have empty incoming packet queues and are
continuously trying to receive packets without sending packets, the network is considered idle.

Once the network is idle, the NAT sends only the last packet it received to address 0; this will cause the computers on
the network to resume activity. In this way, the NAT can throttle power consumption of the network when the ship needs
power in other areas.

Monitor packets released to the computer at address 0 by the NAT. What is the first Y value delivered by the NAT to the
computer at address 0 twice in a row?
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
