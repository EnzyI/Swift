plugins {
    java
    // Nạp plugin vào classpath nhưng chưa apply ở root
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

// Cấu hình riêng cho Swift-Server
project(":Swift-Server") {
    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    // Sử dụng 'configure' kiểu này để tránh lỗi Unresolved khi nạp lần đầu
    extensions.configure<io.papermc.paperweight.userdev.ReobfExtension> {
        // Tạm để trống
    }

    dependencies {
        // Dùng hàm 'add' để né lỗi kiểm tra kiểu dữ liệu của Kotlin DSL
        add("paperweightDevelopmentBundle", "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }
}

project(":Swift-API") {
    apply(plugin = "java")
}
