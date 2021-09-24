plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val coroutinesVersion = "1.5.2"

dependencies {
    api(project(":common:range"))
    implementation(project(":common:intcode"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
