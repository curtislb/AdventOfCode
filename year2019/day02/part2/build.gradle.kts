plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:intcode"))
    implementation(project(":common:range"))
}
