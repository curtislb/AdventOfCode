plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:geometry"))
    implementation(project(":common:grid"))
    implementation(project(":year2020:day20:image"))
}
