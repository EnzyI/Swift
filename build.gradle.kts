plugins {
    id("io.papermc.paperweight.userdev") version "1.7.1" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
