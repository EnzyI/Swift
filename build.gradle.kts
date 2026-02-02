plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.3" apply false // Hạ phiên bản xuống bản ổn định hơn
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
        // Dùng 'add' để vượt qua bước kiểm tra cú pháp của Kotlin DSL
        add("paperweightDevelopmentBundle", "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }
}

