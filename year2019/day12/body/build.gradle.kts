plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:collection"))
    implementation(project(":common:io"))
    testImplementation(project(":common:testing"))
}
