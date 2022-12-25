plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:math"))

    testImplementation(project(":common:testing"))
}
