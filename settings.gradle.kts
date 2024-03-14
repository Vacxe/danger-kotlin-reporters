rootProject.name = "danger-kotlin-shellcheck"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

includeBuild("build-logic")

include(":plugin")
