plugins {
    `kotlin-dsl`
}

val kotlinVersion = "1.5.31"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}

repositories {
    mavenCentral()
}
