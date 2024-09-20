import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.kmpshowcase.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    // The line below is necessary to properly handle the Project.libs extension function inside the ProjectExtension.kt
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("application") {
            id = "com.kmpshowcase.buildlogic.application"
            implementationClass = "ApplicationPlugin"
        }
        register("multiplatform") {
            id = "com.kmpshowcase.buildlogic.multiplatform"
            implementationClass = "MultiplatformPlugin"
        }
        register("composeMultiplatform") {
            id = "com.kmpshowcase.buildlogic.multiplatform.compose"
            implementationClass = "ComposeMultiplatformPlugin"
        }
    }
}
