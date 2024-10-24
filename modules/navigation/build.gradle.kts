import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.compose.multiplatform.ui)
            implementation(libs.compose.navigation)

            // DI
            implementation(libs.koin.core)
            implementation(libs.koin.compose.multiplatform)
            implementation(libs.stately.common)

            // Modules
            implementation(projects.features.details.view)
            implementation(projects.features.details.viewmodel)
            implementation(projects.features.list.view)
            implementation(projects.features.list.viewmodel)
            implementation(projects.modules.theme)
        }
    }
}

android {
    namespace = "io.imrekaszab.eaplayers.navigation"

    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    buildFeatures {
        compose = true
        buildConfig = true
    }
    dependencies {
        // Detekt
        detektPlugins(libs.detekt.formatting)
    }
}
