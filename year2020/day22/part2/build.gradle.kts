plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

val year = "2020"
val day = "22"
val part = "2"

application {
    mainClass.set("com.curtislb.adventofcode.year$year.day$day.part$part.Year${year}Day${day}Part${part}Kt")
}
