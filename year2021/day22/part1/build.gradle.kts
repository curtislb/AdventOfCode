plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

dependencies {
    implementation(project(":common:geometry"))
    implementation(project(":common:range"))
    implementation(project(":year2021:day22:reactor"))
}
