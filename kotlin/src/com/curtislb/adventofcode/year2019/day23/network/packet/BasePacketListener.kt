package com.curtislb.adventofcode.year2019.day23.network.packet

/**
 * A no-op implementation of the [PacketListener] interface that may be extended.
 */
open class BasePacketListener : PacketListener {
    override fun onPacketSent(packet: Packet): Boolean = false
    override fun onPacketReceived(packet: Packet): Boolean = false
}
