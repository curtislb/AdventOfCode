package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableVector
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [Body].
 */
class BodyTest {
    @Test fun testConstructFromBodyString() {
        val body = Body("<x=3, y=-1, z=-7>")
        assertEquals(MutableVector(3, -1, -7), body.position)
        assertEquals(MutableVector(0, 0, 0), body.velocity)
    }

    @Test fun testTotalEnergy() {
        assertEquals(0, Body().totalEnergy)
        assertEquals(0, Body(MutableVector(1, 1, 1)).totalEnergy)
        assertEquals(0, Body(velocity = MutableVector(-1, 2, -3)).totalEnergy)
        assertEquals(9, Body(position = MutableVector(1, 1, 1), velocity = MutableVector(1, 1, 1)).totalEnergy)
        assertEquals(36, Body(position = MutableVector(3, -2, 1), velocity = MutableVector(-1, 2, -3)).totalEnergy)
        assertEquals(110, Body(position = MutableVector(-5, 5, 0), velocity = MutableVector(8, 0, 3)).totalEnergy)
        assertEquals(
            2250,
            Body(position = MutableVector(18, -12, -15), velocity = MutableVector(17, 13, -20)).totalEnergy
        )
    }

    @Test fun testApplyGravity() {
        var body = Body()
        body.applyGravity(body)
        assertEquals(MutableVector(0, 0, 0), body.position)
        assertEquals(MutableVector(0, 0, 0), body.velocity)

        body = Body(MutableVector(1, 1, 1))
        body.applyGravity(Body())
        assertEquals(MutableVector(1, 1, 1), body.position)
        assertEquals(MutableVector(-1, -1, -1), body.velocity)

        body = Body()
        body.applyGravity(Body(MutableVector(1, 1, 1)))
        assertEquals(MutableVector(0, 0, 0), body.position)
        assertEquals(MutableVector(1, 1, 1), body.velocity)

        body = Body(position = MutableVector(-11, -14, 5), velocity = MutableVector(18, -5, 16))
        body.applyGravity(body)
        assertEquals(MutableVector(-11, -14, 5), body.position)
        assertEquals(MutableVector(18, -5, 16), body.velocity)

        body = Body(position = MutableVector(-2, 16, 3), velocity = MutableVector(-18, -7, 13))
        body.applyGravity(Body(position = MutableVector(-11, 16, 11), velocity = MutableVector(-8, -7, -1)))
        assertEquals(MutableVector(-2, 16, 3), body.position)
        assertEquals(MutableVector(-19, -7, 14), body.velocity)
    }

    @Test fun testApplyVelocity() {
        var body = Body()
        body.applyVelocity()
        assertEquals(MutableVector(0, 0, 0), body.position)
        assertEquals(MutableVector(0, 0, 0), body.velocity)

        body = Body(velocity = MutableVector(1, -2, 3))
        body.applyVelocity()
        assertEquals(MutableVector(1, -2, 3), body.position)
        assertEquals(MutableVector(1, -2, 3), body.velocity)

        body = Body(velocity = MutableVector(1, -2, 3))
        body.applyVelocity()
        assertEquals(MutableVector(1, -2, 3), body.position)
        assertEquals(MutableVector(1, -2, 3), body.velocity)

        body = Body(position = MutableVector(-16, 13, -13), velocity = MutableVector(0, 19, -9))
        body.applyVelocity()
        assertEquals(MutableVector(-16, 32, -22), body.position)
        assertEquals(MutableVector(0, 19, -9), body.velocity)
    }

    @Test fun testCopy() {
        val body = Body(position = MutableVector(16, 14, -2), velocity = MutableVector(1, -9, 0))
        val bodyCopy = body.copy()
        body.position.update(3, -15, -7)
        body.velocity.update(-1, 11, -13)
        assertEquals(MutableVector(3, -15, -7), body.position)
        assertEquals(MutableVector(-1, 11, -13), body.velocity)
        assertEquals(MutableVector(16, 14, -2), bodyCopy.position)
        assertEquals(MutableVector(1, -9, 0), bodyCopy.velocity)
    }

    @Test fun testToString() {
        assertEquals(
            "pos=<x=-8, y=10, z=11>, vel=<x=3, y=-3, z=0>",
            Body(position = MutableVector(-8, 10, 11), velocity = MutableVector(3, -3, 0)).toString()
        )
    }
}
