plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

dependencies {
    // Dòng này chính là "ngòi nổ" để hiện ra task applyPatches
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}
