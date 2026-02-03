plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// Định nghĩa cấu hình bắt buộc cho Core
val paperweightDevelopmentBundle by configurations.creating

dependencies {
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

paperweight {
    // Sử dụng cách gán giá trị trực tiếp để tránh lỗi "Unresolved reference"
    projectName.set("Swift")
    
    // Fix lỗi 'paramMappingsRepo' từ file build_log.txt của bạn
    // Chúng ta trỏ thẳng vào repo Maven của Paper để lấy Mapping
    mappingsRepo.set("https://repo.papermc.io/repository/maven-public/")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
