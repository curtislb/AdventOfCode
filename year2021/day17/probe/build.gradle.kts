plugins {
    id("com.curtislb.adventofcode.kotlin-library")
    }

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:io"))
    implementation(project(":common:number"))
    implementation(project(":common:search"))
}
