plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

dependencies {
    api(project(":common:collection"))
    implementation(project(":common:io"))

    testImplementation(project(":common:testing"))
}
