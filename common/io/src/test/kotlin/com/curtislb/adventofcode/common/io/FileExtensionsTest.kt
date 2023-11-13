package com.curtislb.adventofcode.common.io

import com.curtislb.adventofcode.common.testing.createTempFile
import java.io.File
import java.nio.file.Path
import kotlin.test.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [File]-related extension functions.
 */
class FileExtensionsTest {
    @Test
    fun forEachChar_whenEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val chars = mutableListOf<Char>()
        file.forEachChar { chars.add(it) }
        assertThat(chars).isEmpty()
    }

    @Test
    fun forEachChar_withSingleChar(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("a")
        val chars = mutableListOf<Char>()
        file.forEachChar { chars.add(it) }
        assertThat(chars).containsExactly('a')
    }

    @Test
    fun forEachChar_withSingleLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Hello!")
        val chars = mutableListOf<Char>()
        file.forEachChar { chars.add(it) }
        assertEquals(listOf('H', 'e', 'l', 'l', 'o', '!'), chars.toList())
    }

    @Test
    fun forEachChar_withMultipleLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("foo\n Bar \n")
        val chars = mutableListOf<Char>()
        file.forEachChar { chars.add(it) }
        assertThat(chars).containsExactly('f', 'o', 'o', '\n', ' ', 'B', 'a', 'r', ' ', '\n')
    }

    @Test
    fun forEachLineIndexed_whenEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val pairs = mutableListOf<Pair<Int, String>>()
        file.forEachLineIndexed { index, line -> pairs.add(index to line) }
        assertThat(pairs).isEmpty()
    }

    @Test
    fun forEachLineIndexed_withSingleChar(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("a")
        val pairs = mutableListOf<Pair<Int, String>>()
        file.forEachLineIndexed { index, line -> pairs.add(index to line) }
        assertThat(pairs).containsExactly(0 to "a")
    }

    @Test
    fun forEachLineIndexed_withSingleLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Hello!")
        val pairs = mutableListOf<Pair<Int, String>>()
        file.forEachLineIndexed { index, line -> pairs.add(index to line) }
        assertThat(pairs).containsExactly(0 to "Hello!")
    }

    @Test
    fun forEachLineIndexed_withMultipleLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("foo\n Bar \n")
        val pairs = mutableListOf<Pair<Int, String>>()
        file.forEachLineIndexed { index, line -> pairs.add(index to line) }
        assertThat(pairs).containsExactly(0 to "foo", 1 to " Bar ")
    }

    @Test
    fun forEachSection_whenEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).isEmpty()
    }

    @Test
    fun forEachSection_withSingleChar(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("a")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(listOf("a"))
    }

    @Test
    fun forEachSection_withSingleLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem ipsum")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(listOf("Lorem ipsum"))
    }

    @Test
    fun forEachSection_withSingleSection_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun forEachSection_withSingleSection_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n  ")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun forEachSection_withSingleSection_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun forEachSection_withSingleSection_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun forEachSection_withMultipleSections_emptySeparator_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_emptySeparator_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.\n  ")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_emptySeparator_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_emptySeparator_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.\n\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_blankSeparator_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_blankSeparator_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.\n  ")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_blankSeparator_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_blankSeparator_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.\n\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_doubleSeparator_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_doubleSeparator_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.\n  ")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_doubleSeparator_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun forEachSection_withMultipleSections_doubleSeparator_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.\n\n")
        val sections = mutableListOf<List<String>>()
        file.forEachSection { sections.add(it) }
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun mapLines_whenEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val list = file.mapLines { it.uppercase() }
        assertThat(list).isEmpty()
    }

    @Test
    fun mapLines_withSingleChar(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("a")
        val list = file.mapLines { it.uppercase() }
        assertThat(list).containsExactly("A")
    }

    @Test
    fun mapLines_withSingleLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem ipsum")
        val list = file.mapLines { it.uppercase() }
        assertThat(list).containsExactly("LOREM IPSUM")
    }

    @Test
    fun mapLines_withMultipleLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(" dolor \n Sit amet.\n")
        val list = file.mapLines { it.reversed() }
        assertThat(list).containsExactly(" rolod ", ".tema tiS ")
    }

    @Test
    fun readSections_whenEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val sections = file.readSections()
        assertThat(sections).isEmpty()
    }

    @Test
    fun readSections_withSingleChar(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("a")
        val sections = file.readSections()
        assertThat(sections).containsExactly(listOf("a"))
    }

    @Test
    fun readSections_withSingleLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem ipsum")
        val sections = file.readSections()
        assertThat(sections).containsExactly(listOf("Lorem ipsum"))
    }

    @Test
    fun readSections_withSingleSection_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun readSections_withSingleSection_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n  ")
        val sections = file.readSections()
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun readSections_withSingleSection_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum")
        val sections = file.readSections()
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun readSections_withSingleSection_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(listOf("Lorem", " ipsum"))
    }

    @Test
    fun readSections_withMultipleSections_emptySeparator_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_emptySeparator_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.\n  ")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_emptySeparator_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_emptySeparator_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\ndolor \nsit\namet.\n\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_blankSeparator_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_blankSeparator_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.\n  ")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_blankSeparator_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_blankSeparator_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n \ndolor \nsit\namet.\n\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_doubleSeparator_lastLineEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_doubleSeparator_lastLineBlank(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.\n  ")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_doubleSeparator_noTrailingLine(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }

    @Test
    fun readSections_withMultipleSections_doubleSeparator_trailingLines(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile("Lorem\n ipsum\n\n\ndolor \nsit\namet.\n\n")
        val sections = file.readSections()
        assertThat(sections).containsExactly(
            listOf("Lorem", " ipsum"),
            listOf("dolor ", "sit", "amet.")
        )
    }
}
