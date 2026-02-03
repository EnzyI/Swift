import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// Giữ nguyên phần dependencies đã đúng
val paperweightDevelopmentBundle by configurations.creating
val minecraftServer by configurations.creating
val minecraftMappings by configurations.creating

dependencies {
    val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
    paperweightDevelopmentBundle(bundle)
    minecraftServer(bundle)
    minecraftMappings(bundle)
}

// HẮC THUẬT V2: Truy cập qua Method thay vì Field để né Proxy
val paperweight = extensions.getByName("paperweight")
paperweight::class.java.methods.forEach { method ->
    if (method.name == "getParamMappingsRepo") {
        try {
            val prop = method.invoke(paperweight) as Property<String>
            prop.set("https://repo.papermc.io/repository/maven-public/")
            println(">>> [Swift-Server] Đã tiêm thành công qua Method!")
        } catch (e: Exception) {
            // Nếu không phải String, thử với URI
            println(">>> [Swift-Server] Lỗi nhẹ khi tiêm: ${e.message}")
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
