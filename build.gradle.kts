import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinSerialization).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.composeMultiplatform).apply(false)
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.detekt)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    extensions.configure<DetektExtension> {
//    autoCorrect = System.getenv()["CI"] != "true"
        autoCorrect = true
        source.setFrom(
            objects.fileCollection().from(
                DetektExtension.DEFAULT_SRC_DIR_JAVA,
                DetektExtension.DEFAULT_TEST_SRC_DIR_JAVA,
                DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
                DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN,
                "src/androidMain",
                "src/commonMain",
                "src/iosMain",
                "src/commonTest",
                "src/iosTest",
                "src/androidUnitTest",
                "src/test",
                "src/testDebug",
                "src/testRelease",
                "build.gradle.kts",
            )
        )
        buildUponDefaultConfig = false
        // point to your custom config defining rules to run, overwriting default behavior
        config.setFrom(files("$rootDir/config/detekt.yml"))
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = libs.versions.javaTargetCompatibility.get()
        reports {
            html.required.set(true)
            xml.required.set(true)
        }
    }

    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = libs.versions.javaTargetCompatibility.get()
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}
