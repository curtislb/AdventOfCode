plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:range"))

    testImplementation(project(":common:testing"))
}
