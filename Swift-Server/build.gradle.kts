import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

val paperweightDevelopmentBundle by configurations.creating
val minecraftServer by configurations.creating
val minecraftMappings by configurations.creating

dependencies {
    val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
    paperweightDevelopmentBundle(bundle)
    minecraftServer(bundle)
    minecraftMappings(bundle)
}

// --- CHIẾN DỊCH TẬN DIỆT V4 ---
val paperweight = extensions.getByName("paperweight")
val repoUrl = "https://repo.papermc.io/repository/maven-public/"

println(">>> [Swift-Server] Đang quét và tiêu diệt các Repo trống...")

paperweight::class.java.methods.forEach { method ->
    // Quét tất cả các Method Getter có chữ "Repo" ở cuối
    if (method.name.startsWith("get") && method.name.endsWith("Repo")) {
        try {
            val prop = method.invoke(paperweight) as? Property<*>
            if (prop != null) {
                // Ép kiểu sang String property và set giá trị
                (prop as Property<String>).set(repoUrl)
                println(">>> [Swift-Server] Đã khóa mục tiêu: ${method.name}")
            }
        } catch (e: Exception) {
            // Bỏ qua nếu không phải Property<String>
        }
    }
}
// ------------------------------

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
