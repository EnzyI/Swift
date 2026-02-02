plugins {
    java
    id("io.papermc.paperweight.core")
}

dependencies {
    // Vẫn dùng bundle cũ làm nguyên liệu
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

paperweight {
    // Chỉ định dự án này là dự án Server để Patch
    serverProject.set(project)
    
    // Cấu hình nơi chứa mã nguồn sau khi decompile
    // Thường là thư mục 'Swift-Server/src/main/java'
}
