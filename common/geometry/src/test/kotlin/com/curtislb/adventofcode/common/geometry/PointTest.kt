package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.testing.isApproximately
import kotlin.math.PI
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [Point] class.
 */
class PointTest {
    @Test
    fun matrixRow_whenOrigin() {
        val point = Point.ORIGIN
        assertThat(point.matrixRow).isEqualTo(0)
    }

    @Test
    fun matrixRow_withZeroY() {
        val point = Point(1, 0)
        assertThat(point.matrixRow).isEqualTo(0)
    }

    @Test
    fun matrixRow_withNegativeY() {
        val point = Point(1, -2)
        assertThat(point.matrixRow).isEqualTo(2)
    }

    @Test
    fun matrixRow_withPositiveY() {
        val point = Point(1, 3)
        assertThat(point.matrixRow).isEqualTo(-3)
    }

    @Test
    fun matrixCol_whenOrigin() {
        val point = Point.ORIGIN
        assertThat(point.matrixCol).isEqualTo(0)
    }

    @Test
    fun matrixCol_withZeroX() {
        val point = Point(0, 1)
        assertThat(point.matrixCol).isEqualTo(0)
    }

    @Test
    fun matrixCol_withNegativeX() {
        val point = Point(-2, 1)
        assertThat(point.matrixCol).isEqualTo(-2)
    }

    @Test
    fun matrixCol_withPositiveX() {
        val point = Point(3, 1)
        assertThat(point.matrixCol).isEqualTo(3)
    }

    @Test
    fun plus_bothOrigin() {
        val point = Point.ORIGIN
        val other = Point.ORIGIN
        assertThat(point + other).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun plus_leftOrigin() {
        val point = Point.ORIGIN
        val other = Point(-89, 85)
        assertThat(point + other).isEqualTo(other)
    }

    @Test
    fun plus_rightOrigin() {
        val point = Point(68, 3)
        val other = Point.ORIGIN
        assertThat(point + other).isEqualTo(point)
    }

    @Test
    fun plus_bothNotOrigin() {
        val point = Point(-10, 15)
        val other = Point(-59, -12)
        assertThat(point + other).isEqualTo(Point(-69, 3))
    }

    @Test
    fun minus_bothOrigin() {
        val point = Point.ORIGIN
        val other = Point.ORIGIN
        assertThat(point - other).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun minus_leftOrigin() {
        val point = Point.ORIGIN
        val other = Point(-27, -11)
        assertThat(point - other).isEqualTo(Point(27, 11))
    }

    @Test
    fun minus_rightOrigin() {
        val point = Point(20, 93)
        val other = Point.ORIGIN
        assertThat(point - other).isEqualTo(point)
    }

    @Test
    fun minus_bothNotOrigin() {
        val point = Point(-53, 1)
        val other = Point(-62, 39)
        assertThat(point - other).isEqualTo(Point(9, -38))
    }

    @Test
    fun unaryMinus_whenOrigin() {
        val point = Point.ORIGIN
        assertThat(-point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun unaryMinus_whenNotOrigin() {
        val point = Point(-97, 20)
        assertThat(-point).isEqualTo(Point(97, -20))
    }

    @Test
    fun manhattanDistance_bothOrigin() {
        val point = Point.ORIGIN
        val other = Point.ORIGIN
        assertThat(point manhattanDistance other).isEqualTo(0)
    }

    @Test
    fun manhattanDistance_leftOrigin() {
        val point = Point.ORIGIN
        val other = Point(-7, -87)
        assertThat(point manhattanDistance other).isEqualTo(94)
    }

    @Test
    fun manhattanDistance_rightOrigin() {
        val point = Point(7, -54)
        val other = Point.ORIGIN
        assertThat(point manhattanDistance other).isEqualTo(61)
    }

    @Test
    fun manhattanDistance_bothNotOrigin() {
        val point = Point(-4, -19)
        val other = Point(12, -42)
        assertThat(point manhattanDistance other).isEqualTo(39)
    }

    @Test
    fun squaredDistance_bothOrigin() {
        val point = Point.ORIGIN
        val other = Point.ORIGIN
        assertThat(point squaredDistance other).isEqualTo(0L)
    }

    @Test
    fun squaredDistance_leftOrigin() {
        val point = Point.ORIGIN
        val other = Point(-1, 95)
        assertThat(point squaredDistance other).isEqualTo(9026L)
    }

    @Test
    fun squaredDistance_rightOrigin() {
        val point = Point(-31, 19)
        val other = Point.ORIGIN
        assertThat(point squaredDistance other).isEqualTo(1322L)
    }

    @Test
    fun squaredDistance_bothNotOrigin() {
        val point = Point(42, 16)
        val other = Point(-18, 88)
        assertThat(point squaredDistance other).isEqualTo(8784L)
    }

    @Test
    fun allNeighbors_whenOrigin() {
        val point = Point.ORIGIN
        assertThat(point.allNeighbors()).containsExactlyInAnyOrder(
            Point(-1, -1),
            Point(-1, 0),
            Point(-1, 1),
            Point(0, -1),
            Point(0, 1),
            Point(1, -1),
            Point(1, 0),
            Point(1, 1)
        )
    }

    @Test
    fun allNeighbors_whenNotOrigin() {
        val point = Point(80, -60)
        assertThat(point.allNeighbors()).containsExactlyInAnyOrder(
            Point(79, -61),
            Point(79, -60),
            Point(79, -59),
            Point(80, -61),
            Point(80, -59),
            Point(81, -61),
            Point(81, -60),
            Point(81, -59)
        )
    }

    @Test
    fun cardinalNeighbors_whenOrigin() {
        val point = Point.ORIGIN
        assertThat(point.cardinalNeighbors()).containsExactlyInAnyOrder(
            Point(-1, 0),
            Point(0, -1),
            Point(0, 1),
            Point(1, 0)
        )
    }

    @Test
    fun cardinalNeighbors_whenNotOrigin() {
        val point = Point(78, 66)
        assertThat(point.cardinalNeighbors()).containsExactlyInAnyOrder(
            Point(77, 66),
            Point(78, 65),
            Point(78, 67),
            Point(79, 66)
        )
    }

    @Test
    fun diagonalNeighbors_whenOrigin() {
        val point = Point.ORIGIN
        assertThat(point.diagonalNeighbors()).containsExactlyInAnyOrder(
            Point(-1, -1),
            Point(-1, 1),
            Point(1, -1),
            Point(1, 1)
        )
    }

    @Test
    fun diagonalNeighbors_whenNotOrigin() {
        val point = Point(-54, -9)
        assertThat(point.diagonalNeighbors()).containsExactlyInAnyOrder(
            Point(-55, -10),
            Point(-55, -8),
            Point(-53, -10),
            Point(-53, -8)
        )
    }

    @Test
    fun move_whenOrigin_defaultDistance_cardinalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP)).isEqualTo(Point(0, 1))
        assertThat(point.move(Direction.RIGHT)).isEqualTo(Point(1, 0))
        assertThat(point.move(Direction.DOWN)).isEqualTo(Point(0, -1))
        assertThat(point.move(Direction.LEFT)).isEqualTo(Point(-1, 0))
    }

    @Test
    fun move_whenOrigin_defaultDistance_diagonalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP_RIGHT)).isEqualTo(Point(1, 1))
        assertThat(point.move(Direction.DOWN_RIGHT)).isEqualTo(Point(1, -1))
        assertThat(point.move(Direction.DOWN_LEFT)).isEqualTo(Point(-1, -1))
        assertThat(point.move(Direction.UP_LEFT)).isEqualTo(Point(-1, 1))
    }

    @Test
    fun move_whenOrigin_zeroDistance_cardinalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP, distance = 0)).isEqualTo(Point.ORIGIN)
        assertThat(point.move(Direction.RIGHT, distance = 0)).isEqualTo(Point.ORIGIN)
        assertThat(point.move(Direction.DOWN, distance = 0)).isEqualTo(Point.ORIGIN)
        assertThat(point.move(Direction.LEFT, distance = 0)).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun move_whenOrigin_zeroDistance_diagonalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP_RIGHT, distance = 0)).isEqualTo(Point.ORIGIN)
        assertThat(point.move(Direction.DOWN_RIGHT, distance = 0)).isEqualTo(Point.ORIGIN)
        assertThat(point.move(Direction.DOWN_LEFT, distance = 0)).isEqualTo(Point.ORIGIN)
        assertThat(point.move(Direction.UP_LEFT, distance = 0)).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun move_whenOrigin_positiveDistance_cardinalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP, distance = 81)).isEqualTo(Point(0, 81))
        assertThat(point.move(Direction.RIGHT, distance = 52)).isEqualTo(Point(52, 0))
        assertThat(point.move(Direction.DOWN, distance = 49)).isEqualTo(Point(0, -49))
        assertThat(point.move(Direction.LEFT, distance = 72)).isEqualTo(Point(-72, 0))
    }

    @Test
    fun move_whenOrigin_positiveDistance_diagonalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP_RIGHT, distance = 97)).isEqualTo(Point(97, 97))
        assertThat(point.move(Direction.DOWN_RIGHT, distance = 11)).isEqualTo(Point(11, -11))
        assertThat(point.move(Direction.DOWN_LEFT, distance = 67)).isEqualTo(Point(-67, -67))
        assertThat(point.move(Direction.UP_LEFT, distance = 21)).isEqualTo(Point(-21, 21))
    }

    @Test
    fun move_whenOrigin_negativeDistance_cardinalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP, distance = -36)).isEqualTo(Point(0, -36))
        assertThat(point.move(Direction.RIGHT, distance = -89)).isEqualTo(Point(-89, 0))
        assertThat(point.move(Direction.DOWN, distance = -8)).isEqualTo(Point(0, 8))
        assertThat(point.move(Direction.LEFT, distance = -97)).isEqualTo(Point(97, 0))
    }

    @Test
    fun move_whenOrigin_negativeDistance_diagonalDirections() {
        val point = Point.ORIGIN
        assertThat(point.move(Direction.UP_RIGHT, distance = -13)).isEqualTo(Point(-13, -13))
        assertThat(point.move(Direction.DOWN_RIGHT, distance = -62)).isEqualTo(Point(-62, 62))
        assertThat(point.move(Direction.DOWN_LEFT, distance = -31)).isEqualTo(Point(31, 31))
        assertThat(point.move(Direction.UP_LEFT, distance = -94)).isEqualTo(Point(94, -94))
    }
    @Test
    fun move_whenNotOrigin_defaultDistance_cardinalDirections() {
        val point = Point(91, 77)
        assertThat(point.move(Direction.UP)).isEqualTo(Point(91, 78))
        assertThat(point.move(Direction.RIGHT)).isEqualTo(Point(92, 77))
        assertThat(point.move(Direction.DOWN)).isEqualTo(Point(91, 76))
        assertThat(point.move(Direction.LEFT)).isEqualTo(Point(90, 77))
    }

    @Test
    fun move_whenNotOrigin_defaultDistance_diagonalDirections() {
        val point = Point(-64, -34)
        assertThat(point.move(Direction.UP_RIGHT)).isEqualTo(Point(-63, -33))
        assertThat(point.move(Direction.DOWN_RIGHT)).isEqualTo(Point(-63, -35))
        assertThat(point.move(Direction.DOWN_LEFT)).isEqualTo(Point(-65, -35))
        assertThat(point.move(Direction.UP_LEFT)).isEqualTo(Point(-65, -33))
    }

    @Test
    fun move_whenNotOrigin_zeroDistance_cardinalDirections() {
        val point = Point(-92, 14)
        assertThat(point.move(Direction.UP, distance = 0)).isEqualTo(point)
        assertThat(point.move(Direction.RIGHT, distance = 0)).isEqualTo(point)
        assertThat(point.move(Direction.DOWN, distance = 0)).isEqualTo(point)
        assertThat(point.move(Direction.LEFT, distance = 0)).isEqualTo(point)
    }

    @Test
    fun move_whenNotOrigin_zeroDistance_diagonalDirections() {
        val point = Point(6, -53)
        assertThat(point.move(Direction.UP_RIGHT, distance = 0)).isEqualTo(point)
        assertThat(point.move(Direction.DOWN_RIGHT, distance = 0)).isEqualTo(point)
        assertThat(point.move(Direction.DOWN_LEFT, distance = 0)).isEqualTo(point)
        assertThat(point.move(Direction.UP_LEFT, distance = 0)).isEqualTo(point)
    }

    @Test
    fun move_whenNotOrigin_positiveDistance_cardinalDirections() {
        val point = Point(-28, -8)
        assertThat(point.move(Direction.UP, distance = 37)).isEqualTo(Point(-28, 29))
        assertThat(point.move(Direction.RIGHT, distance = 12)).isEqualTo(Point(-16, -8))
        assertThat(point.move(Direction.DOWN, distance = 16)).isEqualTo(Point(-28, -24))
        assertThat(point.move(Direction.LEFT, distance = 55)).isEqualTo(Point(-83, -8))
    }

    @Test
    fun move_whenNotOrigin_positiveDistance_diagonalDirections() {
        val point = Point(48, -54)
        assertThat(point.move(Direction.UP_RIGHT, distance = 58)).isEqualTo(Point(106, 4))
        assertThat(point.move(Direction.DOWN_RIGHT, distance = 8)).isEqualTo(Point(56, -62))
        assertThat(point.move(Direction.DOWN_LEFT, distance = 86)).isEqualTo(Point(-38, -140))
        assertThat(point.move(Direction.UP_LEFT, distance = 5)).isEqualTo(Point(43, -49))
    }

    @Test
    fun move_whenNotOrigin_negativeDistance_cardinalDirections() {
        val point = Point(-15, 73)
        assertThat(point.move(Direction.UP, distance = -41)).isEqualTo(Point(-15, 32))
        assertThat(point.move(Direction.RIGHT, distance = -76)).isEqualTo(Point(-91, 73))
        assertThat(point.move(Direction.DOWN, distance = -66)).isEqualTo(Point(-15, 139))
        assertThat(point.move(Direction.LEFT, distance = -25)).isEqualTo(Point(10, 73))
    }

    @Test
    fun move_whenNotOrigin_negativeDistance_diagonalDirections() {
        val point = Point(85, -68)
        assertThat(point.move(Direction.UP_RIGHT, distance = -55)).isEqualTo(Point(30, -123))
        assertThat(point.move(Direction.DOWN_RIGHT, distance = -75)).isEqualTo(Point(10, 7))
        assertThat(point.move(Direction.DOWN_LEFT, distance = -93)).isEqualTo(Point(178, 25))
        assertThat(point.move(Direction.UP_LEFT, distance = -8)).isEqualTo(Point(93, -76))
    }

    @Test
    fun rotateClockwise_whenOrigin_defaultCenter() {
        val point = Point.ORIGIN
        assertThat(point.rotateClockwise()).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun rotateClockwise_whenOrigin_nonDefaultCenter() {
        val point = Point.ORIGIN
        assertThat(point.rotateClockwise(center = Point(-81, 10))).isEqualTo(Point(-91, -71))
    }

    @Test
    fun rotateClockwise_whenNotOrigin_defaultCenter() {
        val point = Point(68, -48)
        assertThat(point.rotateClockwise()).isEqualTo(Point(-48, -68))
    }

    @Test
    fun rotateClockwise_whenNotOrigin_nonDefaultCenter() {
        val point = Point(-93, -52)
        assertThat(point.rotateClockwise(center = Point(6, -3))).isEqualTo(Point(-43, 96))
    }

    @Test
    fun rotateCounterclockwise_whenOrigin_defaultCenter() {
        val point = Point.ORIGIN
        assertThat(point.rotateCounterclockwise()).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun rotateCounterclockwise_whenOrigin_nonDefaultCenter() {
        val point = Point.ORIGIN
        assertThat(point.rotateCounterclockwise(center = Point(70, 75))).isEqualTo(Point(145, 5))
    }

    @Test
    fun rotateCounterclockwise_whenNotOrigin_defaultCenter() {
        val point = Point(-94, -13)
        assertThat(point.rotateCounterclockwise()).isEqualTo(Point(13, -94))
    }

    @Test
    fun rotateCounterclockwise_whenNotOrigin_nonDefaultCenter() {
        val point = Point(-23, 64)
        assertThat(point.rotateCounterclockwise(center = Point(65, -53)))
            .isEqualTo(Point(-52, -141))
    }

    @Test
    fun rotate180Degrees_whenOrigin_defaultCenter() {
        val point = Point.ORIGIN
        assertThat(point.rotate180Degrees()).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun rotate180Degrees_whenOrigin_nonDefaultCenter() {
        val point = Point.ORIGIN
        assertThat(point.rotate180Degrees(center = Point(32, 46))).isEqualTo(Point(64, 92))
    }

    @Test
    fun rotate180Degrees_whenNotOrigin_defaultCenter() {
        val point = Point(49, -69)
        assertThat(point.rotate180Degrees()).isEqualTo(Point(-49, 69))
    }

    @Test
    fun rotate180Degrees_whenNotOrigin_nonDefaultCenter() {
        val point = Point(55, 33)
        assertThat(point.rotate180Degrees(center = Point(-91, -5))).isEqualTo(Point(-237, -43))
    }

    @Test
    fun clockwiseAngleFromYTo_bothOrigin() {
        val point = Point.ORIGIN
        val other = Point.ORIGIN
        assertThrows<IllegalArgumentException> { point.clockwiseAngleFromYTo(other) }
    }

    @Test
    fun clockwiseAngleFromYTo_bothSamePoint() {
        val point = Point(46, 28)
        val other = Point(46, 28)
        assertThrows<IllegalArgumentException> { point.clockwiseAngleFromYTo(other) }
    }

    @Test
    fun clockwiseAngleFromYTo_otherDirectlyUp() {
        val point = Point(-80, 16)
        val other = Point(-80, 28)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(0.0)
    }

    @Test
    fun clockwiseAngleFromYTo_otherDirectlyRight() {
        val point = Point(-33, 91)
        val other = Point(-5, 91)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(PI / 2.0)
    }

    @Test
    fun clockwiseAngleFromYTo_otherDirectlyDown() {
        val point = Point(53, 61)
        val other = Point(53, -3)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(PI)
    }

    @Test
    fun clockwiseAngleFromYTo_otherDirectlyLeft() {
        val point = Point(90, 58)
        val other = Point(76, 58)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(PI * 1.5)
    }

    @Test
    fun clockwiseAngleFromYTo_otherUpAndRight() {
        val point = Point(-64, -20)
        val other = Point(-18, 28)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(0.764125)
    }

    @Test
    fun clockwiseAngleFromYTo_otherDownAndRight() {
        val point = Point(4, 52)
        val other = Point(81, 71)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(1.328876)
    }

    @Test
    fun clockwiseAngleFromYTo_otherDownAndLeft() {
        val point = Point(-8, 11)
        val other = Point(-43, -46)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(3.692268)
    }

    @Test
    fun clockwiseAngleFromYTo_otherUpAndLeft() {
        val point = Point(92, -94)
        val other = Point(-26, -86)
        assertThat(point.clockwiseAngleFromYTo(other)).isApproximately(4.780082)
    }

    @Test
    fun toString_whenOrigin() {
        val point = Point.ORIGIN
        assertThat(point.toString()).isEqualTo("(0, 0)")
    }

    @Test
    fun toString_whenNotOrigin() {
        val point = Point(-26, 11)
        assertThat(point.toString()).isEqualTo("(-26, 11)")
    }

    @Test
    fun fromMatrixCoordinates_bothIndicesZero() {
        val point = Point.fromMatrixCoordinates(rowIndex = 0, colIndex = 0)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromMatrixCoordinates_rowIndexNegative() {
        val point = Point.fromMatrixCoordinates(rowIndex = -1, colIndex = 0)
        assertThat(point).isEqualTo(Point(0, 1))
    }

    @Test
    fun fromMatrixCoordinates_colIndexNegative() {
        val point = Point.fromMatrixCoordinates(rowIndex = 0, colIndex = -1)
        assertThat(point).isEqualTo(Point(-1, 0))
    }

    @Test
    fun fromMatrixCoordinates_bothIndicesPositive() {
        val point = Point.fromMatrixCoordinates(rowIndex = 13, colIndex = 21)
        assertThat(point).isEqualTo(Point(21, -13))
    }

    @Test
    fun fromString_origin_noParens_noSpace() {
        val string = "0,0"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_origin_noParens_noSpace_invertY() {
        val string = "0,0"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_origin_noParens_withSpace() {
        val string = "0, 0"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_origin_noParens_withSpace_invertY() {
        val string = "0, 0"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_origin_withParens_noSpace() {
        val string = "(0,0)"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_origin_withParens_noSpace_invertY() {
        val string = "(0,0)"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_origin_withParens_withSpace() {
        val string = "(0, 0)"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_origin_withParens_withSpace_invertY() {
        val string = "(0, 0)"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point.ORIGIN)
    }

    @Test
    fun fromString_xZero_noParens_noSpace() {
        val string = "0,38"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(0, 38))
    }

    @Test
    fun fromString_xZero_noParens_noSpace_invertY() {
        val string = "0,38"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(0, -38))
    }

    @Test
    fun fromString_xZero_noParens_withSpace() {
        val string = "0, -56"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(0, -56))
    }

    @Test
    fun fromString_xZero_noParens_withSpace_invertY() {
        val string = "0, -56"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(0, 56))
    }

    @Test
    fun fromString_xZero_withParens_noSpace() {
        val string = "(0,85)"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(0, 85))
    }

    @Test
    fun fromString_xZero_withParens_noSpace_invertY() {
        val string = "(0,85)"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(0, -85))
    }

    @Test
    fun fromString_xZero_withParens_withSpace() {
        val string = "(0, -75)"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(0, -75))
    }

    @Test
    fun fromString_xZero_withParens_withSpace_invertY() {
        val string = "(0, -75)"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(0, 75))
    }

    @Test
    fun fromString_xyNonzero_noParens_noSpace() {
        val string = "-44,13"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(-44, 13))
    }

    @Test
    fun fromString_xyNonzero_noParens_noSpace_invertY() {
        val string = "-44,13"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(-44, -13))
    }

    @Test
    fun fromString_xyNonzero_noParens_withSpace() {
        val string = "54, -52"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(54, -52))
    }

    @Test
    fun fromString_xyNonzero_noParens_withSpace_invertY() {
        val string = "54, -52"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(54, 52))
    }

    @Test
    fun fromString_xyNonzero_withParens_noSpace() {
        val string = "(-51,-93)"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(-51, -93))
    }

    @Test
    fun fromString_xyNonzero_withParens_noSpace_invertY() {
        val string = "(-51,-93)"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(-51, 93))
    }

    @Test
    fun fromString_xyNonzero_withParens_withSpace() {
        val string = "(1, 67)"
        val point = Point.fromString(string)
        assertThat(point).isEqualTo(Point(1, 67))
    }

    @Test
    fun fromString_xyNonzero_withParens_withSpace_invertY() {
        val string = "(1, 67)"
        val point = Point.fromString(string, invertY = true)
        assertThat(point).isEqualTo(Point(1, -67))
    }

    @Test
    fun fromString_wrongFormat() {
        val string = "(-13, 72) "
        assertThrows<IllegalArgumentException> { Point.fromString(string) }
    }

    @Test
    fun fromString_wrongFormat_invertY() {
        val string = "(-13, 72) "
        assertThrows<IllegalArgumentException> { Point.fromString(string, invertY = true) }
    }
}
