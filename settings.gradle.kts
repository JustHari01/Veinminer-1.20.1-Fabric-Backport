rootProject.name = "Veinminer"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://maven.neoforged.net/releases/")
    }
}

// Common code for all modules
include(":core")

// This port targets Minecraft 1.20.1 Fabric only.
include(":veinminer:common")
include(":veinminer:fabric")
include(":veinminer-client:common")
include(":veinminer-client:fabric")
include(":veinminer-enchant")

// Give the nested modules unique names so Gradle resolves both `common` modules.
project(":veinminer:common").name = "veinminer-common"
project(":veinminer:fabric").name = "veinminer-fabric"
project(":veinminer-client:common").name = "veinminer-client-common"
project(":veinminer-client:fabric").name = "veinminer-client-fabric"
