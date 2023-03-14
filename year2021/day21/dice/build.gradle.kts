plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    api(project(":common:collection"))
    implementation(project(":common:io"))
    implementation(project(":common:iteration"))
    implementation(project(":common:number"))
}
