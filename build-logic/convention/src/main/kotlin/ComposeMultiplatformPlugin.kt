import com.android.build.gradle.LibraryExtension
import com.kmpshowcase.buildlogic.configureAndroidShared
import com.kmpshowcase.buildlogic.configureCompose
import com.kmpshowcase.buildlogic.configureIOSLibrary
import com.kmpshowcase.buildlogic.configureMultiplatform
import com.kmpshowcase.buildlogic.configureWebLibrary
import com.kmpshowcase.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("org.jetbrains.kotlin.plugin.serialization")
            apply("org.jmailen.kotlinter")
        }
        val compose = extensions.getByType(ComposeExtension::class.java).dependencies
        extensions.configure<KotlinMultiplatformExtension> {
            configureIOSLibrary()
            configureMultiplatform(libs)
            // FIXME: This target was removed because of poor/missing wasmJs support from various dependencies
            // FIXME: (eg. io.coil-kt.coil3.coil-network-ktor2, file handling, etc.)
//            configureWebLibrary()
            configureCompose(compose, libs)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidShared()
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureCompose(compose, libs)
        }
    }
}

