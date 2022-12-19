plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

dependencies {
    implementation(project(":common:grid"))
    implementation(project(":common:io"))
    implementation(project(":common:math"))
}
