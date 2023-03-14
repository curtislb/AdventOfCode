plugins {
    id("com.curtislb.adventofcode.kotlin-puzzle")
}

dependencies {
    implementation(project(":common:comparison"))
    implementation(project(":common:number"))
    implementation(project(":year2021:day09:basin"))
}
