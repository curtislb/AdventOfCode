plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion = "1.18.22"

dependencies {
    implementation(project(":common:collection"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
