plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:io"))
    implementation(project(":common:number"))
    testImplementation(project(":common:testing"))
}
