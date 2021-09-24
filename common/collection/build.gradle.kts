plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    testImplementation(project(":common:testing"))
    testImplementation("org.hamcrest:hamcrest:2.2")
}
