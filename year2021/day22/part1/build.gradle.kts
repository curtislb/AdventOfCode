plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    implementation(project(":common:geometry"))
    implementation(project(":common:range"))
    implementation(project(":year2021:day22:reactor"))
}
