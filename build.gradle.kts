plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

koverMerged {
    enable()
}

repositories {
    mavenCentral()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = Versions.JAVA
}
