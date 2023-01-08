plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":year2021:day19:beacon"))
}
