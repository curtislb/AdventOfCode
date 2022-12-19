plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

dependencies {
    testImplementation(project(":common:testing"))
}
