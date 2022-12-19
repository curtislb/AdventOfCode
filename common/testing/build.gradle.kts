plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

val hamcrestVersion: String by project
val kotlinVersion: String by project

dependencies {
    implementation("org.hamcrest:hamcrest:$hamcrestVersion")
    implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
