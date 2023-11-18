plugins {
    val kotlinVersion: String by System.getProperties()
    val koverVersion: String by System.getProperties()

    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlinx.kover") version koverVersion
}

dependencies {
    val assertjVersion: String by properties
    val junitVersion: String by properties

    implementation(project(":common:collection"))
    implementation(project(":common:number"))
    implementation(project(":common:range"))
    testImplementation(project(":common:testing"))
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
