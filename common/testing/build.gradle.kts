plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val hamcrestVersion = "2.2"
val kotlinVersion = "1.5.31"

dependencies {
    implementation("org.hamcrest:hamcrest:$hamcrestVersion")
    implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
