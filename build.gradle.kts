plugins {
    id("io.papermc.paperweight.core") version "1.7.1" apply false
}

allprojects {
    group = "com.swift.server"
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
