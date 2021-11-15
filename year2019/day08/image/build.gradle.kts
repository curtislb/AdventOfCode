plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion: String by project

dependencies {
    implementation(project(":common:io"))
    implementation(project(":common:math"))

    testImplementation(project(":common:testing"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
