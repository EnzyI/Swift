plugins {
    java
    id("io.papermc.paperweight.patcher") version "1.7.1"
}

// Đây là block bắt buộc để kích hoạt applyPatches
patcher {
    parent.set(project.rootProject)
}

dependencies {
    // Dùng bundle này để làm nguyên liệu vá (patch)
    add("paperweightDevelopmentBundle", "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}
