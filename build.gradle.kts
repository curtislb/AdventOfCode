plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

val javaVersion: String by project

koverMerged {
    enable()
}

repositories {
    mavenCentral()
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = javaVersion
    }
}
