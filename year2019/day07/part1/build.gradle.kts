plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

val year = "2019"
val day = "07"
val part = "1"

application {
    mainClass.set("com.curtislb.adventofcode.year$year.day$day.part$part.Year${year}Day${day}Part${part}Kt")
}

dependencies {
    implementation(project(":common:range"))
    implementation(project(":year2019:day07:amplifier"))
}
