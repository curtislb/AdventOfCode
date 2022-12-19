plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

//val exampleVersion: String by project

dependencies {
    implementation(project(":common:collection"))
}
