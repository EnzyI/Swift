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

// Cấu hình cho Module Server - Dùng cách gọi này để tránh lỗi Unresolved
project(":Swift-Server") {
    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    // Sử dụng block 'the' để truy cập trực tiếp vào extension của Paperweight
    configure<io.papermc.paperweight.userdev.ReobfExtension> {
        // Cấu hình nếu cần, tạm thời để trống
    }

    dependencies {
        // Dùng chuỗi string trực tiếp để "vượt mặt" lỗi check cú pháp của Kotlin
        "paperweightDevelopmentBundle"("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }
}

project(":Swift-API") {
    apply(plugin = "java")
    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }
}
