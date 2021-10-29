plugins {
    id("com.curtislb.adventofcode.kotlin-library")
}

val hamcrestVersion: String by extra
val kotlinVersion: String by extra

dependencies {
    implementation("org.hamcrest:hamcrest:$hamcrestVersion")
    implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
