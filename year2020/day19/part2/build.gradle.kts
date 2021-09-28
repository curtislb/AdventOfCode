plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

val year = "2020"
val day = "19"
val part = "2"

application {
    mainClass.set("com.curtislb.adventofcode.year$year.day$day.part$part.Year${year}Day${day}Part${part}Kt")
}

dependencies {
    implementation(project(":year2020:day19:rule"))
}
