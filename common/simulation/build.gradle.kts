plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val lombokVersion: String by extra

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
