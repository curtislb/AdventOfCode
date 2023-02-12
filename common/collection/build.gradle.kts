plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    testImplementation(project(":common:testing"))
    testImplementation("org.hamcrest:hamcrest:${Versions.HAMCREST}")
}
