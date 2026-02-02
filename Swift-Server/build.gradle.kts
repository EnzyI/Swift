plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1"
}



dependencies {
    // Đây là dòng duy nhất kích hoạt việc giải mã Minecraft để làm Fork
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
