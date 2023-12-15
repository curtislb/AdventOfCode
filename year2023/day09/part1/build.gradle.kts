plugins {
    val kotlinVersion: String by System.getProperties()
    val koverVersion: String by System.getProperties()

    application
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlinx.kover") version koverVersion
}

application {
    mainClass.set("com.curtislb.adventofcode.year2023.day09.part1.Year2023Day09Part1Kt")
}

dependencies {
    val assertjVersion: String by properties
    val junitJupiterVersion: String by properties
    val junitPlatformVersion: String by properties

    implementation(project(":common:io"))
    implementation(project(":common:parse"))
    implementation(project(":year2023:day09:oasis"))
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
}
