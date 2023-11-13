plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation("org.assertj:assertj-core:${Versions.ASSERTJ}")
    testImplementation("org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
