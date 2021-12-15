plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:math"))
    implementation(project(":year2021:day09:basin"))
}
