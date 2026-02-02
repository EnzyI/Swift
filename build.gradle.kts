plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

group = "com.swift.server"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Đây là nơi định nghĩa phiên bản Minecraft làm nền tảng (Base)
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

allprojects {
    apply(plugin = "java")
    
    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        // Kích hoạt Vector API cho SwiftMath
        options.compilerArgs.addAll(listOf("--add-modules", "jdk.incubator.vector"))
    }
}
