import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import kotlinx.kover.gradle.plugin.dsl.CoverageUnit
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinSerialization).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.composeMultiplatform).apply(false)
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.kotlinJvm).apply(false)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.addAll(
            listOf(
                "-Xconsistent-data-class-copy-visibility",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            )
        )
    }
}

val defaultRequiredMinimumCoverage = 80
val defaultRequiredMaximumCoverage = 100

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jetbrains.kotlinx.kover")

    val koverExcludeList = listOf(
        "*Activity",
        "*Activity\$*",
        "*.databinding.*",
        "*.BuildConfig",
        "*ComposableSingletons",
        "*_Factory",
        "*ComposableSingletons",
    )

    val koverIncludeList = listOf("*.viewmodel.*", "*.data.service.*")

    extensions.configure<DetektExtension> {
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

    kover {
        currentProject {
            instrumentation {
                excludedClasses.addAll(koverExcludeList)
            }
            sources {
                excludeJava = true
            }
        }
        reports {
            filters {
                excludes { classes(koverExcludeList) }
                includes { classes(koverIncludeList) }
            }

            total {
                filters {
                    excludes { classes(koverExcludeList) }
                    includes { classes(koverIncludeList) }
                }
                verify {
                    rule("Minimum coverage verification error") {
                        disabled = false
                        groupBy = kotlinx.kover.gradle.plugin.dsl.GroupingEntityType.APPLICATION

                        bound {
                            minValue.set(defaultRequiredMinimumCoverage)
                            maxValue.set(defaultRequiredMaximumCoverage)
                            coverageUnits.set(CoverageUnit.LINE)
                            aggregationForGroup = kotlinx.kover.gradle.plugin.dsl.AggregationType.COVERED_PERCENTAGE
                        }
                    }
                }
            }
        }
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}
