/*
--- Day 23: Category Six ---

The droids have finished repairing as much of the ship as they can. Their report indicates that this was a Category 6
disaster - not because it was that bad, but because it destroyed the stockpile of Category 6 network cables as well as
most of the ship's network infrastructure.

You'll need to rebuild the network from scratch.

The computers on the network are standard Intcode computers that communicate by sending packets to each other. There
are 50 of them in total, each running a copy of the same Network Interface Controller (NIC) software (your puzzle
input). The computers have network addresses 0 through 49; when each computer boots up, it will request its network
address via a single input instruction. Be sure to give each computer a unique network address.

Once a computer has received its network address, it will begin doing work and communicating over the network by
sending and receiving packets. All packets contain two values named X and Y. Packets sent to a computer are queued by
the recipient and read in the order they are received.

To send a packet to another computer, the NIC will use three output instructions that provide the destination address
of the packet followed by its X and Y values. For example, three output instructions that provide the values 10, 20, 30
would send a packet with X=20 and Y=30 to the computer with address 10.

To receive a packet from another computer, the NIC will use an input instruction. If the incoming packet queue is
empty, provide -1. Otherwise, provide the X value of the next packet; the computer will then use a second input
instruction to receive the Y value for the same packet. Once both values of the packet are read in this way, the packet
is removed from the queue.

Note that these input and output instructions never block. Specifically, output instructions do not wait for the sent
packet to be received - the computer might send multiple packets before receiving any. Similarly, input instructions do
not wait for a packet to arrive - if no packet is waiting, input instructions should receive -1.

Boot up all 50 computers and attach them to your network. What is the Y value of the first packet sent to address 255?
*/

package com.curtislb.adventofcode.year2019.day23.part1

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day23.network.Network
import com.curtislb.adventofcode.year2019.day23.network.packet.BasePacketListener
import com.curtislb.adventofcode.year2019.day23.network.packet.Packet
import java.math.BigInteger
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2019, day 23, part 1.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param computerCount The number of computers in the network.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 23), computerCount: Int = 50): BigInteger? {
    // Listen for the first Y value received by the NAT.
    var firstNatPacketY: BigInteger? = null
    val natPacketListener = object : BasePacketListener() {
        override fun onPacketReceived(packet: Packet): Boolean {
            firstNatPacketY = packet.y
            return true // Stop the network.
        }
    }

    val network = Network(inputPath.toFile(), computerCount, natPacketListener)
    network.run()
    return firstNatPacketY
}

fun main() = when (val solution = solve()) {
    null -> println("No packet received by the NAT.")
    else -> println(solution)
}
