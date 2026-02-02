plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.3" apply false
}

allprojects {
    group = "com.swift.server"
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

// Chỗ này phải khớp 100% với tên thư mục con
project(":Swift-Server") {
    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    dependencies {
        // Dùng 'add' để chắc chắn không bị lỗi check kiểu của Kotlin
        add("paperweightDevelopmentBundle", "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }
}
