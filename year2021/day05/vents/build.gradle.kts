plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    api(project(":common:geometry"))
    implementation(project(":common:collection"))
    implementation(project(":common:parse"))
}
