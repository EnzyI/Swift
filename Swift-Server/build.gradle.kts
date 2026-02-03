import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// CHIẾN THUẬT: Nạp đạn kiểu Kotlin DSL chuẩn
val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
val targetConfigs = listOf(
    "paperweightDevelopmentBundle", 
    "minecraftServer", 
    "minecraftMappings", 
    "paramMappings"
)

// Đăng ký nạp dependency khi configuration xuất hiện
targetConfigs.forEach { configName ->
    configurations.maybeCreate(configName) // Ép tạo nếu chưa có để tránh lỗi 'not found'
    dependencies.add(configName, bundle)
}

// GIỮ NGUYÊN HẮC THUẬT REPO (Đã hoạt động tốt)
val paperweight = extensions.getByName("paperweight")
val repoUrl = "https://repo.papermc.io/repository/maven-public/"
paperweight::class.java.methods.forEach { method ->
    if (method.name.startsWith("get") && method.name.endsWith("Repo")) {
        try {
            val prop = method.invoke(paperweight) as? Property<*>
            if (prop != null) {
                // Dùng @Suppress để tắt cái warning 'Unchecked cast' bro thấy trong log
                @Suppress("UNCHECKED_CAST")
                (prop as Property<String>).set(repoUrl)
            }
        } catch (e: Exception) {}
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
