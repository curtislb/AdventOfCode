plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    implementation(project(":common:intcode"))
    implementation(project(":common:range"))
}
