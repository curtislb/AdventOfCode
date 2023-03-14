plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

dependencies {
    implementation(project(":common:io"))
    implementation(project(":common:parse"))
    implementation(project(":year2020:day16:ticket"))
}
