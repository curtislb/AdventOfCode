plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    implementation(project(":common:io"))
    implementation(project(":common:parse"))
    implementation(project(":year2021:day04:bingo"))
}
