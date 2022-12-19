plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:math"))

    testImplementation(project(":common:testing"))
}
