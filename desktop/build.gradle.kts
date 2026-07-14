import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

group = libs.versions.desktop.group.get()
version = libs.versions.desktop.version.get()

kotlin {
    jvm("desktop") {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        val desktopMain = getByName("desktopMain")
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.desktop {
    application {
        mainClass = libs.versions.desktop.mainClass.get()
        nativeDistributions {
            targetFormats = setOf(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = libs.versions.desktop.distributionPackageName.get()
            packageVersion = libs.versions.desktop.distributionPackageVersion.get()
        }
    }
}

layout.buildDirectory.set(file("$rootDir/.build/desktop"))