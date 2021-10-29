plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion: String by extra

dependencies {
    implementation(project(":common:math"))
    implementation(project(":common:range"))

    testImplementation(project(":common:collection"))
    testImplementation(project(":common:testing"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
