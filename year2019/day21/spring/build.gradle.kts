plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion: String by extra

dependencies {
    implementation(project(":common:intcode"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
