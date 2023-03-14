plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:geometry"))
    implementation(project(":common:collection"))
    implementation(project(":common:io"))
}
