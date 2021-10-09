plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val hamcrestVersion = "2.2"
val lombokVersion = "1.18.22"

dependencies {
    testImplementation(project(":common:testing"))
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
