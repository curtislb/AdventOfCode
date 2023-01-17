plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    api(project(":common:geometry"))
    implementation(project(":common:collection"))
    implementation(project(":common:grid"))
    implementation(project(":common:graph"))
    implementation(project(":common:io"))
}
