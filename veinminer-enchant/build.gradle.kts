import de.miraculixx.gradle.curseForgePublish
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    `kotlin-script`
    `fabric-script`
    `publish-script`
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.fabricmc.net/")
    maven("https://maven.neoforged.net/releases/")
}

dependencies {
    outlet.mcVersionRange = properties["gameVersion"] as String

    compileOnly("net.fabricmc:fabric-loader:${outlet.loaderVersion()}")
    modImplementation("io.github.llamalad7:mixinextras-fabric:0.3.5")
    include("io.github.llamalad7:mixinextras-fabric:0.3.5")
}

tasks.processResources {
    val tokens = mapOf(
        "version" to project.version.toString(),
        "license" to "LGPL-3.0-only"
    )
    inputs.properties(tokens)
    filesMatching("fabric.mod.json") {
        expand(tokens)
    }
}

tasks.jar {
    exclude("META-INF/neoforge.mods.toml")
    exclude("de/miraculixx/veinminerEnchant/neoforge/**")
    exclude("de/miraculixx/veinminerEnchant/paper/**")
    exclude("paper-plugin.yml", "plugin.yml", "paper-libraries.json")
    from(rootProject.file("LICENSES/LGPL-3.0-only.txt")) {
        into("META-INF/licenses")
    }
    from(rootProject.file("PORTING-NOTICE.md")) {
        into("META-INF")
    }
}

modrinth {
    uploadFile.set(tasks.remapJar)
    projectId = properties["modrinthEnchantmentId"] as String
    versionName = "Veinminer Enchantment - $version"
    outlet.mcVersionRange = properties["enchantmentVersions"] as String
    gameVersions.addAll(outlet.mcVersions())
    changelog = properties["changelogEnchantment"] as String
    loaders.add("fabric")
    dependencies {
        required.project(properties["modrinthId"] as String)
    }

    syncBodyFrom = rootProject.file("release-metadata/veinminer-enchantment-description.md").readText()
}

curseForgePublish {
    projectId.set(properties["curseforgeEnchantmentId"] as? String ?: "")
    artifactTaskName.set("remapJar")
    versionName.set("Veinminer Enchantment - $version")
    changelog.set(properties["changelogEnchantment"] as String)
    gameVersions.addAll(outlet.mcVersions())
    loaders.add("Fabric")
    environments.set(listOf("Server", "Client"))
}
