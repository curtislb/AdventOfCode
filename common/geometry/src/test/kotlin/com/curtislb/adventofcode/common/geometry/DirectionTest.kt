package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.geometry.Direction.DOWN
import com.curtislb.adventofcode.common.geometry.Direction.DOWN_LEFT
import com.curtislb.adventofcode.common.geometry.Direction.DOWN_RIGHT
import com.curtislb.adventofcode.common.geometry.Direction.LEFT
import com.curtislb.adventofcode.common.geometry.Direction.RIGHT
import com.curtislb.adventofcode.common.geometry.Direction.UP
import com.curtislb.adventofcode.common.geometry.Direction.UP_LEFT
import com.curtislb.adventofcode.common.geometry.Direction.UP_RIGHT
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_0
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_135
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_180
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_225
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_270
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_315
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_45
import com.curtislb.adventofcode.common.geometry.RotationAngle.DEGREES_90
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [Direction] enum class.
 */
class DirectionTest {
    @Test
    fun reverse_cardinalDirections() {
        assertThat(UP.reverse()).isEqualTo(DOWN)
        assertThat(RIGHT.reverse()).isEqualTo(LEFT)
        assertThat(DOWN.reverse()).isEqualTo(UP)
        assertThat(LEFT.reverse()).isEqualTo(RIGHT)
    }

    @Test
    fun reverse_diagonalDirections() {
        assertThat(UP_RIGHT.reverse()).isEqualTo(DOWN_LEFT)
        assertThat(DOWN_RIGHT.reverse()).isEqualTo(UP_LEFT)
        assertThat(DOWN_LEFT.reverse()).isEqualTo(UP_RIGHT)
        assertThat(UP_LEFT.reverse()).isEqualTo(DOWN_RIGHT)
    }

    @Test
    fun turnLeft_fromUp_defaultAngle() {
        assertThat(UP.turnLeft()).isEqualTo(LEFT)
    }

    @Test
    fun turnLeft_fromUp_setAngle() {
        assertThat(UP.turnLeft(DEGREES_0)).isEqualTo(UP)
        assertThat(UP.turnLeft(DEGREES_45)).isEqualTo(UP_LEFT)
        assertThat(UP.turnLeft(DEGREES_90)).isEqualTo(LEFT)
        assertThat(UP.turnLeft(DEGREES_135)).isEqualTo(DOWN_LEFT)
        assertThat(UP.turnLeft(DEGREES_180)).isEqualTo(DOWN)
        assertThat(UP.turnLeft(DEGREES_225)).isEqualTo(DOWN_RIGHT)
        assertThat(UP.turnLeft(DEGREES_270)).isEqualTo(RIGHT)
        assertThat(UP.turnLeft(DEGREES_315)).isEqualTo(UP_RIGHT)
    }

    @Test
    fun turnLeft_fromUpRight_defaultAngle() {
        assertThat(UP_RIGHT.turnLeft()).isEqualTo(UP_LEFT)
    }

    @Test
    fun turnLeft_fromUpRight_setAngle() {
        assertThat(UP_RIGHT.turnLeft(DEGREES_0)).isEqualTo(UP_RIGHT)
        assertThat(UP_RIGHT.turnLeft(DEGREES_45)).isEqualTo(UP)
        assertThat(UP_RIGHT.turnLeft(DEGREES_90)).isEqualTo(UP_LEFT)
        assertThat(UP_RIGHT.turnLeft(DEGREES_135)).isEqualTo(LEFT)
        assertThat(UP_RIGHT.turnLeft(DEGREES_180)).isEqualTo(DOWN_LEFT)
        assertThat(UP_RIGHT.turnLeft(DEGREES_225)).isEqualTo(DOWN)
        assertThat(UP_RIGHT.turnLeft(DEGREES_270)).isEqualTo(DOWN_RIGHT)
        assertThat(UP_RIGHT.turnLeft(DEGREES_315)).isEqualTo(RIGHT)
    }

    @Test
    fun turnLeft_fromRight_defaultAngle() {
        assertThat(RIGHT.turnLeft()).isEqualTo(UP)
    }

    @Test
    fun turnLeft_fromRight_setAngle() {
        assertThat(RIGHT.turnLeft(DEGREES_0)).isEqualTo(RIGHT)
        assertThat(RIGHT.turnLeft(DEGREES_45)).isEqualTo(UP_RIGHT)
        assertThat(RIGHT.turnLeft(DEGREES_90)).isEqualTo(UP)
        assertThat(RIGHT.turnLeft(DEGREES_135)).isEqualTo(UP_LEFT)
        assertThat(RIGHT.turnLeft(DEGREES_180)).isEqualTo(LEFT)
        assertThat(RIGHT.turnLeft(DEGREES_225)).isEqualTo(DOWN_LEFT)
        assertThat(RIGHT.turnLeft(DEGREES_270)).isEqualTo(DOWN)
        assertThat(RIGHT.turnLeft(DEGREES_315)).isEqualTo(DOWN_RIGHT)
    }

    @Test
    fun turnLeft_fromDownRight_defaultAngle() {
        assertThat(DOWN_RIGHT.turnLeft()).isEqualTo(UP_RIGHT)
    }

    @Test
    fun turnLeft_fromDownRight_setAngle() {
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_0)).isEqualTo(DOWN_RIGHT)
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_45)).isEqualTo(RIGHT)
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_90)).isEqualTo(UP_RIGHT)
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_135)).isEqualTo(UP)
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_180)).isEqualTo(UP_LEFT)
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_225)).isEqualTo(LEFT)
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_270)).isEqualTo(DOWN_LEFT)
        assertThat(DOWN_RIGHT.turnLeft(DEGREES_315)).isEqualTo(DOWN)
    }

    @Test
    fun turnLeft_fromDown_defaultAngle() {
        assertThat(DOWN.turnLeft()).isEqualTo(RIGHT)
    }

    @Test
    fun turnLeft_fromDown_setAngle() {
        assertThat(DOWN.turnLeft(DEGREES_0)).isEqualTo(DOWN)
        assertThat(DOWN.turnLeft(DEGREES_45)).isEqualTo(DOWN_RIGHT)
        assertThat(DOWN.turnLeft(DEGREES_90)).isEqualTo(RIGHT)
        assertThat(DOWN.turnLeft(DEGREES_135)).isEqualTo(UP_RIGHT)
        assertThat(DOWN.turnLeft(DEGREES_180)).isEqualTo(UP)
        assertThat(DOWN.turnLeft(DEGREES_225)).isEqualTo(UP_LEFT)
        assertThat(DOWN.turnLeft(DEGREES_270)).isEqualTo(LEFT)
        assertThat(DOWN.turnLeft(DEGREES_315)).isEqualTo(DOWN_LEFT)
    }

    @Test
    fun turnLeft_fromDownLeft_defaultAngle() {
        assertThat(DOWN_LEFT.turnLeft()).isEqualTo(DOWN_RIGHT)
    }

    @Test
    fun turnLeft_fromDownLeft_setAngle() {
        assertThat(DOWN_LEFT.turnLeft(DEGREES_0)).isEqualTo(DOWN_LEFT)
        assertThat(DOWN_LEFT.turnLeft(DEGREES_45)).isEqualTo(DOWN)
        assertThat(DOWN_LEFT.turnLeft(DEGREES_90)).isEqualTo(DOWN_RIGHT)
        assertThat(DOWN_LEFT.turnLeft(DEGREES_135)).isEqualTo(RIGHT)
        assertThat(DOWN_LEFT.turnLeft(DEGREES_180)).isEqualTo(UP_RIGHT)
        assertThat(DOWN_LEFT.turnLeft(DEGREES_225)).isEqualTo(UP)
        assertThat(DOWN_LEFT.turnLeft(DEGREES_270)).isEqualTo(UP_LEFT)
        assertThat(DOWN_LEFT.turnLeft(DEGREES_315)).isEqualTo(LEFT)
    }

    @Test
    fun turnLeft_fromLeft_defaultAngle() {
        assertThat(LEFT.turnLeft()).isEqualTo(DOWN)
    }

    @Test
    fun turnLeft_fromLeft_setAngle() {
        assertThat(LEFT.turnLeft(DEGREES_0)).isEqualTo(LEFT)
        assertThat(LEFT.turnLeft(DEGREES_45)).isEqualTo(DOWN_LEFT)
        assertThat(LEFT.turnLeft(DEGREES_90)).isEqualTo(DOWN)
        assertThat(LEFT.turnLeft(DEGREES_135)).isEqualTo(DOWN_RIGHT)
        assertThat(LEFT.turnLeft(DEGREES_180)).isEqualTo(RIGHT)
        assertThat(LEFT.turnLeft(DEGREES_225)).isEqualTo(UP_RIGHT)
        assertThat(LEFT.turnLeft(DEGREES_270)).isEqualTo(UP)
        assertThat(LEFT.turnLeft(DEGREES_315)).isEqualTo(UP_LEFT)
    }

    @Test
    fun turnLeft_fromUpLeft_defaultAngle() {
        assertThat(UP_LEFT.turnLeft()).isEqualTo(DOWN_LEFT)
    }

    @Test
    fun turnLeft_fromUpLeft_setAngle() {
        assertThat(UP_LEFT.turnLeft(DEGREES_0)).isEqualTo(UP_LEFT)
        assertThat(UP_LEFT.turnLeft(DEGREES_45)).isEqualTo(LEFT)
        assertThat(UP_LEFT.turnLeft(DEGREES_90)).isEqualTo(DOWN_LEFT)
        assertThat(UP_LEFT.turnLeft(DEGREES_135)).isEqualTo(DOWN)
        assertThat(UP_LEFT.turnLeft(DEGREES_180)).isEqualTo(DOWN_RIGHT)
        assertThat(UP_LEFT.turnLeft(DEGREES_225)).isEqualTo(RIGHT)
        assertThat(UP_LEFT.turnLeft(DEGREES_270)).isEqualTo(UP_RIGHT)
        assertThat(UP_LEFT.turnLeft(DEGREES_315)).isEqualTo(UP)
    }

    @Test
    fun turnRight_fromUp_defaultAngle() {
        assertThat(UP.turnRight()).isEqualTo(RIGHT)
    }

    @Test
    fun turnRight_fromUp_setAngle() {
        assertThat(UP.turnRight(DEGREES_0)).isEqualTo(UP)
        assertThat(UP.turnRight(DEGREES_45)).isEqualTo(UP_RIGHT)
        assertThat(UP.turnRight(DEGREES_90)).isEqualTo(RIGHT)
        assertThat(UP.turnRight(DEGREES_135)).isEqualTo(DOWN_RIGHT)
        assertThat(UP.turnRight(DEGREES_180)).isEqualTo(DOWN)
        assertThat(UP.turnRight(DEGREES_225)).isEqualTo(DOWN_LEFT)
        assertThat(UP.turnRight(DEGREES_270)).isEqualTo(LEFT)
        assertThat(UP.turnRight(DEGREES_315)).isEqualTo(UP_LEFT)
    }

    @Test
    fun turnRight_fromUpRight_defaultAngle() {
        assertThat(UP_RIGHT.turnRight()).isEqualTo(DOWN_RIGHT)
    }

    @Test
    fun turnRight_fromUpRight_setAngle() {
        assertThat(UP_RIGHT.turnRight(DEGREES_0)).isEqualTo(UP_RIGHT)
        assertThat(UP_RIGHT.turnRight(DEGREES_45)).isEqualTo(RIGHT)
        assertThat(UP_RIGHT.turnRight(DEGREES_90)).isEqualTo(DOWN_RIGHT)
        assertThat(UP_RIGHT.turnRight(DEGREES_135)).isEqualTo(DOWN)
        assertThat(UP_RIGHT.turnRight(DEGREES_180)).isEqualTo(DOWN_LEFT)
        assertThat(UP_RIGHT.turnRight(DEGREES_225)).isEqualTo(LEFT)
        assertThat(UP_RIGHT.turnRight(DEGREES_270)).isEqualTo(UP_LEFT)
        assertThat(UP_RIGHT.turnRight(DEGREES_315)).isEqualTo(UP)
    }

    @Test
    fun turnRight_fromRight_defaultAngle() {
        assertThat(RIGHT.turnRight()).isEqualTo(DOWN)
    }

    @Test
    fun turnRight_fromRight_setAngle() {
        assertThat(RIGHT.turnRight(DEGREES_0)).isEqualTo(RIGHT)
        assertThat(RIGHT.turnRight(DEGREES_45)).isEqualTo(DOWN_RIGHT)
        assertThat(RIGHT.turnRight(DEGREES_90)).isEqualTo(DOWN)
        assertThat(RIGHT.turnRight(DEGREES_135)).isEqualTo(DOWN_LEFT)
        assertThat(RIGHT.turnRight(DEGREES_180)).isEqualTo(LEFT)
        assertThat(RIGHT.turnRight(DEGREES_225)).isEqualTo(UP_LEFT)
        assertThat(RIGHT.turnRight(DEGREES_270)).isEqualTo(UP)
        assertThat(RIGHT.turnRight(DEGREES_315)).isEqualTo(UP_RIGHT)
    }

    @Test
    fun turnRight_fromDownRight_defaultAngle() {
        assertThat(DOWN_RIGHT.turnRight()).isEqualTo(DOWN_LEFT)
    }

    @Test
    fun turnRight_fromDownRight_setAngle() {
        assertThat(DOWN_RIGHT.turnRight(DEGREES_0)).isEqualTo(DOWN_RIGHT)
        assertThat(DOWN_RIGHT.turnRight(DEGREES_45)).isEqualTo(DOWN)
        assertThat(DOWN_RIGHT.turnRight(DEGREES_90)).isEqualTo(DOWN_LEFT)
        assertThat(DOWN_RIGHT.turnRight(DEGREES_135)).isEqualTo(LEFT)
        assertThat(DOWN_RIGHT.turnRight(DEGREES_180)).isEqualTo(UP_LEFT)
        assertThat(DOWN_RIGHT.turnRight(DEGREES_225)).isEqualTo(UP)
        assertThat(DOWN_RIGHT.turnRight(DEGREES_270)).isEqualTo(UP_RIGHT)
        assertThat(DOWN_RIGHT.turnRight(DEGREES_315)).isEqualTo(RIGHT)
    }

    @Test
    fun turnRight_fromDown_defaultAngle() {
        assertThat(DOWN.turnRight()).isEqualTo(LEFT)
    }

    @Test
    fun turnRight_fromDown_setAngle() {
        assertThat(DOWN.turnRight(DEGREES_0)).isEqualTo(DOWN)
        assertThat(DOWN.turnRight(DEGREES_45)).isEqualTo(DOWN_LEFT)
        assertThat(DOWN.turnRight(DEGREES_90)).isEqualTo(LEFT)
        assertThat(DOWN.turnRight(DEGREES_135)).isEqualTo(UP_LEFT)
        assertThat(DOWN.turnRight(DEGREES_180)).isEqualTo(UP)
        assertThat(DOWN.turnRight(DEGREES_225)).isEqualTo(UP_RIGHT)
        assertThat(DOWN.turnRight(DEGREES_270)).isEqualTo(RIGHT)
        assertThat(DOWN.turnRight(DEGREES_315)).isEqualTo(DOWN_RIGHT)
    }

    @Test
    fun turnRight_fromDownLeft_defaultAngle() {
        assertThat(DOWN_LEFT.turnRight()).isEqualTo(UP_LEFT)
    }

    @Test
    fun turnRight_fromDownLeft_setAngle() {
        assertThat(DOWN_LEFT.turnRight(DEGREES_0)).isEqualTo(DOWN_LEFT)
        assertThat(DOWN_LEFT.turnRight(DEGREES_45)).isEqualTo(LEFT)
        assertThat(DOWN_LEFT.turnRight(DEGREES_90)).isEqualTo(UP_LEFT)
        assertThat(DOWN_LEFT.turnRight(DEGREES_135)).isEqualTo(UP)
        assertThat(DOWN_LEFT.turnRight(DEGREES_180)).isEqualTo(UP_RIGHT)
        assertThat(DOWN_LEFT.turnRight(DEGREES_225)).isEqualTo(RIGHT)
        assertThat(DOWN_LEFT.turnRight(DEGREES_270)).isEqualTo(DOWN_RIGHT)
        assertThat(DOWN_LEFT.turnRight(DEGREES_315)).isEqualTo(DOWN)
    }

    @Test
    fun turnRight_fromLeft_defaultAngle() {
        assertThat(LEFT.turnRight()).isEqualTo(UP)
    }

    @Test
    fun turnRight_fromLeft_setAngle() {
        assertThat(LEFT.turnRight(DEGREES_0)).isEqualTo(LEFT)
        assertThat(LEFT.turnRight(DEGREES_45)).isEqualTo(UP_LEFT)
        assertThat(LEFT.turnRight(DEGREES_90)).isEqualTo(UP)
        assertThat(LEFT.turnRight(DEGREES_135)).isEqualTo(UP_RIGHT)
        assertThat(LEFT.turnRight(DEGREES_180)).isEqualTo(RIGHT)
        assertThat(LEFT.turnRight(DEGREES_225)).isEqualTo(DOWN_RIGHT)
        assertThat(LEFT.turnRight(DEGREES_270)).isEqualTo(DOWN)
        assertThat(LEFT.turnRight(DEGREES_315)).isEqualTo(DOWN_LEFT)
    }

    @Test
    fun turnRight_fromUpLeft_defaultAngle() {
        assertThat(UP_LEFT.turnRight()).isEqualTo(UP_RIGHT)
    }

    @Test
    fun turnRight_fromUpLeft_setAngle() {
        assertThat(UP_LEFT.turnRight(DEGREES_0)).isEqualTo(UP_LEFT)
        assertThat(UP_LEFT.turnRight(DEGREES_45)).isEqualTo(UP)
        assertThat(UP_LEFT.turnRight(DEGREES_90)).isEqualTo(UP_RIGHT)
        assertThat(UP_LEFT.turnRight(DEGREES_135)).isEqualTo(RIGHT)
        assertThat(UP_LEFT.turnRight(DEGREES_180)).isEqualTo(DOWN_RIGHT)
        assertThat(UP_LEFT.turnRight(DEGREES_225)).isEqualTo(DOWN)
        assertThat(UP_LEFT.turnRight(DEGREES_270)).isEqualTo(DOWN_LEFT)
        assertThat(UP_LEFT.turnRight(DEGREES_315)).isEqualTo(LEFT)
    }

    @Test
    fun cardinalValues_hasAll() {
        assertThat(Direction.cardinalValues()).containsExactlyInAnyOrder(UP, RIGHT, DOWN, LEFT)
    }

    @Test
    fun fromChar_noDirection() {
        assertThrows<IllegalArgumentException> { Direction.fromChar('A') }
    }

    @Test
    fun fromChar_toUp() {
        assertThat(Direction.fromChar('U')).isEqualTo(UP)
        assertThat(Direction.fromChar('u')).isEqualTo(UP)
    }

    @Test
    fun fromChar_toRight() {
        assertThat(Direction.fromChar('R')).isEqualTo(RIGHT)
        assertThat(Direction.fromChar('r')).isEqualTo(RIGHT)
    }

    @Test
    fun fromChar_toDown() {
        assertThat(Direction.fromChar('D')).isEqualTo(DOWN)
        assertThat(Direction.fromChar('d')).isEqualTo(DOWN)
    }

    @Test
    fun fromChar_toLeft() {
        assertThat(Direction.fromChar('L')).isEqualTo(LEFT)
        assertThat(Direction.fromChar('l')).isEqualTo(LEFT)
    }
}
