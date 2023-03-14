plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:geometry"))
    api(project(":common:number"))
    implementation(project(":common:grid"))
}
