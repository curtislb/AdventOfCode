package com.curtislb.adventofcode.year2019.day23.network.packet

/**
 * A listener for processing network packets as they are sent or received.
 */
interface PacketListener {
    /**
     * Processes a [packet] after it has been sent and returns a boolean status indicator.
     */
    fun onPacketSent(packet: Packet): Boolean

    /**
     * Processes a [packet] after it has been received and returns a boolean status indicator.
     */
    fun onPacketReceived(packet: Packet): Boolean
}
