plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:io"))
    implementation(project(":common:parse"))
    implementation(project(":year2020:day16:ticket"))
}
