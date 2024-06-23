import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm")
    id("convention.publishing")
    id("convention.testing")
}

group = "io.github.vacxe.danger.kotlin"


dependencies {
    implementation("systems.danger:danger-kotlin-sdk:1.2")
    implementation("io.github.vacxe.danger.kotlin:core:1.0.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
