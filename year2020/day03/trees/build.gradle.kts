plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    api(project(":common:geometry"))
    api(project(":common:number"))
    implementation(project(":common:grid"))
}
