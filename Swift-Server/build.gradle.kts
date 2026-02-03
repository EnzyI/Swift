import org.gradle.api.provider.Property

plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

// 1. Khai báo các phễu dữ liệu
val paperweightDevelopmentBundle by configurations.creating
val minecraftServer by configurations.creating
val minecraftMappings by configurations.creating
val paramMappings by configurations.creating // Phễu đang bị báo trống

dependencies {
    val bundle = "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT"
    paperweightDevelopmentBundle(bundle)
    minecraftServer(bundle)
    minecraftMappings(bundle)
    paramMappings(bundle) // Nạp bundle vào đây để sửa lỗi 'contains no files'
}

// 2. GIỮ NGUYÊN HẮC THUẬT (Để vượt qua bước check Repo)
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
