plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:grid"))
    implementation(project(":common:collection"))
    implementation(project(":common:io"))
}
