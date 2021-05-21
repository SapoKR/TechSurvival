plugins {
    kotlin("jvm") version "1.5.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    maven("https://jitpack.io")  // For monun's libraries.
    maven("https://papermc.io/repo/repository/maven-public/") // PaperMC
}

dependencies {
    implementation(kotlin("stdlib")) // Kotlin
    implementation("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT") // Paper Latest
    implementation("com.github.monun:kommand:+") // For future command creation convenience.
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "16" // https://papermc.io/java16 | 페이퍼 개놈들아
    }
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
        filteringCharset = "UTF-8"
    }
    shadowJar {
        archiveClassifier.set("dist")
        archiveVersion.set("")
    }
    create<Copy>("dist") {
        from (shadowJar)
        into(".\\")
    }
}