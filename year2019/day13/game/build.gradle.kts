plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:grid"))
    api(project(":common:intcode"))

    testImplementation(project(":common:testing"))
}
