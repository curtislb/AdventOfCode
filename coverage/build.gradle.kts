plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    // Common libraries
    implementation(project(":common:collection"))
    implementation(project(":common:graph"))
    implementation(project(":common:grid"))
    implementation(project(":common:heap"))
    implementation(project(":common:intcode"))
    implementation(project(":common:io"))
    implementation(project(":common:math"))
    implementation(project(":common:parse"))
    implementation(project(":common:range"))
    implementation(project(":common:search"))
    implementation(project(":common:simulation"))
    implementation(project(":common:testing"))

    // Puzzle solutions
    for (year in 2019..2020) {
        // Puzzles for days 1-24 have two parts
        for (day in 1..24) {
            for (part in 1..2) {
                implementation(project(":year$year:day${"%02d".format(day)}:part$part"))
            }
        }

        // The puzzle for day 25 has only one part
        implementation(project(":year$year:day25:part1"))
    }
}

repositories {
    mavenCentral()
}

val rootPath = rootDir.toPath().toAbsolutePath()

// Checks if the given file or directory is part of the root project.
fun File.isInProject(): Boolean = toPath().toAbsolutePath().startsWith(rootPath)

// A resolvable configuration to collect source code
val sourcesPath: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(configurations.implementation.get())

    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
        attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named("source-folders"))
    }
}

// A resolvable configuration to collect JaCoCo coverage data
val coverageDataPath: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(configurations.implementation.get())

    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
        attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named("jacoco-coverage-data"))
    }
}

// Task to gather code coverage from multiple subprojects
val codeCoverageReport by tasks.registering(JacocoReport::class) {
    additionalClassDirs(configurations.runtimeClasspath.get().filter { it.isInProject() })
    additionalSourceDirs(sourcesPath.incoming.artifactView { lenient(true) }.files.filter { it.isInProject() })
    executionData(coverageDataPath.incoming.artifactView { lenient(true) }.files.filter { file ->
        file.exists() && file.isInProject()
    })

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

// Make JaCoCo report generation part of the 'check' lifecycle phase
tasks.check {
    dependsOn(codeCoverageReport)
}
