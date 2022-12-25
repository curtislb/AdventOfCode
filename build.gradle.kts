plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.kover") version PluginVersions.KOVER
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
