plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:io"))
    implementation(project(":common:math"))

    testImplementation(project(":common:testing"))
}
