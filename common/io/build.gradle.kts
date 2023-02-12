plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    implementation(project(":common:parse"))
    implementation(project(":common:range"))
    testImplementation(project(":common:testing"))
}
