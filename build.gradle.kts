plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1" apply false
}

allprojects {
    apply(plugin = "java")
    group = "com.swift.server"
    version = "1.0.0-SNAPSHOT"

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf(
        "--add-modules", "jdk.incubator.vector",
        "-XX:+UnlockExperimentalVMOptions",
        "-XX:+EnableVectorReboxing",
        "-XX:+EnableVectorAggressiveReboxing"
    ))
}
