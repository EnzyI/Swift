plugins {
    java
    id("io.papermc.paperweight.core") // Không dùng userdev ở đây
}

dependencies {
    // Thay vì dùng paperweightDevelopmentBundle(...), dùng 'implementation' trỏ thẳng vào repo
    // Paperweight Core sẽ tự quét các dependency này để tìm bundle
    implementation("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

paperweight {
    // Đây là nơi khai báo tên server của bạn
    serverProjectName.set("Swift-Server")
    
    // Ép plugin nhận diện thư mục patches bạn đã tạo ở ảnh 16:13
    checkstyleConfigDir.set(file("codestyle"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
