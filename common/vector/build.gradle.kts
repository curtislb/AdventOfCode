plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:iteration"))
    testImplementation(project(":common:testing"))
}
