package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.geometry.Direction.DOWN
import com.curtislb.adventofcode.common.geometry.Direction.DOWN_LEFT
import com.curtislb.adventofcode.common.geometry.Direction.DOWN_RIGHT
import com.curtislb.adventofcode.common.geometry.Direction.LEFT
import com.curtislb.adventofcode.common.geometry.Direction.RIGHT
import com.curtislb.adventofcode.common.geometry.Direction.UP
import com.curtislb.adventofcode.common.geometry.Direction.UP_LEFT
import com.curtislb.adventofcode.common.geometry.Direction.UP_RIGHT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [Pose] class.
 */
class PoseTest {
    @Test
    fun move_defaultDirection_defaultDistance() {
        val pose = Pose(position = Point(35, -39), direction = UP)
        assertThat(pose.move()).isEqualTo(Pose(position = Point(35, -38), direction = UP))
    }

    @Test
    fun move_defaultDirection_nonDefaultDistance() {
        val pose = Pose(position = Point(-83, -57), direction = LEFT)
        assertThat(pose.move(distance = -31))
            .isEqualTo(Pose(position = Point(-52, -57), direction = LEFT))
    }

    @Test
    fun move_nonDefaultDirection_defaultDistance() {
        val pose = Pose(position = Point(4, 15), direction = RIGHT)
        assertThat(pose.move(DOWN)).isEqualTo(Pose(position = Point(4, 14), direction = RIGHT))
    }

    @Test
    fun move_nonDefaultDirection_nonDefaultDistance() {
        val pose = Pose(position = Point(-57, 96), direction = DOWN)
        assertThat(pose.move(UP_LEFT, distance = 3))
            .isEqualTo(Pose(position = Point(-60, 99), direction = DOWN))
    }

    @Test
    fun turnAround() {
        val position = Point(81, 80)
        assertThat(Pose(position, UP_RIGHT).turnAround()).isEqualTo(Pose(position, DOWN_LEFT))
    }

    @Test
    fun turnRight_defaultAngle() {
        val position = Point(-43, -44)
        val pose = Pose(position, direction = DOWN_RIGHT)
        assertThat(pose.turnRight()).isEqualTo(Pose(position, direction = DOWN_LEFT))
    }

    @Test
    fun turnRight_nonDefaultAngle() {
        val position = Point(-60, -79)
        val pose = Pose(position, direction = LEFT)
        assertThat(pose.turnRight(RotationAngle.DEGREES_135))
            .isEqualTo(Pose(position, direction = UP_RIGHT))
    }

    @Test
    fun turnLeft_defaultAngle() {
        val position = Point(-12, 59)
        val pose = Pose(position, direction = UP)
        assertThat(pose.turnLeft()).isEqualTo(Pose(position, direction = LEFT))
    }

    @Test
    fun turnLeft_nonDefaultAngle() {
        val position = Point(-62, 25)
        val pose = Pose(position, direction = UP_LEFT)
        assertThat(pose.turnLeft(RotationAngle.DEGREES_225))
            .isEqualTo(Pose(position, direction = RIGHT))
    }

    @Test
    fun turnToFace_sameDirection() {
        val position = Point(78, -24)
        val pose = Pose(position, direction = UP_RIGHT)
        assertThat(pose.turnToFace(UP_RIGHT)).isEqualTo(pose)
    }

    @Test
    fun turnToFace_differentDirection() {
        val position = Point(41, 17)
        val pose = Pose(position, direction = DOWN)
        assertThat(pose.turnToFace(DOWN_RIGHT)).isEqualTo(Pose(position, direction = DOWN_RIGHT))
    }
}
