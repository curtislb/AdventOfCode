plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:grid"))
    implementation(project(":common:io"))
    implementation(project(":common:search"))
}
