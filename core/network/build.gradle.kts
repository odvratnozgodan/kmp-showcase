plugins {
    id("com.kmpshowcase.buildlogic.multiplatform")
}

kotlin {
    // This line is to fix the Gradle error that occurs when we clean/rebuild the project.
    // This is the error in question:
    // Cannot locate tasks that match ':composeApp:testClasses' as task 'testClasses' not found in project ':composeApp'.
    tasks.register("testClasses")

    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.datastore)
            api(libs.bundles.ktor.common)
//            api(libs.inspektify)
        }
        androidMain.dependencies {
            api(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }
        // FIXME: This target was removed because of poor/missing wasmJs support from various dependencies
        // FIXME: (eg. io.coil-kt.coil3.coil-network-ktor2, file handling, etc.)
        /*wasmJsMain.dependencies {
            api(libs.ktor.client.js)
        }*/
    }
}

android {
    namespace = "com.kmpshowcase.client.core.network"
}
