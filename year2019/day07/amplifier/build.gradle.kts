plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:intcode"))

    testImplementation(project(":common:testing"))
}
