package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [modMultiply].
 */
class ModMultiplyTest {
    @Test
    fun testModMultiplyWithLongs() {
        for (m in -11L..11L) {
            for (n in -11L..11L) {
                for (modulus in (-11L..11L)) {
                    if (modulus == 0L) {
                        assertThrows<IllegalArgumentException> { m.modMultiply(n, modulus) }
                    } else {
                        assertEquals((m * n).mod(modulus), m.modMultiply(n, modulus))
                    }
                }
            }
        }
    }

    @Test
    fun testModMultiplyWithBigIntegers() {
        for (m in -11..11) {
            for (n in -11..11) {
                for (modulus in (-2..11)) {
                    if (modulus <= 0) {
                        assertThrows<IllegalArgumentException> {
                            m.toBigInteger().modMultiply(n.toBigInteger(), modulus.toBigInteger())
                        }
                    } else {
                        assertEquals(
                            (m * n).mod(modulus).toBigInteger(),
                            m.toBigInteger().modMultiply(n.toBigInteger(), modulus.toBigInteger())
                        )
                    }
                }
            }
        }
    }
}
