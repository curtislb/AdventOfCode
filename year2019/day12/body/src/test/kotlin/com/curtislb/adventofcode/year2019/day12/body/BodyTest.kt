package com.curtislb.adventofcode.year2019.day12.body

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import org.junit.jupiter.api.Test

/**
 * Tests [Body].
 */
class BodyTest {
    @Test
    fun testConstructWithBodyString() {
        val body = Body("<x=3, y=-1, z=-7>")
        assertEquals(Triple(3, -1, -7), body.position)
        assertEquals(Triple(0, 0, 0), body.velocity)
    }

    @Test
    fun testTotalEnergy() {
        assertEquals(0, Body().totalEnergy)
        assertEquals(0, Body(initialPosition = Triple(1, 1, 1)).totalEnergy)
        assertEquals(0, Body(initialVelocity = Triple(-1, 2, -3)).totalEnergy)
        assertEquals(
            9,
            Body(initialPosition = Triple(1, 1, 1), initialVelocity = Triple(1, 1, 1)).totalEnergy
        )
        assertEquals(
            36,
            Body(
                initialPosition = Triple(3, -2, 1),
                initialVelocity = Triple(-1, 2, -3)
            ).totalEnergy
        )
        assertEquals(
            110,
            Body(initialPosition = Triple(-5, 5, 0), initialVelocity = Triple(8, 0, 3)).totalEnergy
        )
        assertEquals(
            2250,
            Body(
                initialPosition = Triple(18, -12, -15),
                initialVelocity = Triple(17, 13, -20)
            ).totalEnergy
        )
    }

    @Test
    fun testApplyGravity() {
        var body = Body()
        body.applyGravity(body)
        assertEquals(Triple(0, 0, 0), body.position)
        assertEquals(Triple(0, 0, 0), body.velocity)

        body = Body(initialPosition = Triple(1, 1, 1))
        body.applyGravity(Body())
        assertEquals(Triple(1, 1, 1), body.position)
        assertEquals(Triple(-1, -1, -1), body.velocity)

        body = Body()
        body.applyGravity(Body(initialPosition = Triple(1, 1, 1)))
        assertEquals(Triple(0, 0, 0), body.position)
        assertEquals(Triple(1, 1, 1), body.velocity)

        body = Body(initialPosition = Triple(-11, -14, 5), initialVelocity = Triple(18, -5, 16))
        body.applyGravity(body)
        assertEquals(Triple(-11, -14, 5), body.position)
        assertEquals(Triple(18, -5, 16), body.velocity)

        body = Body(initialPosition = Triple(-2, 16, 3), initialVelocity = Triple(-18, -7, 13))
        body.applyGravity(
            Body(initialPosition = Triple(-11, 16, 11), initialVelocity = Triple(-8, -7, -1))
        )
        assertEquals(Triple(-2, 16, 3), body.position)
        assertEquals(Triple(-19, -7, 14), body.velocity)
    }

    @Test
    fun testApplyVelocity() {
        var body = Body()
        body.applyVelocity()
        assertEquals(Triple(0, 0, 0), body.position)
        assertEquals(Triple(0, 0, 0), body.velocity)

        body = Body(initialVelocity = Triple(1, -2, 3))
        body.applyVelocity()
        assertEquals(Triple(1, -2, 3), body.position)
        assertEquals(Triple(1, -2, 3), body.velocity)

        body = Body(initialVelocity = Triple(1, -2, 3))
        body.applyVelocity()
        assertEquals(Triple(1, -2, 3), body.position)
        assertEquals(Triple(1, -2, 3), body.velocity)

        body = Body(initialPosition = Triple(-16, 13, -13), initialVelocity = Triple(0, 19, -9))
        body.applyVelocity()
        assertEquals(Triple(-16, 32, -22), body.position)
        assertEquals(Triple(0, 19, -9), body.velocity)
    }

    @Test
    fun testCopy() {
        val body = Body(initialPosition = Triple(16, 14, -2), initialVelocity = Triple(1, -9, 0))
        val bodyCopy = body.copy()
        body.applyGravity(Body())
        body.applyVelocity()
        assertNotEquals(Triple(16, 14, -2), body.position)
        assertNotEquals(Triple(1, -9, 0), body.velocity)
        assertEquals(Triple(16, 14, -2), bodyCopy.position)
        assertEquals(Triple(1, -9, 0), bodyCopy.velocity)
    }

    @Test
    fun testEquals() {
        assertEquals(Body(), Body())
        assertEquals(
            Body(initialPosition = Triple(29, -81, 39)),
            Body(initialPosition = Triple(29, -81, 39))
        )
        assertEquals(
            Body(initialVelocity = Triple(49, 79, 87)),
            Body(initialVelocity = Triple(49, 79, 87))
        )
        assertEquals(
            Body(initialPosition = Triple(-8, 92, 99), initialVelocity = Triple(-37, 40, -22)),
            Body(initialPosition = Triple(-8, 92, 99), initialVelocity = Triple(-37, 40, -22))
        )
        assertNotEquals(Body(), Body(initialPosition = Triple(0, 1, -1)))
        assertNotEquals(
            Body(initialPosition = Triple(1, 2, 3)),
            Body(initialPosition = Triple(3, 2, 1))
        )
        assertNotEquals(
            Body(initialPosition = Triple(-87, 84, -70), initialVelocity = Triple(-59, -22, 70)),
            Body(initialPosition = Triple(-59, -22, 70), initialVelocity = Triple(-87, 84, -70))
        )
    }

    @Test
    fun testHashCode() {
        val bodies = listOf(
            Body(),
            Body(initialPosition = Triple(73, -96, 34)),
            Body(initialPosition = Triple(-75, -5, 7)),
            Body(initialVelocity = Triple(-70, -95, -82)),
            Body(initialVelocity = Triple(-17, -63, -80)),
            Body(initialPosition = Triple(65, -76, 32), initialVelocity = Triple(-41, -62, 35)),
            Body(initialPosition = Triple(-66, -10, -93), initialVelocity = Triple(-52, 24, 88))
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
                initialPosition = Triple(-8, 10, 11),
                initialVelocity = Triple(3, -3, 0)
            ).toString()
        )
    }
}
