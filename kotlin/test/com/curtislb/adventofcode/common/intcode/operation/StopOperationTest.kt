package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Tests [StopOperation].
 */
class StopOperationTest {
    @Test
    fun testProcess() {
        val intcode = Intcode("1,2,3,4,99")
        val pointer = StopOperation.process(intcode, pointer = 4, parameters = emptyArray(), modes = emptyArray())
        assertTrue(pointer < 0)
    }
}
