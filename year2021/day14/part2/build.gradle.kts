plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

dependencies {
    implementation(project(":common:collection"))
    implementation(project(":common:graph"))
    implementation(project(":common:grid"))
    implementation(project(":common:io"))
    implementation(project(":common:math"))
    implementation(project(":common:range"))
    implementation(project(":common:search"))
    implementation(project(":common:simulation"))
//    implementation(project(":year2021:day00:example"))
}
