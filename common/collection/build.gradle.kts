plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val hamcrestVersion: String by extra
val lombokVersion: String by extra

dependencies {
    testImplementation(project(":common:testing"))
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
