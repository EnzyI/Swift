plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1" apply false
}

// Cấu hình chung cho tất cả
allprojects {
    group = "com.swift.server"
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

// Cấu hình ĐÍCH DANH cho module Server
project(":Swift-Server") {
    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    dependencies {
        // Sử dụng chuỗi string để né lỗi 'unresolved reference' lúc biên dịch script
        "paperweightDevelopmentBundle"("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }
}
