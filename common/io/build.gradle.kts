plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion: String by project

dependencies {
    implementation(project(":common:parse"))
    implementation(project(":common:range"))

    testImplementation(project(":common:testing"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
