plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:vector"))
    implementation(project(":common:io"))
    implementation(project(":common:iteration"))
    testImplementation(project(":common:testing"))
}
