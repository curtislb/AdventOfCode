plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:math"))
    implementation(project(":year2021:day09:basin"))
}
