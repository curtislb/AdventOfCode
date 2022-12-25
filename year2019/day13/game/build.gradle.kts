plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    api(project(":common:grid"))
    api(project(":common:intcode"))

    testImplementation(project(":common:testing"))
}
