plugins {
    val kotlinVersion: String by System.getProperties()
    val koverVersion: String by System.getProperties()

    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlinx.kover") version koverVersion
}

dependencies {
    val assertjVersion: String by properties
    val hamcrestVersion: String by properties
    val kotlinVersion: String by System.getProperties()
    implementation("org.assertj:assertj-core:$assertjVersion")
    implementation("org.hamcrest:hamcrest:$hamcrestVersion")
    implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
