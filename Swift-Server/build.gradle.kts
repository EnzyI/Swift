import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
val targetConfigs = listOf(
    "paperweightDevelopmentBundle", 
    "minecraftServer", 
    "minecraftMappings", 
    "paramMappings"
)

// Tự động nạp bundle vào mọi phễu tiềm năng
targetConfigs.forEach { configName ->
    configurations.maybeCreate(configName)
    dependencies.add(configName, bundle)
}

// HẮC THUẬT REPO (Đã quá chuẩn rồi)
val paperweight = extensions.getByName("paperweight")
val repoUrl = "https://repo.papermc.io/repository/maven-public/"
paperweight::class.java.methods.forEach { method ->
    if (method.name.startsWith("get") && method.name.endsWith("Repo")) {
        try {
            val prop = method.invoke(paperweight) as? Property<*>
            if (prop != null) {
                @Suppress("UNCHECKED_CAST")
                (prop as Property<String>).set(repoUrl)
            }
        } catch (e: Exception) {}
    }
}

// ÉP MỌI TASK PHẢI ĐỢI BUNDLE TẢI XONG
tasks.configureEach {
    if (group?.contains("paperweight", ignoreCase = true) == true) {
        // Mọi task thuộc nhóm paperweight đều cần dữ liệu từ bundle
        dependsOn(configurations.getByName("paperweightDevelopmentBundle"))
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
