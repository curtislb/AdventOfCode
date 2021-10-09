plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion = "1.18.22"

dependencies {
    implementation(project(":common:parse"))
    implementation(project(":common:range"))

    testImplementation(project(":common:testing"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
