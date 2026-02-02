plugins {
    java
    id("io.papermc.paperweight.core") version "1.7.1"
}

dependencies {
    // Khai báo bundle theo chuẩn String-base để tránh lỗi biên dịch script
    "paperweightDevelopmentBundle"("io.papermc.paper:dev-bundle:1.20.4-R0.1-SNAPSHOT")
}

paperweight {
    // Để trống để ChatGPT có thể yêu cầu thêm các tùy chỉnh sau này
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
