plugins {
    java
    id("io.papermc.paperweight.userdev") // Không cần để version ở đây nếu đã khai báo ở root
}

dependencies {
    // Ép kiểu String để tránh lỗi biên dịch script
    add("paperweightDevelopmentBundle", "io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}
