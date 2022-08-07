plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val coroutinesVersion: String by project

dependencies {
    api(project(":common:range"))
    implementation(project(":common:intcode"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
