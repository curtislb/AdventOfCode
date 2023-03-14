plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:intcode"))
    implementation(project(":common:iteration"))
    testImplementation(project(":common:testing"))
}
