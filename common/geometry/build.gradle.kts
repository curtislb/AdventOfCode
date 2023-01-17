plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:number"))
    implementation(project(":common:range"))
    testImplementation(project(":common:testing"))
}
