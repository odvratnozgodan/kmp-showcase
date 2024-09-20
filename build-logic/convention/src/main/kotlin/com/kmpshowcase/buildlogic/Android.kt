package com.kmpshowcase.buildlogic

import AppVersions
import Configs
import DeepLinkConfigs
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.get

internal fun AppExtension.configureAndroidApplication() {
    compileSdkVersion(Configs.COMPILE_SDK_VERSION)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = AppVersions.APP_ID
        minSdk = Configs.MIN_SDK_VERSION
        targetSdk = Configs.TARGET_SDK_VERSION
        versionCode = AppVersions.APP_VERSION_CODE
        versionName = AppVersions.APP_VERSION_NAME
        configureSharedDefaultConfig()
    }
    packagingOptions {
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

    buildFeatures.buildConfig = true
}

internal fun CommonExtension<*, *, *, *, *, *>.configureAndroidShared() {
    compileSdk = Configs.COMPILE_SDK_VERSION

    defaultConfig {
        configureSharedDefaultConfig()
    }
    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isMinifyEnabled = true
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = Configs.JAVA_COMPATIBILITY_VERSION
        targetCompatibility = Configs.JAVA_COMPATIBILITY_VERSION
    }

    buildFeatures {
        buildConfig = true
    }
}

internal fun DefaultConfig.configureSharedDefaultConfig(){
    minSdk = Configs.MIN_SDK_VERSION
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val addConstant = { constantName: String, constantValue: String ->
        manifestPlaceholders[constantName] = constantValue
        buildConfigField("String", constantName, "\"${constantValue}\"")
    }
    addConstant("DEEP_LINK_HOST", DeepLinkConfigs.DEEP_LINK_HOST)
}
