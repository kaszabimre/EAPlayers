import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            freeCompilerArgs += listOf("-Xbinary=bundleId=io.imrekaszab.eaplayers.ComposeApp")
        }
    }
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.compose.navigation)
            implementation(libs.compose.multiplatform.ui)

            implementation(libs.koin.core)
            implementation(libs.koin.compose.multiplatform)
            implementation(libs.stately.common)

            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)

            // Modules
            implementation(projects.features.details.view)
            implementation(projects.features.details.viewmodel)
            implementation(projects.features.list.view)
            implementation(projects.features.list.viewmodel)
            implementation(projects.modules.core)
            implementation(projects.modules.di)
            implementation(projects.modules.domain)
            implementation(projects.modules.navigation)
            implementation(projects.modules.theme)
        }
    }
}

android {
    namespace = "io.imrekaszab.eaplayers"
    compileSdk = libs.versions.targetSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "io.imrekaszab.eaplayers"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_PATH", "\"https://drop-api.ea.com\"")
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    dependencies {
        implementation(libs.androidx.compose.material)
        implementation(libs.kotlinx.coroutines.android)
        debugImplementation(compose.uiTooling)
        implementation(libs.compose.ui)

        // Compose Multiplatform
        implementation(libs.compose.viewmodel)
        implementation(libs.compose.navigation)
        implementation(libs.compose.multiplatform.ui)

        // Detekt
        detektPlugins(libs.detekt.formatting)
    }
}
