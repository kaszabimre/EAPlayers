import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }
        commonMain.dependencies {
            // Logging
            implementation(libs.log.kermit)
            implementation(libs.log.slf4j)

            // Compose Multiplatform
            implementation(libs.compose.viewmodel)
            implementation(libs.compose.navigation)
            implementation(libs.compose.multiplatform.ui)

            implementation(libs.kotlinx.coroutines.core)

            // DI
            implementation(libs.koin.compose.multiplatform)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.imrekaszab.eaplayers.core"

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
