plugins {
    id("application")
    id("com.curtislb.adventofcode.kotlin-library")
}

dependencies {
    implementation(project(":common:io"))
    testImplementation(project(":common:io"))
}

tasks.run<JavaExec> {
    workingDir = rootDir
}

tasks.test {
    workingDir = rootDir
}
