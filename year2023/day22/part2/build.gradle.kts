plugins {
    val kotlinVersion: String by System.getProperties()
    val koverVersion: String by System.getProperties()

    application
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlinx.kover") version koverVersion
}

application {
    mainClass.set("com.curtislb.adventofcode.year2023.day22.part2.Year2023Day22Part2Kt")
}

dependencies {
    val assertjVersion: String by properties
    val junitJupiterVersion: String by properties
    val junitPlatformVersion: String by properties

    implementation(project(":common:collection"))
    implementation(project(":common:comparison"))
    implementation(project(":common:geometry"))
    implementation(project(":common:graph"))
    implementation(project(":common:grid"))
    implementation(project(":common:heap"))
    implementation(project(":common:io"))
    implementation(project(":common:iteration"))
    implementation(project(":common:number"))
    implementation(project(":common:parse"))
    implementation(project(":common:range"))
    implementation(project(":common:search"))
    implementation(project(":common:simulation"))
    implementation(project(":common:vector"))
    implementation(project(":year2023:day22:bricks"))
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
}
