plugins {
    java
    id("io.papermc.paperweight.core")
}

dependencies {
    // Trong core, ta dùng 'paperweightDevelopmentBundle' làm config name
    "paperweightDevelopmentBundle"("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

// Chỉ khai báo block này nếu bạn thực sự cần đổi tên, 
// nhưng để an toàn hãy cứ để mặc định trước
paperweight {
}
