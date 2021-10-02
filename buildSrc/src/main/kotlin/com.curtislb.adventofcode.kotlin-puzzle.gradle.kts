plugins {
    id("application")
    id("com.curtislb.adventofcode.kotlin-library")
}

// Extract year, day, and part strings from the project path.
val regex = Regex("""\d+""")
val path = projectDir.toPath().toAbsolutePath()
val year = regex.find(path.parent.parent.fileName.toString())?.value
val day = regex.find(path.parent.fileName.toString())?.value
val part = regex.find(path.fileName.toString())?.value

application {
    mainClass.set("com.curtislb.adventofcode.year$year.day$day.part$part.Year${year}Day${day}Part${part}Kt")
}
