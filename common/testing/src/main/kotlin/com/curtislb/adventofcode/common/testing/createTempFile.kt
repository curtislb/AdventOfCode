package com.curtislb.adventofcode.common.testing

import java.io.File
import java.nio.file.Path

/**
 * TODO
 */
fun Path.createTempFile(text: String = "", fileName: String = "tmp"): File =
    resolve(fileName).toFile().apply { writeText(text) }
