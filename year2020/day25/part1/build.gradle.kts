plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

val year = "2020"
val day = "25"
val part = "1"

application {
    mainClass.set("com.curtislb.adventofcode.year$year.day$day.part$part.Year${year}Day${day}Part${part}Kt")
}

dependencies {
    implementation(project(":common:math"))
}
