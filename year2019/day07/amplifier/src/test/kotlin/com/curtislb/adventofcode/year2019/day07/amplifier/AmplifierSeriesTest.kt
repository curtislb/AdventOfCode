package com.curtislb.adventofcode.year2019.day07.amplifier

import com.curtislb.adventofcode.common.testing.createTempFile
import java.math.BigInteger
import java.nio.file.Path
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [AmplifierSeries].
 */
class AmplifierSeriesTest {
    @Test
    fun testConstructWithZeroAmplifierCount(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "99")
        assertThrows<IllegalArgumentException> { AmplifierSeries(file, 0) }
    }

    @Test
    fun testFindMaxSignalForWrongPhaseSettingsSize(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "99")
        val series = AmplifierSeries(file, 2)
        assertThrows<IllegalArgumentException> {
            series.findMaxSignal(setOf(BigInteger.ZERO, BigInteger.ONE, BigInteger.TWO))
        }
    }

    @Test
    fun testFindMaxSignalForOneAmplifier(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
        val series = AmplifierSeries(file, 1)
        assertEquals(BigInteger.ONE, series.findMaxSignal(setOf(BigInteger.ONE)))
    }

    @Test
    fun testFindMaxSignalForTwoAmplifiers(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
        val series = AmplifierSeries(file, 2)
        assertEquals(BigInteger("21"), series.findMaxSignal(setOf(BigInteger.ONE, BigInteger.TWO)))
    }

    @Test
    fun testFindMaxSignalForThreeAmplifiers(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
        val series = AmplifierSeries(file, 3)
        assertEquals(
            BigInteger("1021"),
            series.findMaxSignal(setOf(BigInteger.ONE, BigInteger.TWO, BigInteger.TEN))
        )
    }

    @Test
    fun testFindMaxSignalWithFeedbackForWrongPhaseSettingsSize(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "99")
        val series = AmplifierSeries(file, 3)
        assertThrows<IllegalArgumentException> {
            series.findMaxSignalWithFeedback(setOf(BigInteger.ZERO, BigInteger.ONE))
        }
    }

    @Test
    fun testFindMaxSignalWithFeedbackForOneAmplifier(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text =
            "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
        )
        val series = AmplifierSeries(file, 1)
        assertEquals(BigInteger.ZERO, series.findMaxSignalWithFeedback(setOf(BigInteger.TEN)))
    }

    @Test
    fun testFindMaxSignalWithFeedbackForTwoAmplifiers(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text =
            "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
        )
        val series = AmplifierSeries(file, 2)
        assertEquals(
            BigInteger("6820"),
            series.findMaxSignalWithFeedback(setOf(BigInteger.TEN, BigInteger("11")))
        )
    }

    @Test
    fun testFindMaxSignalWithFeedbackForThreeAmplifiers(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text =
            "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
        )
        val series = AmplifierSeries(file, 3)
        assertEquals(
            BigInteger("243412"),
            series.findMaxSignalWithFeedback(
                setOf(
                    BigInteger.TEN,
                    BigInteger("11"),
                    BigInteger("12")
                )
            )
        )
    }
}
