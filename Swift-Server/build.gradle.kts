plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// Tự định nghĩa các cấu hình mà Plugin Core yêu cầu
val paperweightDevelopmentBundle by configurations.creating
val minecraftServer by configurations.creating
val minecraftMappings by configurations.creating

dependencies {
    // 1. Bundle chứa nguyên liệu build
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
    
    // 2. Ép nạp server gốc để lấy Mappings (Fix lỗi trong log của bạn)
    minecraftServer("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

paperweight {
    // Khai báo repo chứa Mapping để sửa lỗi 'paramMappingsRepo'
    serverProjectName.set("Swift-Server")
    
    // Đây là dòng "cứu cánh" để fix lỗi property 'paramMappingsRepo'
    paramMappingsRepo.set("https://maven.quiltmc.org/repository/release/")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
