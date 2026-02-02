plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

dependencies {
    // Dòng này là "linh hồn" để Plugin tự sinh ra task applyPatches
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
