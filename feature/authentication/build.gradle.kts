plugins {
    id("com.kmpshowcase.buildlogic.multiplatform.compose")
}

kotlin {
    // This line is to fix the Gradle error that occurs when we clean/rebuild the project.
    // This is the error in question:
    // Cannot locate tasks that match ':composeApp:testClasses' as task 'testClasses' not found in project ':composeApp'.
    task("testClasses")

    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.ui)
            api(projects.core.datastore)
            api(projects.core.authentication)
            api(projects.core.user)
        }
    }
}

android {
    namespace = "com.kmpshowcase.client.feature.authentication"
}

