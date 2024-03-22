rootProject.name = "danger-kotlin-reporters"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

includeBuild("build-logic")

include(":core")
include(":shellcheck")
