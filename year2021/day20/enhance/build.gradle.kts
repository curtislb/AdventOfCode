plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:geometry"))
    api(project(":common:grid"))
    implementation(project(":common:io"))
    implementation(project(":common:number"))
}
