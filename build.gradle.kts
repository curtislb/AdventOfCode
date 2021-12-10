plugins {
    jacoco
    kotlin("jvm")
}

val javaVersion: String by project

dependencies {
    // Add all leaf subprojects as dependencies
    subprojects.filter { it.subprojects.isEmpty() }.forEach { implementation(it) }
}

repositories {
    mavenCentral()
}

@Suppress("HasPlatformType")
val rootPath = rootDir.toPath().toAbsolutePath()

// Checks if the given file or directory is part of the root project
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
    group = "verification"

    additionalClassDirs(configurations.runtimeClasspath.get().filter { it.isInProject() })
    additionalSourceDirs(sourcesPath.incoming.artifactView { lenient(true) }.files.filter { file ->
        file.isInProject()
    })
    executionData(coverageDataPath.incoming.artifactView { lenient(true) }.files.filter { file ->
        file.exists() && file.isInProject()
    })

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = javaVersion
    }

    // Make JaCoCo report generation part of the 'check' lifecycle phase
    check {
        dependsOn(codeCoverageReport)
    }
}
