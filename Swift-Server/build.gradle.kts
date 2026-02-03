import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// CHIẾN THUẬT: Lazy Injection - Tự động nạp khi thấy phễu xuất hiện
val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
val targetConfigs = listOf(
    "paperweightDevelopmentBundle", 
    "minecraftServer", 
    "minecraftMappings", 
    "paramMappings"
)

configurations.all {
    if (targetConfigs.contains(name)) {
        dependencies.add(name, bundle)
        println(">>> [Swift-Server] Đã tìm thấy và nạp đạn cho: $name")
    }
}

// GIỮ NGUYÊN HẮC THUẬT REPO (Đã quá ổn định rồi)
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
