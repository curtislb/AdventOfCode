plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:geometry"))
    implementation(project(":common:iteration"))
    implementation(project(":common:parse"))
}
