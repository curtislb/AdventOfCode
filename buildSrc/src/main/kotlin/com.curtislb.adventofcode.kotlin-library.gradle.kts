plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}")
}

repositories {
    mavenCentral()
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = Versions.JAVA
    }
}
