import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// 1. Định nghĩa các cấu hình (Phần này đã đúng, giữ nguyên)
val paperweightDevelopmentBundle by configurations.creating
val minecraftServer by configurations.creating
val minecraftMappings by configurations.creating

dependencies {
    val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
    paperweightDevelopmentBundle(bundle)
    minecraftServer(bundle)
    minecraftMappings(bundle)
}

// 2. HẮC THUẬT: Dùng Reflection để set giá trị 'paramMappingsRepo'
// Bỏ qua Paperweight DSL, ta chọc thẳng vào field ẩn của nó.
try {
    val paperweight = extensions.getByName("paperweight")
    // Tìm biến tên là 'paramMappingsRepo' như trong log lỗi của bạn
    val field = paperweight.javaClass.getDeclaredField("paramMappingsRepo")
    field.isAccessible = true // Mở khóa truy cập
    
    // Ép kiểu về Property<String> và set giá trị
    val prop = field.get(paperweight) as Property<String>
    prop.set("https://repo.papermc.io/repository/maven-public/")
    
    println(">>> [Swift-Server] Đã tiêm 'paramMappingsRepo' thành công!")
} catch (e: Exception) {
    println(">>> [Swift-Server] Cảnh báo: Không thể tiêm giá trị: ${e.message}")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
