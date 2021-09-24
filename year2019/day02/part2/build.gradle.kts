plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

val year = "2019"
val day = "02"
val part = "2"

application {
    mainClass.set("com.curtislb.adventofcode.year$year.day$day.part$part.Year${year}Day${day}Part${part}Kt")
}

dependencies {
    implementation(project(":common:intcode"))
    implementation(project(":common:range"))
}
