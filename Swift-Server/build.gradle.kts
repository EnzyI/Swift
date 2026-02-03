plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// Tự tạo cấu hình để Plugin không báo "not found" nữa
val paperweightDevelopmentBundle: Configuration by configurations.creating

dependencies {
    // Nạp bundle vào đúng cấu hình vừa tạo
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

paperweight {
    // Giữ nguyên để tránh lỗi reference ở giai đoạn này
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
