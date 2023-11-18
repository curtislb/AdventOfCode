plugins {
    val kotlinVersion: String by System.getProperties()
    val koverVersion: String by System.getProperties()

    application
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlinx.kover") version koverVersion
}

application {
    mainClass.set("com.curtislb.adventofcode.year2021.day13.part1.Year2021Day13Part1Kt")
}

dependencies {
    val assertjVersion: String by properties
    val junitVersion: String by properties
    val kotlinVersion: String by System.getProperties()

    implementation(project(":common:io"))
    implementation(project(":year2021:day13:origami"))
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
