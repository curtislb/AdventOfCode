plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val hamcrestVersion: String by project
val lombokVersion: String by project

dependencies {
    testImplementation(project(":common:testing"))
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
