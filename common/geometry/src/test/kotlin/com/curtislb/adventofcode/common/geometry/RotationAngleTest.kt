package com.curtislb.adventofcode.common.geometry

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
 * Tests the [RotationAngle] enum class.
 */
class RotationAngleTest {
    @Test
    fun plus_from0Degrees() {
        val angle = DEGREES_0
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_0)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_45)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_90)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_135)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_180)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_225)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_270)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_315)
    }

    @Test
    fun plus_from45Degrees() {
        val angle = DEGREES_45
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_45)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_90)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_135)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_180)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_225)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_270)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_315)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_0)
    }

    @Test
    fun plus_from90Degrees() {
        val angle = DEGREES_90
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_90)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_135)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_180)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_225)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_270)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_315)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_0)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_45)
    }

    @Test
    fun plus_from135Degrees() {
        val angle = DEGREES_135
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_135)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_180)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_225)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_270)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_315)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_0)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_45)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_90)
    }

    @Test
    fun plus_from180Degrees() {
        val angle = DEGREES_180
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_180)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_225)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_270)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_315)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_0)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_45)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_90)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_135)
    }

    @Test
    fun plus_from225Degrees() {
        val angle = DEGREES_225
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_225)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_270)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_315)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_0)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_45)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_90)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_135)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_180)
    }

    @Test
    fun plus_from270Degrees() {
        val angle = DEGREES_270
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_270)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_315)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_0)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_45)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_90)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_135)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_180)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_225)
    }

    @Test
    fun plus_from315Degrees() {
        val angle = DEGREES_315
        assertThat(angle + DEGREES_0).isEqualTo(DEGREES_315)
        assertThat(angle + DEGREES_45).isEqualTo(DEGREES_0)
        assertThat(angle + DEGREES_90).isEqualTo(DEGREES_45)
        assertThat(angle + DEGREES_135).isEqualTo(DEGREES_90)
        assertThat(angle + DEGREES_180).isEqualTo(DEGREES_135)
        assertThat(angle + DEGREES_225).isEqualTo(DEGREES_180)
        assertThat(angle + DEGREES_270).isEqualTo(DEGREES_225)
        assertThat(angle + DEGREES_315).isEqualTo(DEGREES_270)
    }

    @Test
    fun minus_from0Degrees() {
        val angle = DEGREES_0
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_0)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_315)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_270)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_225)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_180)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_135)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_90)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_45)
    }

    @Test
    fun minus_from45Degrees() {
        val angle = DEGREES_45
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_45)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_0)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_315)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_270)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_225)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_180)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_135)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_90)
    }

    @Test
    fun minus_from90Degrees() {
        val angle = DEGREES_90
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_90)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_45)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_0)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_315)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_270)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_225)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_180)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_135)
    }

    @Test
    fun minus_from135Degrees() {
        val angle = DEGREES_135
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_135)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_90)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_45)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_0)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_315)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_270)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_225)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_180)
    }

    @Test
    fun minus_from180Degrees() {
        val angle = DEGREES_180
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_180)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_135)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_90)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_45)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_0)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_315)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_270)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_225)
    }

    @Test
    fun minus_from225Degrees() {
        val angle = DEGREES_225
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_225)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_180)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_135)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_90)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_45)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_0)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_315)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_270)
    }

    @Test
    fun minus_from270Degrees() {
        val angle = DEGREES_270
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_270)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_225)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_180)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_135)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_90)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_45)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_0)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_315)
    }

    @Test
    fun minus_from315Degrees() {
        val angle = DEGREES_315
        assertThat(angle - DEGREES_0).isEqualTo(DEGREES_315)
        assertThat(angle - DEGREES_45).isEqualTo(DEGREES_270)
        assertThat(angle - DEGREES_90).isEqualTo(DEGREES_225)
        assertThat(angle - DEGREES_135).isEqualTo(DEGREES_180)
        assertThat(angle - DEGREES_180).isEqualTo(DEGREES_135)
        assertThat(angle - DEGREES_225).isEqualTo(DEGREES_90)
        assertThat(angle - DEGREES_270).isEqualTo(DEGREES_45)
        assertThat(angle - DEGREES_315).isEqualTo(DEGREES_0)
    }

    @Test
    fun unaryMinus_when0Degrees() {
        val angle = DEGREES_0
        assertThat(-angle).isEqualTo(DEGREES_0)
    }

    @Test
    fun unaryMinus_when45Degrees() {
        val angle = DEGREES_45
        assertThat(-angle).isEqualTo(DEGREES_315)
    }

    @Test
    fun unaryMinus_when90Degrees() {
        val angle = DEGREES_90
        assertThat(-angle).isEqualTo(DEGREES_270)
    }

    @Test
    fun unaryMinus_when135Degrees() {
        val angle = DEGREES_135
        assertThat(-angle).isEqualTo(DEGREES_225)
    }

    @Test
    fun unaryMinus_when180Degrees() {
        val angle = DEGREES_180
        assertThat(-angle).isEqualTo(DEGREES_180)
    }

    @Test
    fun unaryMinus_when225Degrees() {
        val angle = DEGREES_225
        assertThat(-angle).isEqualTo(DEGREES_135)
    }

    @Test
    fun unaryMinus_when270Degrees() {
        val angle = DEGREES_270
        assertThat(-angle).isEqualTo(DEGREES_90)
    }

    @Test
    fun unaryMinus_when315Degrees() {
        val angle = DEGREES_315
        assertThat(-angle).isEqualTo(DEGREES_45)
    }

    @Test
    fun fromDegrees_0Degrees() {
        val degrees = 0
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_0)
    }

    @Test
    fun fromDegrees_45Degrees() {
        val degrees = 45
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_45)
    }

    @Test
    fun fromDegrees_90Degrees() {
        val degrees = 90
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_90)
    }

    @Test
    fun fromDegrees_135Degrees() {
        val degrees = 135
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_135)
    }

    @Test
    fun fromDegrees_180Degrees() {
        val degrees = 180
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_180)
    }

    @Test
    fun fromDegrees_225Degrees() {
        val degrees = 225
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_225)
    }

    @Test
    fun fromDegrees_270Degrees() {
        val degrees = 270
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_270)
    }

    @Test
    fun fromDegrees_360Degrees() {
        val degrees = 360
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_0)
    }

    @Test
    fun fromDegrees_405Degrees() {
        val degrees = 405
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_45)
    }

    @Test
    fun fromDegrees_450Degrees() {
        val degrees = 450
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_90)
    }

    @Test
    fun fromDegrees_495Degrees() {
        val degrees = 495
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_135)
    }

    @Test
    fun fromDegrees_540Degrees() {
        val degrees = 540
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_180)
    }

    @Test
    fun fromDegrees_585Degrees() {
        val degrees = 585
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_225)
    }

    @Test
    fun fromDegrees_630Degrees() {
        val degrees = 630
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_270)
    }

    @Test
    fun fromDegrees_675Degrees() {
        val degrees = 675
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_315)
    }

    @Test
    fun fromDegrees_720Degrees() {
        val degrees = 720
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_0)
    }

    @Test
    fun fromDegrees_negative45Degrees() {
        val degrees = -45
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_315)
    }

    @Test
    fun fromDegrees_negative90Degrees() {
        val degrees = -90
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_270)
    }

    @Test
    fun fromDegrees_negative135Degrees() {
        val degrees = -135
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_225)
    }

    @Test
    fun fromDegrees_negative180Degrees() {
        val degrees = -180
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_180)
    }

    @Test
    fun fromDegrees_negative225Degrees() {
        val degrees = -225
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_135)
    }

    @Test
    fun fromDegrees_negative270Degrees() {
        val degrees = -270
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_90)
    }

    @Test
    fun fromDegrees_negative315Degrees() {
        val degrees = -315
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_45)
    }

    @Test
    fun fromDegrees_negative360Degrees() {
        val degrees = -360
        assertThat(RotationAngle.fromDegrees(degrees)).isEqualTo(DEGREES_0)
    }

    @Test
    fun fromDegrees_invalidDegrees() {
        val degrees = 215
        assertThrows<IllegalArgumentException> { RotationAngle.fromDegrees(degrees) }
    }

    @Test
    fun fromTurnCount_turnCountZero() {
        val turnCount = 0
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_0)
    }

    @Test
    fun fromTurnCount_turnCountOne() {
        val turnCount = 1
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_45)
    }

    @Test
    fun fromTurnCount_turnCountTwo() {
        val turnCount = 2
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_90)
    }

    @Test
    fun fromTurnCount_turnCountThree() {
        val turnCount = 3
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_135)
    }

    @Test
    fun fromTurnCount_turnCountFour() {
        val turnCount = 4
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_180)
    }

    @Test
    fun fromTurnCount_turnCountFive() {
        val turnCount = 5
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_225)
    }

    @Test
    fun fromTurnCount_turnCountSix() {
        val turnCount = 6
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_270)
    }

    @Test
    fun fromTurnCount_turnCountSeven() {
        val turnCount = 7
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_315)
    }

    @Test
    fun fromTurnCount_turnCountEight() {
        val turnCount = 8
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_0)
    }

    @Test
    fun fromTurnCount_turnCountNine() {
        val turnCount = 9
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_45)
    }

    @Test
    fun fromTurnCount_turnCountTen() {
        val turnCount = 10
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_90)
    }

    @Test
    fun fromTurnCount_turnCountEleven() {
        val turnCount = 11
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_135)
    }

    @Test
    fun fromTurnCount_turnCountTwelve() {
        val turnCount = 12
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_180)
    }

    @Test
    fun fromTurnCount_turnCountThirteen() {
        val turnCount = 13
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_225)
    }

    @Test
    fun fromTurnCount_turnCountFourteen() {
        val turnCount = 14
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_270)
    }

    @Test
    fun fromTurnCount_turnCountFifteen() {
        val turnCount = 15
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_315)
    }

    @Test
    fun fromTurnCount_turnCountSixteen() {
        val turnCount = 16
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_0)
    }

    @Test
    fun fromTurnCount_turnCountNegativeOne() {
        val turnCount = -1
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_315)
    }

    @Test
    fun fromTurnCount_turnCountNegativeTwo() {
        val turnCount = -2
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_270)
    }

    @Test
    fun fromTurnCount_turnCountNegativeThree() {
        val turnCount = -3
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_225)
    }

    @Test
    fun fromTurnCount_turnCountNegativeFour() {
        val turnCount = -4
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_180)
    }

    @Test
    fun fromTurnCount_turnCountNegativeFive() {
        val turnCount = -5
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_135)
    }

    @Test
    fun fromTurnCount_turnCountNegativeSix() {
        val turnCount = -6
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_90)
    }

    @Test
    fun fromTurnCount_turnCountNegativeSeven() {
        val turnCount = -7
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_45)
    }

    @Test
    fun fromTurnCount_turnCountNegativeEight() {
        val turnCount = -8
        assertThat(RotationAngle.fromTurnCount(turnCount)).isEqualTo(DEGREES_0)
    }
}
