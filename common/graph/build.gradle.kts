plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:heap"))
    testImplementation(project(":common:testing"))
}
