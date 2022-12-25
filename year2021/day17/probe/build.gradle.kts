plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:io"))
    implementation(project(":common:math"))
    implementation(project(":common:search"))
}
