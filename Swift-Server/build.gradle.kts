plugins {
    java
    // Vẫn dùng userdev nhưng phải đi kèm với cấu hình đặc biệt bên dưới
    id("io.papermc.paperweight.userdev") version "1.7.1" 
}

// Đây là "chìa khóa" để mở task applyPatches
paperweight {
    serverProject.set(project(":Swift-Server"))
}

dependencies {
    // Phải dùng 'paperweightDevelopmentBundle' thì task mới xuất hiện
    add("paperweightDevelopmentBundle", "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}
