plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    api(project(":common:collection"))
    implementation(project(":common:io"))
    implementation(project(":common:number"))
}
