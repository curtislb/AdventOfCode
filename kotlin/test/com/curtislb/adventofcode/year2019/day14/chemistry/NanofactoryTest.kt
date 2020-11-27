package com.curtislb.adventofcode.year2019.day14.chemistry

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Tests [Nanofactory].
 */
class NanofactoryTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File

    @Before
    fun setUp() {
        file = temporaryFolder.newFile()
    }

    @Test
    fun testFindRequiredMaterials() {
        file.writeText("""
            2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG
            17 NVRVD, 3 JNWZP => 8 VPVL
            53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL
            22 VJHF, 37 MNCFX => 5 FWMGM
            139 ORE => 4 NVRVD
            144 ORE => 7 JNWZP
            5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC
            5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV
            145 ORE => 6 MNCFX
            1 NVRVD => 8 CXFTF
            1 VJHF, 6 MNCFX => 4 RFSQX
            176 ORE => 6 VJHF
        """.trimIndent())

        val nanofactory = Nanofactory(file)
        var requiredMaterials = nanofactory.findRequiredMaterials(setOf("ORE"), listOf(MaterialAmount("NVRVD", 4L)))
        assertNotNull(requiredMaterials)
        assertContainsExactly(listOf(MaterialAmount("ORE", 139L)), requiredMaterials)

        requiredMaterials = nanofactory.findRequiredMaterials(setOf("JNWZP"), listOf(MaterialAmount("VPVL", 1L)))
        assertNull(requiredMaterials)

        requiredMaterials = nanofactory.findRequiredMaterials(setOf("FAKE"), listOf(MaterialAmount("VPVL", 1L)))
        assertNull(requiredMaterials)

        requiredMaterials = nanofactory.findRequiredMaterials(emptySet(), listOf(MaterialAmount("VPVL", 1L)))
        assertNull(requiredMaterials)

        requiredMaterials = nanofactory.findRequiredMaterials(setOf("ORE"), listOf(MaterialAmount("VPVL", -1L)))
        assertNull(requiredMaterials)

        requiredMaterials = nanofactory.findRequiredMaterials(setOf("ORE", "JNWZP"), listOf(MaterialAmount("VPVL", 8L)))
        assertNotNull(requiredMaterials)
        assertContainsExactly(listOf(MaterialAmount("ORE", 695L), MaterialAmount("JNWZP", 3L)), requiredMaterials)

        requiredMaterials = nanofactory.findRequiredMaterials(
            setOf("ORE"),
            listOf(MaterialAmount("JNWZP", 8L), MaterialAmount("CXFTF", 20L))
        )
        assertNotNull(requiredMaterials)
        assertContainsExactly(listOf(MaterialAmount("ORE", 427L)), requiredMaterials)

        requiredMaterials = nanofactory.findRequiredMaterials(
            setOf("NVRVD", "JNWZP", "MNCFX", "VJHF"),
            listOf(MaterialAmount("HVMC", 6L), MaterialAmount("FUEL", 1L))
        )
        assertNotNull(requiredMaterials)
        assertContainsExactly(
            listOf(
                MaterialAmount("NVRVD", 573L),
                MaterialAmount("JNWZP", 81L),
                MaterialAmount("MNCFX", 4257L),
                MaterialAmount("VJHF", 2014L)
            ),
            requiredMaterials
        )

        requiredMaterials = nanofactory.findRequiredMaterials(setOf("ORE"), listOf(MaterialAmount("FUEL", 1L)))
        assertNotNull(requiredMaterials)
        assertContainsExactly(listOf(MaterialAmount("ORE", 180697L)), requiredMaterials)
    }

    @Test
    fun testToString() {
        val factoryString = """
            157 ORE => 5 NZVS
            165 ORE => 6 DCFZ
            44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL
            12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ
            179 ORE => 7 PSHF
            177 ORE => 5 HKGWZ
            7 DCFZ, 7 PSHF => 2 XJWVT
            165 ORE => 2 GPVTF
            3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT
        """.trimIndent()
        file.writeText(factoryString)
        val nanofactory = Nanofactory(file)
        assertContainsExactly(factoryString.split('\n'), nanofactory.toString().split('\n'))
    }
}
