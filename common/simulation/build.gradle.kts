plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion = "1.18.22"

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
