plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

dependencies {
    implementation(project(":common:parse"))
    implementation(project(":common:range"))

    testImplementation(project(":common:testing"))
}
