import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("maven-publish")
    id("signing")
    id("com.github.johnrengelman.shadow")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<ShadowJar>().configureEach {
    archiveClassifier.set("")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "plugin"

            from(components["java"])

            pom {
                name.set("danger-kotlin-shellcheck")
                description.set("Danger-kotlin plugin to parse and report Shellcheck violations")
                url.set("https://github.com/vacxe/danger-kotlin-shellcheck")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("vacxe")
                        name.set("Konstantin Aksenov")
                        email.set("aksenov.kostya@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/vacxe/danger-kotlin-shellcheck.git")
                    developerConnection.set("scm:git:ssh://github.com/vacxe/danger-kotlin-shellcheck.git")
                    url.set("https://github.com/vacxe/danger-kotlin-shellcheck")
                }
            }
        }
        repositories {
            maven {
                credentials {
                    username = System.getenv("OSSRH_USERNAME")
                    password = System.getenv("OSSRH_PASSWORD")
                }
                name = "sonatype"
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
        }
    }
}

signing {
    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications)
}
