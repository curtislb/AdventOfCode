plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:math"))
    implementation(project(":common:range"))
    testImplementation(project(":common:collection"))
    testImplementation(project(":common:testing"))
}