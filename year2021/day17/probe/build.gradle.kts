plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:number"))
    implementation(project(":common:parse"))
    implementation(project(":common:search"))
    implementation(project(":common:vector"))
}
