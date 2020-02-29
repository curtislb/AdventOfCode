package com.curtislb.adventofcode.year2019.day23.network.packet

/**
 * An extendable no-op implementation of the [PacketListener] interface.
 */
open class BasePacketListener : PacketListener {
    override fun onPacketSent(packet: Packet): Boolean = false
    override fun onPacketReceived(packet: Packet): Boolean = false
}
