plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    api(project(":common:grid"))
    implementation(project(":common:io"))
    implementation(project(":common:math"))
}
