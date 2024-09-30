plugins {
    id("com.kmpshowcase.buildlogic.multiplatform")
}

kotlin {
    // This line is to fix the Gradle error that occurs when we clean/rebuild the project.
    // This is the error in question:
    // Cannot locate tasks that match ':composeApp:testClasses' as task 'testClasses' not found in project ':composeApp'.
    task("testClasses")

    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.datastore)
            api(libs.bundles.ktor.common)
            api(libs.inspektify)
        }
        androidMain.dependencies {
            api(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }
        wasmJsMain.dependencies {
            api(libs.ktor.client.js)
        }
    }
}

android {
    namespace = "com.kmpshowcase.client.core.network"
}
