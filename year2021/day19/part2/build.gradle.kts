plugins {
    val kotlinVersion: String by System.getProperties()
    val koverVersion: String by System.getProperties()

    application
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlinx.kover") version koverVersion
}

application {
    mainClass.set("com.curtislb.adventofcode.year2021.day19.part2.Year2021Day19Part2Kt")
}

dependencies {
    val assertjVersion: String by properties
    val junitVersion: String by properties
    val kotlinVersion: String by System.getProperties()

    implementation(project(":common:iteration"))
    implementation(project(":year2021:day19:beacon"))
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
