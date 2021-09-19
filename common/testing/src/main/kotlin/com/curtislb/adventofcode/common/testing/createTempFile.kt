package com.curtislb.adventofcode.common.testing

import java.io.File
import java.nio.file.Path

/**
 * TODO
 */
fun Path.createTempFile(name: String = "tmp", text: String = ""): File {
    return resolve(name).toFile().apply { writeText(text) }
}
