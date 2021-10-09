plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:intcode"))

    testImplementation(project(":common:testing"))
}
