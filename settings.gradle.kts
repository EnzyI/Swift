pluginManagement {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        gradlePluginPortal()
    }
}

rootProject.name = "Swift"
include("Swift-API")
include("Swift-Server")
