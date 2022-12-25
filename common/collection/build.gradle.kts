plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
}

val hamcrestVersion: String by project

dependencies {
    testImplementation(project(":common:testing"))
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")
}
