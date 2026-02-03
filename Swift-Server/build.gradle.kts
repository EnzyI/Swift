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

targetConfigs.forEach { configName ->
    configurations.maybeCreate(configName)
    dependencies.add(configName, bundle)
}

// HẮC THUẬT REPO (Giữ nguyên)
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

// MỚI: ÉP THỨ TỰ CHẠY TASK
// Đảm bảo task bung bundle phải chạy TRƯỚC khi task filter đòi dữ liệu
tasks.withType<org.gradle.api.Task>().configureEach {
    if (name == "filterSpigotExcludes") {
        dependsOn(":Swift-Server:setupPaperweightVisualStudioCode") 
        // Hoặc các task extract mặc định của paperweight nếu có
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
