package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.ImmediateMode
import com.curtislb.adventofcode.common.intcode.mode.PositionMode
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [OutputOperation].
 */
class OutputOperationTest {
    private lateinit var intcode: Intcode
    private lateinit var outputs: MutableList<BigInteger>

    @BeforeEach
    fun setUp() {
        outputs = mutableListOf()
        intcode = Intcode("1,23,-20,0,4,2,99") { outputs.add(it) }
    }

    @Test
    fun testProcessWithImmediateParam() {
        assertEquals(
            6,
            OutputOperation.process(
                intcode,
                pointer = 4,
                parameters = arrayOf(BigInteger.TWO),
                modes = arrayOf(ImmediateMode)
            )
        )
        assertEquals(listOf(BigInteger.TWO), outputs.toList())
    }

    @Test
    fun testProcessWithPositionParam() {
        assertEquals(
            6,
            OutputOperation.process(
                intcode,
                pointer = 4,
                parameters = arrayOf(BigInteger.TWO),
                modes = arrayOf(PositionMode)
            )
        )
        assertEquals(listOf(BigInteger("-20")), outputs.toList())
    }
}
