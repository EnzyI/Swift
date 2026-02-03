import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// 1. Nạp "nguyên liệu" vào các phễu có sẵn của Plugin
dependencies {
    val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
    
    // Dùng 'implementation' hoặc tên gốc để nạp vào các configuration mà Plugin tự tạo
    "paperweightDevelopmentBundle"(bundle)
    "minecraftServer"(bundle)
    "minecraftMappings"(bundle)
    "paramMappings"(bundle)
}

// 2. GIỮ NGUYÊN HẮC THUẬT QUÉT REPO (Đã hoạt động ở ảnh 19:07)
val paperweight = extensions.getByName("paperweight")
val repoUrl = "https://repo.papermc.io/repository/maven-public/"

paperweight::class.java.methods.forEach { method ->
    if (method.name.startsWith("get") && method.name.endsWith("Repo")) {
        try {
            val prop = method.invoke(paperweight) as? Property<*>
            if (prop != null) {
                (prop as Property<String>).set(repoUrl)
            }
        } catch (e: Exception) {}
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
