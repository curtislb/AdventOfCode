plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    }

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:graph"))
    implementation(project(":common:number"))
    implementation(project(":common:range"))
}
