package com.kmpshowcase.buildlogic

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
internal fun KotlinMultiplatformExtension.configureWebApplication() {
    js(IR){
        browser{
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                        add(project.projectDir.path + "/commonMain/")
                        add(project.projectDir.path + "/wasmJsMain/")
                    }
                }
            }
            binaries.executable()
        }
    }
/*
    wasmJs {
        outputModuleName.set("composeApp")
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                        add(project.projectDir.path + "/commonMain/")
                        add(project.projectDir.path + "/wasmJsMain/")
                    }
                }
            }
        }
        binaries.executable()
    }
*/
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
internal fun KotlinMultiplatformExtension.configureWebLibrary() {
    js {
        outputModuleName.set(project.name)
        browser()
    }
/*
    wasmJs {
        outputModuleName.set(project.name)
        browser()
    }
*/
}
