plugins {
    java
    // Kích hoạt plugin paperweight ở dự án gốc
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

// Cấu hình cho dự án Server - nơi các task của paperweight sẽ xuất hiện
project(":Swift-Server") {
    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    dependencies {
        // Định nghĩa bản Minecraft nền tảng
        paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }
}

allprojects {
    group = "com.swift.server"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        // Kích hoạt Vector API cho SwiftMath
        options.compilerArgs.addAll(listOf("--add-modules", "jdk.incubator.vector"))
    }
}
