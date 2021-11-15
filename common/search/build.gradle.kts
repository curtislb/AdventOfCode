plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion: String by project

dependencies {
    implementation(project(":common:collection"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
