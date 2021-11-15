plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val hamcrestVersion: String by project
val kotlinVersion: String by project

dependencies {
    implementation("org.hamcrest:hamcrest:$hamcrestVersion")
    implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
