plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

dependencies {
    implementation(project(":common:graph"))
    implementation(project(":year2019:day15:repair"))
}
