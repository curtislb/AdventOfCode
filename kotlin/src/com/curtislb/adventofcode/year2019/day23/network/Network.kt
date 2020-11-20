package com.curtislb.adventofcode.year2019.day23.network

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.year2019.day23.network.packet.BasePacketListener
import com.curtislb.adventofcode.year2019.day23.network.packet.Packet
import com.curtislb.adventofcode.year2019.day23.network.packet.PacketListener
import java.io.File
import java.math.BigInteger

/**
 * A network of computers that may send and receive packets from one another and a NAT that moderates network activity.
 *
 * @param file A file containing the [Intcode] program that controls each computer in the network.
 * @param computerCount The number of computers that make up the network.
 * @param natPacketListener A listener for packets that are received or sent by the NAT.
 */
class Network(file: File, computerCount: Int, var natPacketListener: PacketListener = BasePacketListener()) {
    /**
     * An array containing the Intcode programs for all computers in address order.
     */
    private val computers: Array<Intcode>

    init {
        val programString = file.readText().trim()
        computers = Array(computerCount) { Intcode(programString) }
        computers.forEachIndexed { address, computer -> computer.sendInput(address.toBigInteger()) }
    }

    /**
     * An array of incoming packet queues for each computer, in address order.
     */
    private val queuedPackets: Array<MutableList<Packet>> = Array(computerCount) { mutableListOf() }

    /**
     * An array of the number of cycles for which each computer has been idle, in address order.
     */
    private val idleCounts: IntArray = IntArray(computerCount)

    /**
     * The latest packet received by the NAT, or `null` if the NAT has not received any packets.
     */
    private var natPacket: Packet? = null

    /**
     * Whether the network has finished running.
     */
    private var isDone = false

    /**
     * Restores the network to its starting state, immediately after initialization.
     */
    fun reset() {
        // Reset each computer and provide its address.
        computers.forEachIndexed { address, computer ->
            computer.resetState()
            computer.sendInput(address.toBigInteger())
        }

        // Clear all queued packets.
        for (i in queuedPackets.indices) {
            queuedPackets[i] = mutableListOf()
        }

        // Allow the network to be run again.
        isDone = false
    }

    /**
     * Runs all computers in the network until a stopping condition is reached.
     *
     * All computers will be run repeatedly, in address order, sending and receiving packets between each other and the
     * NAT. This will continue until one of the following occurs:
     * - The NAT receives a packet for which [PacketListener.onPacketReceived] of [natPacketListener] returns `true`.
     * - The NAT sends a packet for which [PacketListener.onPacketSent] of [natPacketListener] returns `true`.
     *
     * In either case, this method will return. Any future calls to [run] will then immediately return, until [reset] is
     * invoked.
     */
    fun run() {
        // Check if the network has already finished running.
        if (isDone) {
            return
        }

        // Initialize output handling for each computer.
        val onOutput = createOutputHandler()
        computers.forEach { it.onOutput = onOutput }

        try {
            // Run all computers in order until finished.
            var address = 0
            while (!isDone) {
                runComputer(address)
                if (address == computers.lastIndex) {
                    handleIdleState()
                }
                address = (address + 1) % computers.size
            }
        } finally {
            // Clean up references to output handler.
            computers.forEach { it.resetOutput() }
        }
    }

    /**
     * Returns an output handler function to be used by each computer in the network.
     */
    private fun createOutputHandler(): (BigInteger) -> Unit {
        var outputState = 0
        var packetAddress: Int = -1
        var packetX: BigInteger = NO_PACKET_INPUT
        return { output ->
            when (outputState) {
                0 -> packetAddress = output.toInt()
                1 -> packetX = output
                2 -> {
                    val packet = Packet(packetX, output)
                    if (packetAddress == NAT_ADDRESS) {
                        natPacket = packet
                        isDone = natPacketListener.onPacketReceived(packet)
                    } else {
                        queuedPackets[packetAddress].add(packet)
                    }
                }
            }
            outputState = (outputState + 1) % 3
        }
    }

    /**
     * Runs the [Intcode] program for the computer at the given [address] in the network.
     *
     * If there are queued packets for the computer to receive, sends the x and y value of each packet in order as input
     * before running the program. Otherwise, sends an input value of -1 to the program and increments its idle counter.
     *
     * @see [Intcode.run]
     */
    private fun runComputer(address: Int) {
        val computer = computers[address]
        val packets = queuedPackets[address]
        if (packets.isEmpty()) {
            computer.sendInput(NO_PACKET_INPUT)
            idleCounts[address]++
        } else {
            computer.sendInput(packets.flatMap { listOf(it.x, it.y) }.asSequence())
            queuedPackets[address] = mutableListOf()
            idleCounts[address] = 0
        }
        computer.run()
    }

    /**
     * If the network is idle, sends the last packet received by the NAT to the computer at address 0.
     */
    private fun handleIdleState() {
        if (idleCounts.all { it >= IDLE_THRESHOLD }) {
            natPacket?.let { packet ->
                queuedPackets[0].add(packet)
                isDone = natPacketListener.onPacketSent(packet)
            }
        }
    }

    companion object {
        /**
         * The number of times in a row a computer can poll for and not receive packets before it is considered idle.
         */
        private const val IDLE_THRESHOLD = 2

        /**
         * The network address corresponding to the NAT.
         */
        private const val NAT_ADDRESS = 255

        /**
         * An input value indicating that there are no queued packets for a computer to process.
         */
        private val NO_PACKET_INPUT = BigInteger("-1")
    }
}
