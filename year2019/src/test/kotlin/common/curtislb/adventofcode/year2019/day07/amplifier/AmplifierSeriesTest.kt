package com.curtislb.adventofcode.year2019.day07.amplifier

import org.junit.jupiter.api.BeforeEach
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [AmplifierSeries].
 */
class AmplifierSeriesTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File

    @Before
    fun setUp() {
        file = temporaryFolder.newFile()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructWithZeroAmplifierCount() {
        file.writeText("99")
        AmplifierSeries(file, 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testFindMaxSignalForWrongPhaseSettingsSize() {
        file.writeText("99")
        val series = AmplifierSeries(file, 2)
        series.findMaxSignal(setOf(BigInteger.ZERO, BigInteger.ONE, BigInteger.TWO))
    }

    @Test
    fun testFindMaxSignalForOneAmplifier() {
        file.writeText("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
        val series = AmplifierSeries(file, 1)
        assertEquals(BigInteger.ONE, series.findMaxSignal(setOf(BigInteger.ONE)))
    }

    @Test
    fun testFindMaxSignalForTwoAmplifiers() {
        file.writeText("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
        val series = AmplifierSeries(file, 2)
        assertEquals(BigInteger("21"), series.findMaxSignal(setOf(BigInteger.ONE, BigInteger.TWO)))
    }

    @Test
    fun testFindMaxSignalForThreeAmplifiers() {
        file.writeText("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
        val series = AmplifierSeries(file, 3)
        assertEquals(BigInteger("1021"), series.findMaxSignal(setOf(BigInteger.ONE, BigInteger.TWO, BigInteger.TEN)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testFindMaxSignalWithFeedbackForWrongPhaseSettingsSize() {
        file.writeText("99")
        val series = AmplifierSeries(file, 3)
        series.findMaxSignalWithFeedback(setOf(BigInteger.ZERO, BigInteger.ONE))
    }

    @Test
    fun testFindMaxSignalWithFeedbackForOneAmplifier() {
        file.writeText("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5")
        val series = AmplifierSeries(file, 1)
        assertEquals(BigInteger.ZERO, series.findMaxSignalWithFeedback(setOf(BigInteger.TEN)))
    }

    @Test
    fun testFindMaxSignalWithFeedbackForTwoAmplifiers() {
        file.writeText("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5")
        val series = AmplifierSeries(file, 2)
        assertEquals(BigInteger("6820"), series.findMaxSignalWithFeedback(setOf(BigInteger.TEN, BigInteger("11"))))
    }

    @Test
    fun testFindMaxSignalWithFeedbackForThreeAmplifiers() {
        file.writeText("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5")
        val series = AmplifierSeries(file, 3)
        assertEquals(
            BigInteger("243412"),
            series.findMaxSignalWithFeedback(setOf(BigInteger.TEN, BigInteger("11"), BigInteger("12")))
        )
    }
}
