plugins {
    id("com.kmpshowcase.buildlogic.multiplatform.compose")
}

compose.resources {
    publicResClass = true
    generateResClass = always
}

kotlin {
    // This line is to fix the Gradle error that occurs when we clean/rebuild the project.
    // This is the error in question:
    // Cannot locate tasks that match ':composeApp:testClasses' as task 'testClasses' not found in project ':composeApp'.
    task("testClasses")

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.kmpshowcase.client.core.common"
}
