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
        commonMain.dependencies {
            implementation(libs.compose.navigation)

            implementation(projects.modules.core)
            implementation(projects.modules.domain)
            implementation(projects.modules.testing)
        }

        androidUnitTest.dependencies {
            // Test
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.test.junit)
            implementation(libs.test.coroutines)
            implementation(libs.test.mockk)
        }
    }
}

android {
    namespace = "io.imrekaszab.eaplayers.playerdetails.viewmodel"

    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
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
