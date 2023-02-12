plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    api(project(":common:range"))
    implementation(project(":common:intcode"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}")
}
