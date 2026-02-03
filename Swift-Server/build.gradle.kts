plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// Khai báo đúng các tên mà Plugin Core tìm kiếm ngầm
val paperweightDevelopmentBundle by configurations.creating
val minecraftServer by configurations.creating
val minecraftMappings by configurations.creating

dependencies {
    // Nạp bundle vào cả 3 cấu hình để Plugin không thể kêu thiếu Mapping
    val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
    paperweightDevelopmentBundle(bundle)
    minecraftServer(bundle)
    minecraftMappings(bundle)
}

paperweight {
    // ĐỂ TRỐNG HOÀN TOÀN - Đây là chìa khóa để tránh lỗi Unresolved reference
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
