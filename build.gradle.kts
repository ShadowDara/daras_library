plugins {
    id("java")
    kotlin("jvm")
    //kotlin("plugin.serialization") version "1.9.10"
}

group = "de.shadowdara.daras_library"
version = "0.1.5-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(kotlin("stdlib-jdk8"))
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "de.shadowdara.daras_library.Main"
        }

        // nur dein Code rein, keine dependencies
        from(sourceSets.main.get().output)

        // optional: verhindern, dass ungewollt was reingepackt wird
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
    }
}
