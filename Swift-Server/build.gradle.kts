plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// Tự tay định nghĩa các cấu hình mà Core đang tìm kiếm
val paperweightDevelopmentBundle by configurations.creating
val minecraftServer by configurations.creating
val minecraftMappings by configurations.creating

dependencies {
    // Nạp bundle vào cả 3 cấu hình để Plugin tự hiểu mà không cần hỏi mappingsRepo ở đâu
    val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
    paperweightDevelopmentBundle(bundle)
    minecraftServer(bundle)
    minecraftMappings(bundle)
}

paperweight {
    // Để trống hoàn toàn - Tuyệt đối không thêm projectName hay mappingsRepo ở đây
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
