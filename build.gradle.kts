plugins {
    // Khai báo plugin ở đây để toàn bộ dự án đều nhận diện được
    java
    id("io.papermc.paperweight.userdev") version "1.7.1" apply false
}

allprojects {
    apply(plugin = "java")
    
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

// Cấu hình riêng cho module Server để chạy được applyPatches
project(":Swift-Server") {
    apply(plugin = "io.papermc.paperweight.userdev")

    dependencies {
        paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }
}
