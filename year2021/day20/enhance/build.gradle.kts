plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    api(project(":common:geometry"))
    api(project(":common:grid"))
    implementation(project(":common:io"))
    implementation(project(":common:number"))
}