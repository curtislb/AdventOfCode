plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:number"))
    implementation(project(":common:range"))
    testImplementation(project(":common:testing"))
}
