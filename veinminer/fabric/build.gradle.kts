import de.miraculixx.gradle.curseForgePublish

plugins {
    `kotlin-script`
    `fabric-script`
    `publish-script`
}

dependencies {
    implementation(project(":core")) {
        attributes {
            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
        }
    }
    implementation(project(":veinminer:veinminer-common")) {
        attributes {
            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
        }
    }
}

val coreSourceSets = project(":core").extensions.getByType<SourceSetContainer>()
val commonSourceSets = project(":veinminer:veinminer-common").extensions.getByType<SourceSetContainer>()

tasks.jar {
    dependsOn(":core:classes", ":veinminer:veinminer-common:classes")
    from(coreSourceSets.named("main").map { it.output })
    from(commonSourceSets.named("main").map { it.output })
    from(rootProject.file("LICENSES/AGPL-3.0-only.txt")) {
        into("META-INF/licenses")
    }
    from(rootProject.file("PORTING-NOTICE.md")) {
        into("META-INF")
    }
}

loom {
    accessWidenerPath = file("src/main/resources/veinminer.accesswidener")
    mods {
        create("veinminer") {
            sourceSet(sourceSets.main.get())
            sourceSet(coreSourceSets["main"])
            sourceSet(commonSourceSets["main"])
        }
    }
}

sourceSets {
    main {
        resources.srcDirs("$rootDir/veinminer/assets/")
    }
}

modrinth {
    uploadFile.set(tasks.remapJar)
    projectId = properties["modrinthId"] as String
    versionName = "Veinminer Fabric - $version"
    outlet.mcVersionRange = properties["fabricSupportedVersions"] as String
    gameVersions.addAll(outlet.mcVersions())
    loaders.addAll(buildList {
        add("fabric")
    })
    dependencies {
        required.project("fabric-api")
        required.project("fabric-language-kotlin")
        optional.project("veinminer-hotkey-1201-fabric-backport")
    }
}

curseForgePublish {
    projectId.set(properties["curseforgeId"] as String)
    artifactTaskName.set("remapJar")
    versionName.set("Veinminer Fabric - $version")
    changelog.set(properties["changelog"] as String)
    gameVersions.addAll(outlet.mcVersions())
    loaders.add("Fabric")
    requiredDependencies.addAll(listOf("fabric-api", "fabric-language-kotlin"))
    optionalDependencies.add("veinminer-hotkey-1201-fabric-backport")
}
