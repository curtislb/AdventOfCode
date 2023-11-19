plugins {
    val kotlinVersion: String by System.getProperties()
    val koverVersion: String by System.getProperties()

    application
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlinx.kover") version koverVersion
}

application {
    mainClass.set("com.curtislb.adventofcode.year2021.day01.part1.Year2021Day01Part1Kt")
}

dependencies {
    val assertjVersion: String by properties
    val junitJupiterVersion: String by properties
    val junitPlatformVersion: String by properties
    val kotlinVersion: String by System.getProperties()

    implementation(project(":common:collection"))
    implementation(project(":common:parse"))
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
}
