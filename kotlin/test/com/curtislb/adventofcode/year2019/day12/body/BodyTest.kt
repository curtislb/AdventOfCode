package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableVector
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Tests [Body].
 */
class BodyTest {
    @Test(expected = IllegalArgumentException::class)
    fun testConstructWithWrongPositionVectorSize() {
        Body(MutableVector(0, 1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructWithWrongVelocityVectorSize() {
        Body(velocity = MutableVector(-2, 3, 4, -5))
    }

    @Test
    fun testConstructFromBodyString() {
        val body = Body("<x=3, y=-1, z=-7>")
        assertEquals(MutableVector(3, -1, -7), body.position)
        assertEquals(MutableVector(0, 0, 0), body.velocity)
    }

    @Test
    fun testTotalEnergy() {
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

    @Test
    fun testApplyGravity() {
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

    @Test
    fun testApplyVelocity() {
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

    @Test
    fun testCopy() {
        val body = Body(position = MutableVector(16, 14, -2), velocity = MutableVector(1, -9, 0))
        val bodyCopy = body.copy()
        body.position.update(3, -15, -7)
        body.velocity.update(-1, 11, -13)
        assertEquals(MutableVector(3, -15, -7), body.position)
        assertEquals(MutableVector(-1, 11, -13), body.velocity)
        assertEquals(MutableVector(16, 14, -2), bodyCopy.position)
        assertEquals(MutableVector(1, -9, 0), bodyCopy.velocity)
    }

    @Test
    fun testEquals() {
        assertEquals(Body(), Body())
        assertEquals(Body(MutableVector(29, -81, 39)), Body(MutableVector(29, -81, 39)))
        assertEquals(Body(velocity = MutableVector(49, 79, 87)), Body(velocity = MutableVector(49, 79, 87)))
        assertEquals(
            Body(position = MutableVector(-8, 92, 99), velocity = MutableVector(-37, 40, -22)),
            Body(position = MutableVector(-8, 92, 99), velocity = MutableVector(-37, 40, -22))
        )

        assertNotEquals(Body(), Body(MutableVector(0, 1, -1)))
        assertNotEquals(Body(MutableVector(1, 2, 3)), Body(MutableVector(3, 2, 1)))
        assertNotEquals(
            Body(position = MutableVector(-87, 84, -70), velocity = MutableVector(-59, -22, 70)),
            Body(position = MutableVector(-59, -22, 70), velocity = MutableVector(-87, 84, -70))
        )
    }

    @Test
    fun testHashCode() {
        val bodies = listOf(
            Body(),
            Body(MutableVector(73, -96, 34)),
            Body(MutableVector(-75, -5, 7)),
            Body(velocity = MutableVector(-70, -95, -82)),
            Body(velocity = MutableVector(-17, -63, -80)),
            Body(position = MutableVector(65, -76, 32), velocity = MutableVector(-41, -62, 35)),
            Body(position = MutableVector(-66, -10, -93), velocity = MutableVector(-52, 24, 88))
        )
        val hashMap = HashMap<Body, Int>()
        bodies.forEachIndexed { index, body -> hashMap[body] = index }
        bodies.forEachIndexed { index, body -> assertEquals(index, hashMap[body]) }
    }

    @Test
    fun testToString() {
        assertEquals(
            "pos=<x=-8, y=10, z=11>, vel=<x=3, y=-3, z=0>",
            Body(position = MutableVector(-8, 10, 11), velocity = MutableVector(3, -3, 0)).toString()
        )
    }
}
