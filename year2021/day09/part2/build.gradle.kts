plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:number"))
    implementation(project(":year2021:day09:basin"))
}
