package com.kmpshowcase.buildlogic

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun KotlinMultiplatformExtension.configureMultiplatform(libs: LibrariesForLibs) {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Dependency injection
                implementation(project.dependencies.platform(libs.koin.bom))
                api(libs.koin.core)

                // Serialization
                implementation(libs.jetbrains.kotlinx.serialization)

                // ViewModel
                implementation(libs.lifecycle.viewmodel)

                // Navigation
                api(libs.navigation.compose)

                // Logging
                api(libs.logging.kermit)

                // Paging
                implementation(libs.paging.appcache.common)

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
                // Dependencies go here
                api(libs.koin.android)
            }
        }
        val iosMain by creating {
            dependencies {
                // Dependencies go here
            }
        }

        val wasmJsMain by creating {
            dependencies {
                // Dependencies go here
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosTest by creating {
            dependencies {
                // Dependencies go here
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
    }

}
