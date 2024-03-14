plugins {
    kotlin("jvm")
    id("convention.publishing")
    id("convention.testing")
}

group = "io.github.vacxe.danger.shellcheck"
version = "1.0.1"

dependencies {
    implementation("systems.danger:danger-kotlin-sdk:1.2")
    implementation("com.google.code.gson:gson:2.9.1")
}
