plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion = "1.18.22"

dependencies {
    implementation(project(":common:io"))
    implementation(project(":common:math"))

    testImplementation(project(":common:testing"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
