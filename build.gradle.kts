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

// Cấu hình riêng cho Swift-Server - Đây là nơi "phẫu thuật"
project(":Swift-Server") {
    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    dependencies {
        // Phải đặt trong block dependencies của module đã apply plugin paperweight
        paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("--add-modules", "jdk.incubator.vector"))
    }
}

// Cấu hình cho API
project(":Swift-API") {
    apply(plugin = "java")
    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }
}
