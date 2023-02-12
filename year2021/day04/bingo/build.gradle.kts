plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    api(project(":common:grid"))
    implementation(project(":common:geometry"))
    implementation(project(":common:parse"))
}
