plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

val year = "2019"
val day = "21"
val part = "1"

application {
    mainClass.set("com.curtislb.adventofcode.year$year.day$day.part$part.Year${year}Day${day}Part${part}Kt")
}

dependencies {
    implementation(project(":year2019:day21:spring"))
}
