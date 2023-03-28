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
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [Segment] class.
 */
class SegmentTest {
    @Test
    fun startAndEnd_whenPoint() {
        val point = Point(-53, -30)
        val segment = Segment.ofPoint(point)
        assertThat(segment.start).isEqualTo(point)
        assertThat(segment.end).isEqualTo(point)
    }

    @Test
    fun startAndEnd_whenHorizontal_directionRight() {
        val point = Point(1, 66)
        val segment = Segment.from(point, direction = RIGHT, distance = 17)
        assertThat(segment.start).isEqualTo(point)
        assertThat(segment.end).isEqualTo(Point(18, 66))
    }

    @Test
    fun startAndEnd_whenHorizontal_directionLeft() {
        val point = Point(58, 34)
        val segment = Segment.from(point, direction = LEFT, distance = 90)
        assertThat(segment.start).isEqualTo(Point(-32, 34))
        assertThat(segment.end).isEqualTo(point)
    }

    @Test
    fun startAndEnd_whenVertical_directionUp() {
        val point = Point(-22, -33)
        val segment = Segment.from(point, direction = UP, distance = 51)
        assertThat(segment.start).isEqualTo(point)
        assertThat(segment.end).isEqualTo(Point(-22, 18))
    }

    @Test
    fun startAndEnd_whenVertical_directionDown() {
        val point = Point(-73, 84)
        val segment = Segment.from(point, direction = DOWN, distance = 78)
        assertThat(segment.start).isEqualTo(Point(-73, 6))
        assertThat(segment.end).isEqualTo(point)
    }

    @Test
    fun startAndEnd_whenDiagonal_directionUpRight() {
        val point = Point(-75, 73)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 20)
        assertThat(segment.start).isEqualTo(point)
        assertThat(segment.end).isEqualTo(Point(-55, 93))
    }

    @Test
    fun startAndEnd_whenDiagonal_directionDownRight() {
        val point = Point(68, -5)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 48)
        assertThat(segment.start).isEqualTo(point)
        assertThat(segment.end).isEqualTo(Point(116, -53))
    }

    @Test
    fun startAndEnd_whenDiagonal_directionUpLeft() {
        val point = Point(95, -97)
        val segment = Segment.from(point, direction = UP_LEFT, distance = 26)
        assertThat(segment.start).isEqualTo(Point(69, -71))
        assertThat(segment.end).isEqualTo(point)
    }

    @Test
    fun startAndEnd_whenDiagonal_directionDownLeft() {
        val point = Point(-22, -46)
        val segment = Segment.from(point, direction = DOWN_LEFT, distance = 23)
        assertThat(segment.start).isEqualTo(Point(-45, -69))
        assertThat(segment.end).isEqualTo(point)
    }

    @Test
    fun coordRanges_whenPoint() {
        val point = Point(-53, -30)
        val segment = Segment.ofPoint(point)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = -53..-53, y = -30..-30))
    }

    @Test
    fun coordRanges_whenHorizontal_directionRight() {
        val point = Point(1, 66)
        val segment = Segment.from(point, direction = RIGHT, distance = 17)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = 1..18, y = 66..66))
    }

    @Test
    fun coordRanges_whenHorizontal_directionLeft() {
        val point = Point(58, 34)
        val segment = Segment.from(point, direction = LEFT, distance = 90)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = -32..58, y = 34..34))
    }

    @Test
    fun coordRanges_whenVertical_directionUp() {
        val point = Point(-22, -33)
        val segment = Segment.from(point, direction = UP, distance = 51)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = -22..-22, y = -33..18))
    }

    @Test
    fun coordRanges_whenVertical_directionDown() {
        val point = Point(-73, 84)
        val segment = Segment.from(point, direction = DOWN, distance = 78)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = -73..-73, y = 6..84))
    }

    @Test
    fun coordRanges_whenDiagonal_directionUpRight() {
        val point = Point(-75, 73)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 20)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = -75..-55, y = 73..93))
    }

    @Test
    fun coordRanges_whenDiagonal_directionDownRight() {
        val point = Point(68, -5)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 48)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = 68..116, y = -53..-5))
    }

    @Test
    fun coordRanges_whenDiagonal_directionUpLeft() {
        val point = Point(95, -97)
        val segment = Segment.from(point, direction = UP_LEFT, distance = 26)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = 69..95, y = -97..-71))
    }

    @Test
    fun coordRanges_whenDiagonal_directionDownLeft() {
        val point = Point(-22, -46)
        val segment = Segment.from(point, direction = DOWN_LEFT, distance = 23)
        assertThat(segment.coordRanges).isEqualTo(CoordinateRanges(x = -45..-22, y = -69..-46))
    }

    @Test
    fun isPoint_whenPoint() {
        val point = Point(-38, -65)
        val segment = Segment.ofPoint(point)
        assertThat(segment.isPoint).isTrue
    }

    @Test
    fun isPoint_whenHorizontal() {
        val point = Point(-24, 13)
        val segment = Segment.from(point, direction = RIGHT, distance = 9)
        assertThat(segment.isPoint).isFalse
    }

    @Test
    fun isPoint_whenVertical() {
        val point = Point(50, -4)
        val segment = Segment.from(point, direction = UP, distance = 84)
        assertThat(segment.isPoint).isFalse
    }

    @Test
    fun isPoint_whenUpRightDiagonal() {
        val point = Point(-37, 94)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 6)
        assertThat(segment.isPoint).isFalse
    }

    @Test
    fun isPoint_whenDownRightDiagonal() {
        val point = Point(69, -51)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 27)
        assertThat(segment.isPoint).isFalse
    }

    @Test
    fun isHorizontal_whenPoint() {
        val point = Point(-38, -65)
        val segment = Segment.ofPoint(point)
        assertThat(segment.isHorizontal).isFalse
    }

    @Test
    fun isHorizontal_whenHorizontal() {
        val point = Point(-24, 13)
        val segment = Segment.from(point, direction = RIGHT, distance = 9)
        assertThat(segment.isHorizontal).isTrue
    }

    @Test
    fun isHorizontal_whenVertical() {
        val point = Point(50, -4)
        val segment = Segment.from(point, direction = UP, distance = 84)
        assertThat(segment.isHorizontal).isFalse
    }

    @Test
    fun isHorizontal_whenUpRightDiagonal() {
        val point = Point(-37, 94)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 6)
        assertThat(segment.isHorizontal).isFalse
    }

    @Test
    fun isHorizontal_whenDownRightDiagonal() {
        val point = Point(69, -51)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 27)
        assertThat(segment.isHorizontal).isFalse
    }

    @Test
    fun isVertical_whenPoint() {
        val point = Point(-38, -65)
        val segment = Segment.ofPoint(point)
        assertThat(segment.isVertical).isFalse
    }

    @Test
    fun isVertical_whenHorizontal() {
        val point = Point(-24, 13)
        val segment = Segment.from(point, direction = RIGHT, distance = 9)
        assertThat(segment.isVertical).isFalse
    }

    @Test
    fun isVertical_whenVertical() {
        val point = Point(50, -4)
        val segment = Segment.from(point, direction = UP, distance = 84)
        assertThat(segment.isVertical).isTrue
    }

    @Test
    fun isVertical_whenUpRightDiagonal() {
        val point = Point(-37, 94)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 6)
        assertThat(segment.isVertical).isFalse
    }

    @Test
    fun isVertical_whenDownRightDiagonal() {
        val point = Point(69, -51)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 27)
        assertThat(segment.isVertical).isFalse
    }

    @Test
    fun isDiagonal_whenPoint() {
        val point = Point(-38, -65)
        val segment = Segment.ofPoint(point)
        assertThat(segment.isDiagonal).isFalse
    }

    @Test
    fun isDiagonal_whenHorizontal() {
        val point = Point(-24, 13)
        val segment = Segment.from(point, direction = RIGHT, distance = 9)
        assertThat(segment.isDiagonal).isFalse
    }

    @Test
    fun isDiagonal_whenVertical() {
        val point = Point(50, -4)
        val segment = Segment.from(point, direction = UP, distance = 84)
        assertThat(segment.isDiagonal).isFalse
    }

    @Test
    fun isDiagonal_whenUpRightDiagonal() {
        val point = Point(-37, 94)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 6)
        assertThat(segment.isDiagonal).isTrue
    }

    @Test
    fun isDiagonal_whenDownRightDiagonal() {
        val point = Point(69, -51)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 27)
        assertThat(segment.isDiagonal).isTrue
    }

    @Test
    fun otherEndpoint_whenPoint() {
        val point = Point(-53, -30)
        val segment = Segment.ofPoint(point)
        assertThat(segment.otherEndpoint(point)).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenHorizontal_directionRight() {
        val point = Point(1, 66)
        val segment = Segment.from(point, direction = RIGHT, distance = 17)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(18, 66))
        assertThat(segment.otherEndpoint(Point(18, 66))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenHorizontal_directionLeft() {
        val point = Point(58, 34)
        val segment = Segment.from(point, direction = LEFT, distance = 90)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(-32, 34))
        assertThat(segment.otherEndpoint(Point(-32, 34))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenVertical_directionUp() {
        val point = Point(-22, -33)
        val segment = Segment.from(point, direction = UP, distance = 51)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(-22, 18))
        assertThat(segment.otherEndpoint(Point(-22, 18))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenVertical_directionDown() {
        val point = Point(-73, 84)
        val segment = Segment.from(point, direction = DOWN, distance = 78)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(-73, 6))
        assertThat(segment.otherEndpoint(Point(-73, 6))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenDiagonal_directionUpRight() {
        val point = Point(-75, 73)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 20)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(-55, 93))
        assertThat(segment.otherEndpoint(Point(-55, 93))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenDiagonal_directionDownRight() {
        val point = Point(68, -5)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 48)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(116, -53))
        assertThat(segment.otherEndpoint(Point(116, -53))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenDiagonal_directionUpLeft() {
        val point = Point(95, -97)
        val segment = Segment.from(point, direction = UP_LEFT, distance = 26)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(69, -71))
        assertThat(segment.otherEndpoint(Point(69, -71))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_whenDiagonal_directionDownLeft() {
        val point = Point(-22, -46)
        val segment = Segment.from(point, direction = DOWN_LEFT, distance = 23)
        assertThat(segment.otherEndpoint(point)).isEqualTo(Point(-45, -69))
        assertThat(segment.otherEndpoint(Point(-45, -69))).isEqualTo(point)
    }

    @Test
    fun otherEndpoint_invalidPoint() {
        val point = Point(-33, 25)
        val segment = Segment.from(point, direction = RIGHT, distance = 26)
        assertThrows<IllegalArgumentException> { segment.otherEndpoint(Point(-32, 25)) }
    }

    @Test
    fun points_whenPoint() {
        val point = Point(-41, -24)
        val segment = Segment.ofPoint(point)
        assertThat(segment.points().asIterable()).containsExactly(point)
    }

    @Test
    fun points_whenHorizontal() {
        val point = Point(37, -32)
        val segment = Segment.from(point, direction = RIGHT, distance = 4)
        assertThat(segment.points().asIterable()).containsExactlyInAnyOrder(
            point,
            Point(38, -32),
            Point(39, -32),
            Point(40, -32),
            Point(41, -32)
        )
    }

    @Test
    fun points_whenVertical() {
        val point = Point(37, -32)
        val segment = Segment.from(point, direction = UP, distance = 4)
        assertThat(segment.points().asIterable()).containsExactlyInAnyOrder(
            point,
            Point(37, -31),
            Point(37, -30),
            Point(37, -29),
            Point(37, -28)
        )
    }

    @Test
    fun points_whenUpRightDiagonal() {
        val point = Point(37, -32)
        val segment = Segment.from(point, direction = UP_RIGHT, distance = 4)
        assertThat(segment.points().asIterable()).containsExactlyInAnyOrder(
            point,
            Point(38, -31),
            Point(39, -30),
            Point(40, -29),
            Point(41, -28)
        )
    }

    @Test
    fun points_whenDownRightDiagonal() {
        val point = Point(37, -32)
        val segment = Segment.from(point, direction = DOWN_RIGHT, distance = 4)
        assertThat(segment.points().asIterable()).containsExactlyInAnyOrder(
            point,
            Point(38, -33),
            Point(39, -34),
            Point(40, -35),
            Point(41, -36)
        )
    }

    @Test
    fun parallelTo_bothPoint_samePoint() {
        val point = Point(40, 41)
        val segment = Segment.ofPoint(point)
        val other = Segment.ofPoint(point)
        assertThat(segment parallelTo other).isFalse
    }

    @Test
    fun parallelTo_bothPoint_distinctPoints() {
        val segment = Segment.ofPoint(Point(-86, 87))
        val other = Segment.ofPoint(Point(-22, 68))
        assertThat(segment parallelTo other).isFalse
    }

    @Test
    fun parallelTo_thisPoint() {
        val segment = Segment.ofPoint(Point(-64, -62))
        val other = Segment.from(Point(-64, 57), direction = RIGHT, distance = 1)
        assertThat(segment parallelTo other).isFalse
    }

    @Test
    fun parallelTo_otherPoint() {
        val segment = Segment.from(Point(-22, 35), direction = UP, distance = 4)
        val other = Segment.ofPoint(Point(-72, 35))
        assertThat(segment parallelTo other).isFalse
    }

    @Test
    fun parallelTo_sameSlope() {
        val segment = Segment.from(Point(65, -17), direction = DOWN_RIGHT, distance = 22)
        val other = Segment.from(Point(72, 58), direction = UP_LEFT, distance = 82)
        assertThat(segment parallelTo other).isTrue
    }

    @Test
    fun parallelTo_differentSlope() {
        val segment = Segment.from(Point(-78, -51), direction = UP, distance = 4)
        val other = Segment.from(Point(52, 47), direction = UP_RIGHT, distance = 12)
        assertThat(segment parallelTo other).isFalse
    }

    @Test
    fun perpendicularTo_bothPoint_samePoint() {
        val point = Point(40, 41)
        val segment = Segment.ofPoint(point)
        val other = Segment.ofPoint(point)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_bothPoint_distinctPoints() {
        val segment = Segment.ofPoint(Point(-86, 87))
        val other = Segment.ofPoint(Point(-22, 68))
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisPoint() {
        val segment = Segment.ofPoint(Point(-64, -62))
        val other = Segment.from(Point(-64, 57), direction = RIGHT, distance = 1)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_otherPoint() {
        val segment = Segment.from(Point(-22, 35), direction = UP, distance = 4)
        val other = Segment.ofPoint(Point(-72, 35))
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisHorizontal_otherHorizontal() {
        val segment = Segment.from(Point(-18, 16), direction = RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisHorizontal_otherVertical() {
        val segment = Segment.from(Point(-18, 16), direction = RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN, distance = 6)
        assertThat(segment perpendicularTo other).isTrue
    }

    @Test
    fun perpendicularTo_thisHorizontal_otherUpRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisHorizontal_otherDownRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = UP_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisVertical_otherHorizontal() {
        val segment = Segment.from(Point(-18, 16), direction = UP, distance = 22)
        val other = Segment.from(Point(10, 53), direction = LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isTrue
    }

    @Test
    fun perpendicularTo_thisVertical_otherVertical() {
        val segment = Segment.from(Point(-18, 16), direction = UP, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisVertical_otherUpRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = UP, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisVertical_otherDownRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = UP, distance = 22)
        val other = Segment.from(Point(10, 53), direction = UP_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisUpRightDiagonal_otherHorizontal() {
        val segment = Segment.from(Point(-18, 16), direction = UP_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisUpRightDiagonal_otherVertical() {
        val segment = Segment.from(Point(-18, 16), direction = UP_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisUpRightDiagonal_otherUpRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = UP_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisUpRightDiagonal_otherDownRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = UP_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = UP_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isTrue
    }

    @Test
    fun perpendicularTo_thisDownRightDiagonal_otherHorizontal() {
        val segment = Segment.from(Point(-18, 16), direction = DOWN_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisDownRightDiagonal_otherVertical() {
        val segment = Segment.from(Point(-18, 16), direction = DOWN_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun perpendicularTo_thisDownRightDiagonal_otherUpRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = DOWN_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = DOWN_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isTrue
    }

    @Test
    fun perpendicularTo_thisDownRightDiagonal_otherDownRightDiagonal() {
        val segment = Segment.from(Point(-18, 16), direction = DOWN_RIGHT, distance = 22)
        val other = Segment.from(Point(10, 53), direction = UP_LEFT, distance = 6)
        assertThat(segment perpendicularTo other).isFalse
    }

    @Test
    fun intersect_bothPoint_samePoint() {
        val point = Point(40, 41)
        val segment = Segment.ofPoint(point)
        val other = Segment.ofPoint(point)
        assertThat(segment intersect other).isEqualTo(point)
    }

    @Test
    fun intersect_bothPoint_distinctPoints() {
        val segment = Segment.ofPoint(Point(-86, 87))
        val other = Segment.ofPoint(Point(-22, 68))
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisPoint_overlapping() {
        val point = Point(-64, -62)
        val segment = Segment.ofPoint(point)
        val other = Segment.from(Point(-66, -62), direction = RIGHT, distance = 5)
        assertThat(segment intersect other).isEqualTo(point)
    }

    @Test
    fun intersect_thisPoint_nonOverlapping() {
        val segment = Segment.ofPoint(Point(-64, -62))
        val other = Segment.from(Point(-64, 57), direction = RIGHT, distance = 1)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_otherPoint_overlapping() {
        val point = Point(-64, -62)
        val segment = Segment.from(Point(-65, -63), direction = UP_RIGHT, distance = 12)
        val other = Segment.ofPoint(point)
        assertThat(segment intersect other).isEqualTo(point)
    }

    @Test
    fun intersect_otherPoint_nonOverlapping() {
        val segment = Segment.from(Point(-65, -62), direction = UP_RIGHT, distance = 40)
        val other = Segment.ofPoint(Point(-64, -62))
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisHorizontal_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(32, 6), direction = RIGHT, distance = 45)
        val other = Segment.from(Point(77, 6), direction = RIGHT, distance = 21)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisHorizontal_otherHorizontal_nonOverlapping() {
        val segment = Segment.from(Point(32, 6), direction = RIGHT, distance = 45)
        val other = Segment.from(Point(78, 6), direction = RIGHT, distance = 21)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisHorizontal_otherVertical_overlapping() {
        val segment = Segment.from(Point(58, -69), direction = RIGHT, distance = 38)
        val other = Segment.from(Point(72, -78), direction = UP, distance = 37)
        assertThat(segment intersect other).isEqualTo(Point(72, -69))
    }

    @Test
    fun intersect_thisHorizontal_otherVertical_nonOverlapping() {
        val segment = Segment.from(Point(-21, 39), direction = RIGHT, distance = 68)
        val other = Segment.from(Point(-6, 21), direction = UP, distance = 17)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisHorizontal_otherUpRightDiagonal_overlapping() {
        val segment = Segment.from(Point(-14, -3), direction = RIGHT, distance = 44)
        val other = Segment.from(Point(4, -19), direction = UP_RIGHT, distance = 58)
        assertThat(segment intersect other).isEqualTo(Point(20, -3))
    }

    @Test
    fun intersect_thisHorizontal_otherUpRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(-71, 62), direction = RIGHT, distance = 9)
        val other = Segment.from(Point(-38, 55), direction = UP_RIGHT, distance = 45)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisHorizontal_otherDownRightDiagonal_overlapping() {
        val segment = Segment.from(Point(-62, -84), direction = RIGHT, distance = 57)
        val other = Segment.from(Point(-73, -56), direction = DOWN_RIGHT, distance = 82)
        assertThat(segment intersect other).isEqualTo(Point(-45, -84))
    }

    @Test
    fun intersect_thisHorizontal_otherDownRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(65, -18), direction = RIGHT, distance = 40)
        val other = Segment.from(Point(52, -3), direction = DOWN_RIGHT, distance = 14)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisVertical_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(89, -30), direction = UP, distance = 45)
        val other = Segment.from(Point(74, 12), direction = RIGHT, distance = 21)
        assertThat(segment intersect other).isEqualTo(Point(89, 12))
    }

    @Test
    fun intersect_thisVertical_otherHorizontal_nonOverlapping() {
        val segment = Segment.from(Point(43, -63), direction = UP, distance = 54)
        val other = Segment.from(Point(-59, -9), direction = RIGHT, distance = 77)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisVertical_otherVertical_overlapping() {
        val segment = Segment.from(Point(-78, 35), direction = UP, distance = 18)
        val other = Segment.from(Point(-78, -56), direction = UP, distance = 91)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisVertical_otherVertical_nonOverlapping() {
        val segment = Segment.from(Point(-52, 15), direction = UP, distance = 17)
        val other = Segment.from(Point(-52, 34), direction = UP, distance = 5)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisVertical_otherUpRightDiagonal_overlapping() {
        val segment = Segment.from(Point(59, 4), direction = UP, distance = 60)
        val other = Segment.from(Point(46, -7), direction = UP_RIGHT, distance = 68)
        assertThat(segment intersect other).isEqualTo(Point(59, 6))
    }

    @Test
    fun intersect_thisVertical_otherUpRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(81, 34), direction = UP, distance = 29)
        val other = Segment.from(Point(53, -3), direction = UP_RIGHT, distance = 78)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisVertical_otherDownRightDiagonal_overlapping() {
        val segment = Segment.from(Point(52, -40), direction = UP, distance = 87)
        val other = Segment.from(Point(46, 53), direction = DOWN_RIGHT, distance = 48)
        assertThat(segment intersect other).isEqualTo(Point(52, 47))
    }

    @Test
    fun intersect_thisVertical_otherDownRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(-53, -43), direction = UP, distance = 11)
        val other = Segment.from(Point(-47, -20), direction = DOWN_RIGHT, distance = 13)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(56, -32), direction = UP_RIGHT, distance = 87)
        val other = Segment.from(Point(58, 33), direction = RIGHT, distance = 81)
        assertThat(segment intersect other).isEqualTo(Point(121, 33))
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherHorizontal_nonOverlapping() {
        val segment = Segment.from(Point(-28, 25), direction = UP_RIGHT, distance = 60)
        val other = Segment.from(Point(50, 85), direction = RIGHT, distance = 9)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherVertical_overlapping() {
        val segment = Segment.from(Point(-94, -77), direction = UP_RIGHT, distance = 96)
        val other = Segment.from(Point(-61, -44), direction = UP, distance = 68)
        assertThat(segment intersect other).isEqualTo(Point(-61, -44))
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherVertical_nonOverlapping() {
        val segment = Segment.from(Point(2, -12), direction = UP_RIGHT, distance = 93)
        val other = Segment.from(Point(46, -30), direction = UP, distance = 50)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherUpRightDiagonal_overlapping() {
        val segment = Segment.from(Point(14, 80), direction = UP_RIGHT, distance = 15)
        val other = Segment.from(Point(-75, -9), direction = UP_RIGHT, distance = 94)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherUpRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(38, 53), direction = UP_RIGHT, distance = 51)
        val other = Segment.from(Point(17, -55), direction = UP_RIGHT, distance = 42)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherDownRightDiagonal_overlappingOnGrid() {
        val segment = Segment.from(Point(-84, 27), direction = UP_RIGHT, distance = 75)
        val other = Segment.from(Point(-46, 71), direction = DOWN_RIGHT, distance = 16)
        assertThat(segment intersect other).isEqualTo(Point(-43, 68))
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherDownRightDiagonal_overlappingOffGrid() {
        val segment = Segment.from(Point(-84, 27), direction = UP_RIGHT, distance = 75)
        val other = Segment.from(Point(-47, 71), direction = DOWN_RIGHT, distance = 16)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisUpRightDiagonal_otherDownRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(-90, -15), direction = UP_RIGHT, distance = 70)
        val other = Segment.from(Point(-39, 14), direction = DOWN_RIGHT, distance = 93)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(-76, 67), direction = DOWN_RIGHT, distance = 79)
        val other = Segment.from(Point(-56, 21), direction = RIGHT, distance = 35)
        assertThat(segment intersect other).isEqualTo(Point(-30, 21))
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherHorizontal_nonOverlapping() {
        val segment = Segment.from(Point(-55, 15), direction = DOWN_RIGHT, distance = 65)
        val other = Segment.from(Point(36, -28), direction = RIGHT, distance = 22)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherVertical_overlapping() {
        val segment = Segment.from(Point(35, -65), direction = DOWN_RIGHT, distance = 25)
        val other = Segment.from(Point(57, -96), direction = UP, distance = 54)
        assertThat(segment intersect other).isEqualTo(Point(57, -87))
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherVertical_nonOverlapping() {
        val segment = Segment.from(Point(7, 33), direction = DOWN_RIGHT, distance = 68)
        val other = Segment.from(Point(6, 1), direction = UP, distance = 31)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherUpRightDiagonal_overlappingOnGrid() {
        val segment = Segment.from(Point(-21, 30), direction = DOWN_RIGHT, distance = 84)
        val other = Segment.from(Point(-98, -61), direction = UP_RIGHT, distance = 90)
        assertThat(segment intersect other).isEqualTo(Point(-14, 23))
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherUpRightDiagonal_overlappingOffGrid() {
        val segment = Segment.from(Point(-21, 30), direction = DOWN_RIGHT, distance = 84)
        val other = Segment.from(Point(-99, -61), direction = UP_RIGHT, distance = 90)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherUpRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(16, -16), direction = DOWN_RIGHT, distance = 44)
        val other = Segment.from(Point(-42, -48), direction = UP_RIGHT, distance = 53)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherDownRightDiagonal_overlapping() {
        val segment = Segment.from(Point(15, 76), direction = DOWN_RIGHT, distance = 43)
        val other = Segment.from(Point(1, 90), direction = DOWN_RIGHT, distance = 22)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun intersect_thisDownRightDiagonal_otherDownRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(19, 47), direction = DOWN_RIGHT, distance = 28)
        val other = Segment.from(Point(-92, 50), direction = DOWN_RIGHT, distance = 88)
        assertThat(segment intersect other).isNull()
    }

    @Test
    fun overlap_bothPoint_samePoint() {
        val point = Point(40, 41)
        val segment = Segment.ofPoint(point)
        val other = Segment.ofPoint(point)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(point))
    }

    @Test
    fun overlap_bothPoint_distinctPoints() {
        val segment = Segment.ofPoint(Point(-86, 87))
        val other = Segment.ofPoint(Point(-22, 68))
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisPoint_overlapping() {
        val point = Point(-64, -62)
        val segment = Segment.ofPoint(point)
        val other = Segment.from(Point(-66, -62), direction = RIGHT, distance = 5)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(point))
    }

    @Test
    fun overlap_thisPoint_nonOverlapping() {
        val segment = Segment.ofPoint(Point(-64, -62))
        val other = Segment.from(Point(-64, 57), direction = RIGHT, distance = 1)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_otherPoint_overlapping() {
        val point = Point(-64, -62)
        val segment = Segment.from(Point(-65, -63), direction = UP_RIGHT, distance = 12)
        val other = Segment.ofPoint(point)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(point))
    }

    @Test
    fun overlap_otherPoint_nonOverlapping() {
        val segment = Segment.from(Point(-65, -62), direction = UP_RIGHT, distance = 40)
        val other = Segment.ofPoint(Point(-64, -62))
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisHorizontal_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(32, 6), direction = RIGHT, distance = 69)
        val other = Segment.from(Point(77, 6), direction = RIGHT, distance = 21)
        assertThat(segment overlap other)
            .isEqualTo(Segment.from(Point(77, 6), direction = RIGHT, distance = 21))
    }

    @Test
    fun overlap_thisHorizontal_otherHorizontal_nonOverlapping_xOverlap() {
        val segment = Segment.from(Point(30, -68), direction = RIGHT, distance = 61)
        val other = Segment.from(Point(31, 38), direction = RIGHT, distance = 78)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisHorizontal_otherHorizontal_nonOverlapping_yOverlap() {
        val segment = Segment.from(Point(32, 6), direction = RIGHT, distance = 45)
        val other = Segment.from(Point(78, 6), direction = RIGHT, distance = 21)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisHorizontal_otherVertical_overlapping() {
        val segment = Segment.from(Point(58, -69), direction = RIGHT, distance = 38)
        val other = Segment.from(Point(72, -78), direction = UP, distance = 37)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(72, -69)))
    }

    @Test
    fun overlap_thisHorizontal_otherVertical_nonOverlapping() {
        val segment = Segment.from(Point(-21, 39), direction = RIGHT, distance = 68)
        val other = Segment.from(Point(-6, 21), direction = UP, distance = 17)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisHorizontal_otherUpRightDiagonal_overlapping() {
        val segment = Segment.from(Point(-14, -3), direction = RIGHT, distance = 44)
        val other = Segment.from(Point(4, -19), direction = UP_RIGHT, distance = 58)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(20, -3)))
    }

    @Test
    fun overlap_thisHorizontal_otherUpRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(-71, 62), direction = RIGHT, distance = 9)
        val other = Segment.from(Point(-38, 55), direction = UP_RIGHT, distance = 45)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisHorizontal_otherDownRightDiagonal_overlapping() {
        val segment = Segment.from(Point(-62, -84), direction = RIGHT, distance = 57)
        val other = Segment.from(Point(-73, -56), direction = DOWN_RIGHT, distance = 82)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(-45, -84)))
    }

    @Test
    fun overlap_thisHorizontal_otherDownRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(65, -18), direction = RIGHT, distance = 40)
        val other = Segment.from(Point(52, -3), direction = DOWN_RIGHT, distance = 14)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisVertical_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(89, -30), direction = UP, distance = 45)
        val other = Segment.from(Point(74, 12), direction = RIGHT, distance = 21)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(89, 12)))
    }

    @Test
    fun overlap_thisVertical_otherHorizontal_nonOverlapping() {
        val segment = Segment.from(Point(43, -63), direction = UP, distance = 54)
        val other = Segment.from(Point(-59, -9), direction = RIGHT, distance = 77)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisVertical_otherVertical_overlapping() {
        val segment = Segment.from(Point(-78, 35), direction = UP, distance = 18)
        val other = Segment.from(Point(-78, -56), direction = UP, distance = 93)
        assertThat(segment overlap other)
            .isEqualTo(Segment.from(Point(-78, 35), direction = UP, distance = 2))
    }

    @Test
    fun overlap_thisVertical_otherVertical_nonOverlapping_xOverlap() {
        val segment = Segment.from(Point(-52, 15), direction = UP, distance = 17)
        val other = Segment.from(Point(-52, 34), direction = UP, distance = 5)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisVertical_otherVertical_nonOverlapping_yOverlap() {
        val segment = Segment.from(Point(-78, 66), direction = UP, distance = 19)
        val other = Segment.from(Point(-53, 45), direction = UP, distance = 80)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisVertical_otherUpRightDiagonal_overlapping() {
        val segment = Segment.from(Point(59, 4), direction = UP, distance = 60)
        val other = Segment.from(Point(46, -7), direction = UP_RIGHT, distance = 68)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(59, 6)))
    }

    @Test
    fun overlap_thisVertical_otherUpRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(81, 34), direction = UP, distance = 29)
        val other = Segment.from(Point(53, -3), direction = UP_RIGHT, distance = 78)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisVertical_otherDownRightDiagonal_overlapping() {
        val segment = Segment.from(Point(52, -40), direction = UP, distance = 87)
        val other = Segment.from(Point(46, 53), direction = DOWN_RIGHT, distance = 48)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(52, 47)))
    }

    @Test
    fun overlap_thisVertical_otherDownRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(-53, -43), direction = UP, distance = 11)
        val other = Segment.from(Point(-47, -20), direction = DOWN_RIGHT, distance = 13)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(56, -32), direction = UP_RIGHT, distance = 87)
        val other = Segment.from(Point(58, 33), direction = RIGHT, distance = 81)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(121, 33)))
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherHorizontal_nonOverlapping() {
        val segment = Segment.from(Point(-28, 25), direction = UP_RIGHT, distance = 60)
        val other = Segment.from(Point(50, 85), direction = RIGHT, distance = 9)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherVertical_overlapping() {
        val segment = Segment.from(Point(-94, -77), direction = UP_RIGHT, distance = 96)
        val other = Segment.from(Point(-61, -44), direction = UP, distance = 68)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(-61, -44)))
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherVertical_nonOverlapping() {
        val segment = Segment.from(Point(2, -12), direction = UP_RIGHT, distance = 93)
        val other = Segment.from(Point(46, -30), direction = UP, distance = 50)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherUpRightDiagonal_overlapping() {
        val segment = Segment.from(Point(14, 80), direction = UP_RIGHT, distance = 15)
        val other = Segment.from(Point(-75, -9), direction = UP_RIGHT, distance = 94)
        assertThat(segment overlap other)
            .isEqualTo(Segment.from(Point(14, 80), direction = UP_RIGHT, distance = 5))
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherUpRightDiagonal_nonOverlapping_differentYIntercept() {
        val segment = Segment.from(Point(38, 53), direction = UP_RIGHT, distance = 51)
        val other = Segment.from(Point(17, -55), direction = UP_RIGHT, distance = 42)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherUpRightDiagonal_nonOverlapping_sameYIntercept() {
        val segment = Segment.from(Point(77, 42), direction = UP_RIGHT, distance = 27)
        val other = Segment.from(Point(32, -3), direction = UP_RIGHT, distance = 43)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherDownRightDiagonal_overlappingOnGrid() {
        val segment = Segment.from(Point(-84, 27), direction = UP_RIGHT, distance = 75)
        val other = Segment.from(Point(-46, 71), direction = DOWN_RIGHT, distance = 16)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(-43, 68)))
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherDownRightDiagonal_overlappingOffGrid() {
        val segment = Segment.from(Point(-84, 27), direction = UP_RIGHT, distance = 75)
        val other = Segment.from(Point(-47, 71), direction = DOWN_RIGHT, distance = 16)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisUpRightDiagonal_otherDownRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(-90, -15), direction = UP_RIGHT, distance = 70)
        val other = Segment.from(Point(-39, 14), direction = DOWN_RIGHT, distance = 93)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherHorizontal_overlapping() {
        val segment = Segment.from(Point(-76, 67), direction = DOWN_RIGHT, distance = 79)
        val other = Segment.from(Point(-56, 21), direction = RIGHT, distance = 35)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(-30, 21)))
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherHorizontal_nonOverlapping() {
        val segment = Segment.from(Point(-55, 15), direction = DOWN_RIGHT, distance = 65)
        val other = Segment.from(Point(36, -28), direction = RIGHT, distance = 22)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherVertical_overlapping() {
        val segment = Segment.from(Point(35, -65), direction = DOWN_RIGHT, distance = 25)
        val other = Segment.from(Point(57, -96), direction = UP, distance = 54)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(57, -87)))
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherVertical_nonOverlapping() {
        val segment = Segment.from(Point(7, 33), direction = DOWN_RIGHT, distance = 68)
        val other = Segment.from(Point(6, 1), direction = UP, distance = 31)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherUpRightDiagonal_overlappingOnGrid() {
        val segment = Segment.from(Point(-21, 30), direction = DOWN_RIGHT, distance = 84)
        val other = Segment.from(Point(-98, -61), direction = UP_RIGHT, distance = 90)
        assertThat(segment overlap other).isEqualTo(Segment.ofPoint(Point(-14, 23)))
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherUpRightDiagonal_overlappingOffGrid() {
        val segment = Segment.from(Point(-21, 30), direction = DOWN_RIGHT, distance = 84)
        val other = Segment.from(Point(-99, -61), direction = UP_RIGHT, distance = 90)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherUpRightDiagonal_nonOverlapping() {
        val segment = Segment.from(Point(16, -16), direction = DOWN_RIGHT, distance = 44)
        val other = Segment.from(Point(-42, -48), direction = UP_RIGHT, distance = 53)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherDownRightDiagonal_overlapping() {
        val segment = Segment.from(Point(15, 76), direction = DOWN_RIGHT, distance = 22)
        val other = Segment.from(Point(1, 90), direction = DOWN_RIGHT, distance = 43)
        assertThat(segment overlap other)
            .isEqualTo(Segment.from(Point(15, 76), direction = DOWN_RIGHT, distance = 22))
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherDownRightDiagonal_nonOverlapping_differentYIntercept() {
        val segment = Segment.from(Point(19, 47), direction = DOWN_RIGHT, distance = 28)
        val other = Segment.from(Point(-92, 50), direction = DOWN_RIGHT, distance = 88)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun overlap_thisDownRightDiagonal_otherDownRightDiagonal_nonOverlapping_sameYIntercept() {
        val segment = Segment.from(Point(-47, 96), direction = DOWN_RIGHT, distance = 27)
        val other = Segment.from(Point(-12, 61), direction = DOWN_RIGHT, distance = 65)
        assertThat(segment overlap other).isNull()
    }

    @Test
    fun toString_whenPoint() {
        val point = Point(36, 27)
        val segment = Segment.ofPoint(point)
        assertThat(segment.toString()).isEqualTo("(36, 27) -> (36, 27)")
    }

    @Test
    fun toString_withDistinctEndpoints() {
        val point = Point(-77, -81)
        val segment = Segment.from(point, direction = UP_LEFT, distance = 15)
        assertThat(segment.toString()).isEqualTo("(-92, -66) -> (-77, -81)")
    }

    @Test
    fun equals_nullSegment() {
        val segment = Segment.ofPoint(Point.ORIGIN)
        val other: Segment? = null
        assertThat(segment).isNotEqualTo(other)
    }

    @Test
    fun equals_differentPoint() {
        val segment = Segment.from(Point(-47, -5), direction = UP, distance = 10)
        val other = Segment.from(Point(-47, -4), direction = UP, distance = 10)
        assertThat(segment).isNotEqualTo(other)
    }

    @Test
    fun equals_oppositeDirection() {
        val segment = Segment.from(Point(54, 85), direction = RIGHT, distance = 77)
        val other = Segment.from(Point(54, 85), direction = LEFT, distance = 77)
        assertThat(segment).isNotEqualTo(other)
    }

    @Test
    fun equals_differentSlope() {
        val segment = Segment.from(Point(-64, 61), direction = UP, distance = 46)
        val other = Segment.from(Point(-64, 61), direction = RIGHT, distance = 46)
        assertThat(segment).isNotEqualTo(other)
    }

    @Test
    fun equals_differentDistance() {
        val segment = Segment.from(Point(26, -44), direction = UP, distance = 57)
        val other = Segment.from(Point(26, -44), direction = UP, distance = 50)
        assertThat(segment).isNotEqualTo(other)
    }

    @Test
    fun equals_sameEndpoints_sameOrder() {
        val segment = Segment.from(Point(76, 26), direction = UP_RIGHT, distance = 87)
        val other = Segment.from(Point(76, 26), direction = UP_RIGHT, distance = 87)
        assertThat(segment).isEqualTo(other)
    }

    @Test
    fun equals_sameEndpoints_oppositeOrder() {
        val segment = Segment.from(Point(-83, 95), direction = RIGHT, distance = 6)
        val other = Segment.from(Point(-77, 95), direction = LEFT, distance = 6)
        assertThat(segment).isEqualTo(other)
    }

    @Test
    fun hashCode_whenPoint() {
        val hashMap = hashMapOf(Segment.ofPoint(Point(16, -99)) to "foo")
        assertThat(hashMap[Segment.ofPoint(Point(16, -99))]).isEqualTo("foo")
        assertThat(hashMap[Segment.ofPoint(Point.ORIGIN)]).isNull()
        assertThat(hashMap[Segment.ofPoint(Point(-16, 99))]).isNull()
        assertThat(hashMap[Segment.ofPoint(Point(-99, 16))]).isNull()
    }

    @Test
    fun hashCode_withDistinctEndpoints() {
        val hashMap = hashMapOf(Segment.from(Point(94, -2), direction = UP, distance = 32) to "bar")
        assertThat(hashMap[Segment.from(Point(94, -2), direction = UP, distance = 32)])
            .isEqualTo("bar")
        assertThat(hashMap[Segment.from(Point(94, 30), direction = DOWN, distance = 32)])
            .isEqualTo("bar")
        assertThat(hashMap[Segment.from(Point(-2, 94), direction = UP, distance = 32)]).isNull()
        assertThat(hashMap[Segment.from(Point(94, -2), direction = RIGHT, distance = 32)]).isNull()
        assertThat(hashMap[Segment.from(Point(94, -2), direction = UP, distance = 30)]).isNull()
    }

    @Test
    fun between_samePoint() {
        val point = Point(-32, -49)
        val segment = Segment.between(point, point)
        assertThat(segment).isEqualTo(Segment.ofPoint(point))
    }

    @Test
    fun between_aLeftOfB() {
        val pointA = Point(-69, -86)
        val pointB = Point(4, -86)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointA, direction = RIGHT, distance = 73))
    }

    @Test
    fun between_aRightOfB() {
        val pointA = Point(83, 24)
        val pointB = Point(72, 24)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointB, direction = RIGHT, distance = 11))
    }

    @Test
    fun between_aBelowB() {
        val pointA = Point(29, 25)
        val pointB = Point(29, 79)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointA, direction = UP, distance = 54))
    }

    @Test
    fun between_aAboveB() {
        val pointA = Point(25, -6)
        val pointB = Point(25, -53)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointB, direction = UP, distance = 47))
    }

    @Test
    fun between_aBelowLeftOfB() {
        val pointA = Point(-75, 73)
        val pointB = Point(-55, 93)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointA, direction = UP_RIGHT, distance = 20))
    }

    @Test
    fun between_aAboveLeftOfB() {
        val pointA = Point(68, -5)
        val pointB = Point(116, -53)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointA, direction = DOWN_RIGHT, distance = 48))
    }

    @Test
    fun between_aBelowRightOfB() {
        val pointA = Point(95, -97)
        val pointB = Point(69, -71)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointB, direction = DOWN_RIGHT, distance = 26))
    }

    @Test
    fun between_aAboveRightOfB() {
        val pointA = Point(-22, -46)
        val pointB = Point(-45, -69)
        val segment = Segment.between(pointA, pointB)
        assertThat(segment).isEqualTo(Segment.from(pointB, direction = UP_RIGHT, distance = 23))
    }

    @Test
    fun between_invalidSlope() {
        val pointA = Point(-6, 63)
        val pointB = Point(76, -87)
        assertThrows<IllegalArgumentException> { Segment.between(pointA, pointB) }
    }

    @Test
    fun from_negativeDistance() {
        val point = Point(49, 87)
        val segment = Segment.from(point, direction = RIGHT, distance = -17)
        assertThat(segment).isEqualTo(Segment.from(Point(32, 87), direction = RIGHT, distance = 17))
    }

    @Test
    fun from_zeroDistance() {
        val point = Point(36, -55)
        val segment = Segment.from(point, direction = UP, distance = 0)
        assertThat(segment).isEqualTo(Segment.ofPoint(point))
    }

    @Test
    fun slopeFromDirection_invalidDirection() {
        val direction = DOWN
        assertThrows<IllegalArgumentException> { Slope.fromDirection(direction) }
    }
}
