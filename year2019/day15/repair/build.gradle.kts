plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:geometry"))
    implementation(project(":common:graph"))
    implementation(project(":common:intcode"))
}
