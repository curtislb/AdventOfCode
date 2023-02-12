plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

dependencies {
    implementation("org.hamcrest:hamcrest:${Versions.HAMCREST}")
    implementation("org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}")
}
