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

// --- KHU VỰC HẮC THUẬT V3: TIÊM DIỆN RỘNG ---
val paperweight = extensions.getByName("paperweight")
val repoUrl = "https://repo.papermc.io/repository/maven-public/"

// Danh sách các hàm Getter của những biến đang bị thiếu
val targetMethods = listOf(
    "getParamMappingsRepo", 
    "getRemapRepo", 
    "getReobfRepo", 
    "getStartupRepo"
)

println(">>> [Swift-Server] Bắt đầu chiến dịch tiêm Repo...")

paperweight::class.java.methods.forEach { method ->
    if (targetMethods.contains(method.name)) {
        try {
            // Ép kiểu và set giá trị cưỡng bức
            val prop = method.invoke(paperweight) as Property<String>
            prop.set(repoUrl)
            println(">>> [Swift-Server] Đã tiêm THÀNH CÔNG: ${method.name}")
        } catch (e: Exception) {
            println(">>> [Swift-Server] Lỗi khi tiêm ${method.name}: ${e.message}")
        }
    }
}
// ---------------------------------------------

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
