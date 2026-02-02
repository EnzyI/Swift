plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

dependencies {
    // Đây là dòng quan trọng nhất để kích hoạt applyPatches
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

// Thêm dòng này để ép Paperweight nhận diện đúng mode làm việc
paperweight {
    // Cấu hình này giúp Plugin biết bạn đang chuẩn bị can thiệp mã nguồn
}
