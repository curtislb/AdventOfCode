plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

dependencies {
    implementation(project(":common:grid"))
    implementation(project(":common:io"))
    implementation(project(":year2021:day13:origami"))
}
