plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:math"))
    implementation(project(":year2021:day09:basin"))
}
