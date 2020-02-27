package com.curtislb.adventofcode.year2019.day23.network.packet

import java.math.BigInteger

/**
 * A packet, consisting of [x] and [y] values, that may be sent between computers in the network.
 */
data class Packet(val x: BigInteger, val y: BigInteger)
