plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:graph"))
    implementation(project(":common:number"))
    implementation(project(":common:range"))
}
