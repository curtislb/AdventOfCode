plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:vector"))
    implementation(project(":common:collection"))
    implementation(project(":common:geometry"))
    implementation(project(":common:io"))
    implementation(project(":common:simulation"))
}
