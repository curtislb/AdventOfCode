package com.curtislb.adventofcode.year2019.day14.chemistry

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Reaction].
 */
class ReactionTest {
    @Test
    fun testConstructWithNoReactants() {
        assertThrows<IllegalArgumentException> {
            Reaction(
                emptyList(),
                MaterialAmount("magic", 1L)
            )
        }
    }

    @Test
    fun testToString() {
        assertEquals(
            "1 lorem => 2 ipsum",
            Reaction(listOf(MaterialAmount("lorem", 1L)), MaterialAmount("ipsum", 2L)).toString()
        )
        assertEquals(
            "2 foo, 3 bar => 1 foobar",
            Reaction(
                listOf(MaterialAmount("foo", 2L), MaterialAmount("bar", 3L)),
                MaterialAmount("foobar", 1L)
            ).toString()
        )
        assertEquals(
            "5 A, 11 B, 9 C => 23 XYZ",
            Reaction(
                listOf(MaterialAmount("A", 5L), MaterialAmount("B", 11L), MaterialAmount("C", 9L)),
                MaterialAmount("XYZ", 23L)
            ).toString()
        )
    }

    @Test
    fun testFromString() {
        assertEquals(
            Reaction(listOf(MaterialAmount("lorem", 1L)), MaterialAmount("ipsum", 2L)),
            Reaction.from("1 lorem => 2 ipsum"),
        )
        assertEquals(
            Reaction(
                listOf(MaterialAmount("foo", 2L), MaterialAmount("bar", 3L)),
                MaterialAmount("foobar", 1L)
            ),
            Reaction.from("2 foo, 3 bar => 1 foobar"),
        )
        assertEquals(
            Reaction(
                listOf(MaterialAmount("A", 5L), MaterialAmount("B", 11L), MaterialAmount("C", 9L)),
                MaterialAmount("XYZ", 23L)
            ),
            Reaction.from("5 A, 11 B, 9 C => 23 XYZ"),
        )
    }
}
