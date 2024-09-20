package com.kmpshowcase.buildlogic

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun KotlinMultiplatformExtension.configureCompose(
    compose: ComposePlugin.Dependencies,
    libs: LibrariesForLibs
) {
    compilerOptions {
        // Don't propagate the experimental annotation to the generated code.
        // This is a workaround for the TemplateAndroidAppTheme experimental annotation.
        freeCompilerArgs.add("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(project.dependencies.platform(libs.koin.bom))
                api(libs.koin.compose)
                api(libs.koin.compose.viewmodel)

                api(libs.bundles.coil)

                // Navigation
                api(libs.navigation.compose)

                // Paging
                implementation(libs.paging.appcache.comopose)
            }
        }
        val commonTest by getting {
            dependencies {
                // Dependencies go here
//                    implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
            }
        }
        val iosMain by getting {
            dependencies {
                // Dependencies go here
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosTest by getting {
            dependencies {
                // Dependencies go here
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
    }
}
