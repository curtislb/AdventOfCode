plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation("org.assertj:assertj-core:${Versions.ASSERTJ}")
    implementation("org.hamcrest:hamcrest:${Versions.HAMCREST}")
    implementation("org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}")
}
