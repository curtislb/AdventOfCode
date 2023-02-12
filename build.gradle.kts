import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.kover") version Versions.KOVER
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlinx.kover")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = Versions.JAVA
    }
}

koverMerged {
    enable()
}
