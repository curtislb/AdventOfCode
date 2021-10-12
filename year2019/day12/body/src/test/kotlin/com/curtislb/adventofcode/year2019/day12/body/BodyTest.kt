package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableIntVector
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Body].
 */
class BodyTest {
    @Test
    fun testConstructWithWrongPositionVectorSize() {
        assertThrows<IllegalArgumentException> { Body(MutableIntVector(0, 1)) }
    }

    @Test
    fun testConstructWithWrongVelocityVectorSize() {
        assertThrows<IllegalArgumentException> { Body(velocity = MutableIntVector(-2, 3, 4, -5)) }
    }

    @Test
    fun testConstructWithBodyString() {
        val body = Body("<x=3, y=-1, z=-7>")
        assertEquals(MutableIntVector(3, -1, -7), body.position)
        assertEquals(MutableIntVector(0, 0, 0), body.velocity)
    }

    @Test
    fun testTotalEnergy() {
        assertEquals(0, Body().totalEnergy)
        assertEquals(0, Body(MutableIntVector(1, 1, 1)).totalEnergy)
        assertEquals(0, Body(velocity = MutableIntVector(-1, 2, -3)).totalEnergy)
        assertEquals(
            9,
            Body(
                position = MutableIntVector(1, 1, 1),
                velocity = MutableIntVector(1, 1, 1)
            ).totalEnergy
        )
        assertEquals(
            36,
            Body(
                position = MutableIntVector(3, -2, 1),
                velocity = MutableIntVector(-1, 2, -3)
            ).totalEnergy
        )
        assertEquals(
            110,
            Body(
                position = MutableIntVector(-5, 5, 0),
                velocity = MutableIntVector(8, 0, 3)
            ).totalEnergy
        )
        assertEquals(
            2250,
            Body(
                position = MutableIntVector(18, -12, -15),
                velocity = MutableIntVector(17, 13, -20)
            ).totalEnergy
        )
    }

    @Test
    fun testApplyGravity() {
        var body = Body()
        body.applyGravity(body)
        assertEquals(MutableIntVector(0, 0, 0), body.position)
        assertEquals(MutableIntVector(0, 0, 0), body.velocity)

        body = Body(MutableIntVector(1, 1, 1))
        body.applyGravity(Body())
        assertEquals(MutableIntVector(1, 1, 1), body.position)
        assertEquals(MutableIntVector(-1, -1, -1), body.velocity)

        body = Body()
        body.applyGravity(Body(MutableIntVector(1, 1, 1)))
        assertEquals(MutableIntVector(0, 0, 0), body.position)
        assertEquals(MutableIntVector(1, 1, 1), body.velocity)

        body = Body(
            position = MutableIntVector(-11, -14, 5),
            velocity = MutableIntVector(18, -5, 16)
        )
        body.applyGravity(body)
        assertEquals(MutableIntVector(-11, -14, 5), body.position)
        assertEquals(MutableIntVector(18, -5, 16), body.velocity)

        body = Body(
            position = MutableIntVector(-2, 16, 3),
            velocity = MutableIntVector(-18, -7, 13)
        )
        body.applyGravity(
            Body(
                position = MutableIntVector(-11, 16, 11),
                velocity = MutableIntVector(-8, -7, -1)
            )
        )
        assertEquals(MutableIntVector(-2, 16, 3), body.position)
        assertEquals(MutableIntVector(-19, -7, 14), body.velocity)
    }

    @Test
    fun testApplyVelocity() {
        var body = Body()
        body.applyVelocity()
        assertEquals(MutableIntVector(0, 0, 0), body.position)
        assertEquals(MutableIntVector(0, 0, 0), body.velocity)

        body = Body(velocity = MutableIntVector(1, -2, 3))
        body.applyVelocity()
        assertEquals(MutableIntVector(1, -2, 3), body.position)
        assertEquals(MutableIntVector(1, -2, 3), body.velocity)

        body = Body(velocity = MutableIntVector(1, -2, 3))
        body.applyVelocity()
        assertEquals(MutableIntVector(1, -2, 3), body.position)
        assertEquals(MutableIntVector(1, -2, 3), body.velocity)

        body = Body(
            position = MutableIntVector(-16, 13, -13),
            velocity = MutableIntVector(0, 19, -9)
        )
        body.applyVelocity()
        assertEquals(MutableIntVector(-16, 32, -22), body.position)
        assertEquals(MutableIntVector(0, 19, -9), body.velocity)
    }

    @Test
    fun testCopy() {
        val body = Body(
            position = MutableIntVector(16, 14, -2),
            velocity = MutableIntVector(1, -9, 0)
        )
        val bodyCopy = body.copy()
        body.position.update(3, -15, -7)
        body.velocity.update(-1, 11, -13)
        assertEquals(MutableIntVector(3, -15, -7), body.position)
        assertEquals(MutableIntVector(-1, 11, -13), body.velocity)
        assertEquals(MutableIntVector(16, 14, -2), bodyCopy.position)
        assertEquals(MutableIntVector(1, -9, 0), bodyCopy.velocity)
    }

    @Test
    fun testEquals() {
        assertEquals(Body(), Body())
        assertEquals(Body(MutableIntVector(29, -81, 39)), Body(MutableIntVector(29, -81, 39)))
        assertEquals(
            Body(velocity = MutableIntVector(49, 79, 87)),
            Body(velocity = MutableIntVector(49, 79, 87))
        )
        assertEquals(
            Body(
                position = MutableIntVector(-8, 92, 99),
                velocity = MutableIntVector(-37, 40, -22)
            ),
            Body(position = MutableIntVector(-8, 92, 99), velocity = MutableIntVector(-37, 40, -22))
        )

        assertNotEquals(Body(), Body(MutableIntVector(0, 1, -1)))
        assertNotEquals(Body(MutableIntVector(1, 2, 3)), Body(MutableIntVector(3, 2, 1)))
        assertNotEquals(
            Body(
                position = MutableIntVector(-87, 84, -70),
                velocity = MutableIntVector(-59, -22, 70)
            ),
            Body(
                position = MutableIntVector(-59, -22, 70),
                velocity = MutableIntVector(-87, 84, -70)
            )
        )
    }

    @Test
    fun testHashCode() {
        val bodies = listOf(
            Body(),
            Body(MutableIntVector(73, -96, 34)),
            Body(MutableIntVector(-75, -5, 7)),
            Body(velocity = MutableIntVector(-70, -95, -82)),
            Body(velocity = MutableIntVector(-17, -63, -80)),
            Body(
                position = MutableIntVector(65, -76, 32),
                velocity = MutableIntVector(-41, -62, 35)
            ),
            Body(
                position = MutableIntVector(-66, -10, -93),
                velocity = MutableIntVector(-52, 24, 88)
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
                position = MutableIntVector(-8, 10, 11),
                velocity = MutableIntVector(3, -3, 0)
            ).toString()
        )
    }
}
