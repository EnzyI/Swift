plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1" apply false
}

allprojects {
    group = "com.swift.server"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

project(":Swift-Server") {
    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    dependencies {
        // Quay lại với bản "huyền thoại" ổn định
        add("paperweightDevelopmentBundle", "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }
}

project(":Swift-API") {
    apply(plugin = "java")
}
