plugins {
    id("com.kmpshowcase.buildlogic.application")
}

kotlin {
    // This line is to fix the Gradle error that occurs when we clean/rebuild the project.
    // This is the error in question:
    // Cannot locate tasks that match ':composeApp:testClasses' as task 'testClasses' not found in project ':composeApp'.
    task("testClasses")

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.ui)
            implementation(projects.core.datastore)
            implementation(projects.core.network)
            implementation(projects.core.authentication)
            implementation(projects.core.user)
            implementation(projects.feature.authentication)
            implementation(projects.feature.home)
        }
    }
}

android {
    namespace = "com.kmpshowcase.client"

    buildFeatures {
        compose = true
    }
}
