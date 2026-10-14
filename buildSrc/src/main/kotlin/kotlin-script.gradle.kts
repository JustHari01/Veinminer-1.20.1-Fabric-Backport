import org.gradle.api.tasks.bundling.AbstractArchiveTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val javaTargetVersion = providers.gradleProperty("javaTargetVersion").get().toInt()

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.+")
    implementation(kotlin("reflect"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaTargetVersion))
    }
}

tasks {
    withType<AbstractArchiveTask>().configureEach {
        archiveVersion.set("${project.version}+${properties["gameVersion"]}")
    }

    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaTargetVersion)
    }
    compileKotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(javaTargetVersion.toString())
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }
}
