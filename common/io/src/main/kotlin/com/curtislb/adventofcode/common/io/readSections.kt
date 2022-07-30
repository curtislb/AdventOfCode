package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * TODO
 */
fun File.readSections(charset: Charset = Charsets.UTF_8): List<List<String>> {
    val sections = mutableListOf<List<String>>()
    forEachSection(charset) { sections.add(it) }
    return sections
}
