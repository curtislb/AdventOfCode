package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.vector.intVectorOf
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import org.junit.jupiter.api.Test

/**
 * Tests [Body].
 */
class BodyTest {
    @Test
    fun testConstructWithBodyString() {
        val body = Body.fromPositionString("<x=3, y=-1, z=-7>")
        assertEquals(intVectorOf(3, -1, -7), body.position())
        assertEquals(intVectorOf(0, 0, 0), body.velocity())
    }

    @Test
    fun testTotalEnergy() {
        assertEquals(0, Body().totalEnergy())
        assertEquals(0, Body(initialPosition = intVectorOf(1, 1, 1)).totalEnergy())
        assertEquals(0, Body(initialVelocity = intVectorOf(-1, 2, -3)).totalEnergy())
        assertEquals(
            9,
            Body(
                initialPosition = intVectorOf(1, 1, 1),
                initialVelocity = intVectorOf(1, 1, 1)
            ).totalEnergy()
        )
        assertEquals(
            36,
            Body(
                initialPosition = intVectorOf(3, -2, 1),
                initialVelocity = intVectorOf(-1, 2, -3)
            ).totalEnergy()
        )
        assertEquals(
            110,
            Body(
                initialPosition = intVectorOf(-5, 5, 0),
                initialVelocity = intVectorOf(8, 0, 3)
            ).totalEnergy()
        )
        assertEquals(
            2250,
            Body(
                initialPosition = intVectorOf(18, -12, -15),
                initialVelocity = intVectorOf(17, 13, -20)
            ).totalEnergy()
        )
    }

    @Test
    fun testApplyGravity() {
        var body = Body()
        body.applyGravity(body)
        assertEquals(intVectorOf(0, 0, 0), body.position())
        assertEquals(intVectorOf(0, 0, 0), body.velocity())

        body = Body(initialPosition = intVectorOf(1, 1, 1))
        body.applyGravity(Body())
        assertEquals(intVectorOf(1, 1, 1), body.position())
        assertEquals(intVectorOf(-1, -1, -1), body.velocity())

        body = Body()
        body.applyGravity(Body(initialPosition = intVectorOf(1, 1, 1)))
        assertEquals(intVectorOf(0, 0, 0), body.position())
        assertEquals(intVectorOf(1, 1, 1), body.velocity())

        body = Body(
            initialPosition = intVectorOf(-11, -14, 5),
            initialVelocity = intVectorOf(18, -5, 16)
        )
        body.applyGravity(body)
        assertEquals(intVectorOf(-11, -14, 5), body.position())
        assertEquals(intVectorOf(18, -5, 16), body.velocity())

        body = Body(
            initialPosition = intVectorOf(-2, 16, 3),
            initialVelocity = intVectorOf(-18, -7, 13)
        )
        body.applyGravity(
            Body(
                initialPosition = intVectorOf(-11, 16, 11),
                initialVelocity = intVectorOf(-8, -7, -1)
            )
        )
        assertEquals(intVectorOf(-2, 16, 3), body.position())
        assertEquals(intVectorOf(-19, -7, 14), body.velocity())
    }

    @Test
    fun testApplyVelocity() {
        var body = Body()
        body.applyVelocity()
        assertEquals(intVectorOf(0, 0, 0), body.position())
        assertEquals(intVectorOf(0, 0, 0), body.velocity())

        body = Body(initialVelocity = intVectorOf(1, -2, 3))
        body.applyVelocity()
        assertEquals(intVectorOf(1, -2, 3), body.position())
        assertEquals(intVectorOf(1, -2, 3), body.velocity())

        body = Body(initialVelocity = intVectorOf(1, -2, 3))
        body.applyVelocity()
        assertEquals(intVectorOf(1, -2, 3), body.position())
        assertEquals(intVectorOf(1, -2, 3), body.velocity())

        body = Body(
            initialPosition = intVectorOf(-16, 13, -13),
            initialVelocity = intVectorOf(0, 19, -9)
        )
        body.applyVelocity()
        assertEquals(intVectorOf(-16, 32, -22), body.position())
        assertEquals(intVectorOf(0, 19, -9), body.velocity())
    }

    @Test
    fun testEquals() {
        assertEquals(Body(), Body())
        assertEquals(
            Body(initialPosition = intVectorOf(29, -81, 39)),
            Body(initialPosition = intVectorOf(29, -81, 39))
        )
        assertEquals(
            Body(initialVelocity = intVectorOf(49, 79, 87)),
            Body(initialVelocity = intVectorOf(49, 79, 87))
        )
        assertEquals(
            Body(
                initialPosition = intVectorOf(-8, 92, 99),
                initialVelocity = intVectorOf(-37, 40, -22)
            ),
            Body(
                initialPosition = intVectorOf(-8, 92, 99),
                initialVelocity = intVectorOf(-37, 40, -22)
            )
        )
        assertNotEquals(Body(), Body(initialPosition = intVectorOf(0, 1, -1)))
        assertNotEquals(
            Body(initialPosition = intVectorOf(1, 2, 3)),
            Body(initialPosition = intVectorOf(3, 2, 1))
        )
        assertNotEquals(
            Body(
                initialPosition = intVectorOf(-87, 84, -70),
                initialVelocity = intVectorOf(-59, -22, 70)
            ),
            Body(
                initialPosition = intVectorOf(-59, -22, 70),
                initialVelocity = intVectorOf(-87, 84, -70)
            )
        )
    }

    @Test
    fun testHashCode() {
        val bodies = listOf(
            Body(),
            Body(initialPosition = intVectorOf(73, -96, 34)),
            Body(initialPosition = intVectorOf(-75, -5, 7)),
            Body(initialVelocity = intVectorOf(-70, -95, -82)),
            Body(initialVelocity = intVectorOf(-17, -63, -80)),
            Body(
                initialPosition = intVectorOf(65, -76, 32),
                initialVelocity = intVectorOf(-41, -62, 35)
            ),
            Body(
                initialPosition = intVectorOf(-66, -10, -93),
                initialVelocity = intVectorOf(-52, 24, 88)
            )
        )
        val hashMap = HashMap<Body, Int>()
        bodies.forEachIndexed { index, body -> hashMap[body] = index }
        bodies.forEachIndexed { index, body -> assertEquals(index, hashMap[body]) }
    }

    @Test
    fun testToString() {
        assertEquals(
            "pos=<x=-8, y=10, z=11>, vel=<x=3, y=-3, z=0>",
            Body(
                initialPosition = intVectorOf(-8, 10, 11),
                initialVelocity = intVectorOf(3, -3, 0)
            ).toString()
        )
    }
}
