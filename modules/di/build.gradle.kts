import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
        androidMain.dependencies {
            implementation(libs.koin.compose)
            implementation(libs.koin.android)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {

            // Logging
            implementation(libs.log.kermit)
            implementation(libs.log.slf4j)

            // DI
            implementation(libs.koin.core)

            // Network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization)

            // Modules
            implementation(projects.features.details.view)
            implementation(projects.features.details.viewmodel)
            implementation(projects.features.list.view)
            implementation(projects.features.list.viewmodel)
            implementation(projects.modules.core)
            implementation(projects.modules.data)
            implementation(projects.modules.domain)
            implementation(projects.modules.logger)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
    }
}

android {
    namespace = "io.imrekaszab.eaplayers.di"

    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        buildConfig = true
    }
    dependencies {
        // Detekt
        detektPlugins(libs.detekt.formatting)
    }
}
