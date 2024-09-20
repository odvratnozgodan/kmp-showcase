import com.android.build.gradle.AppExtension
import com.kmpshowcase.buildlogic.configureAndroidApplication
import com.kmpshowcase.buildlogic.configureCompose
import com.kmpshowcase.buildlogic.configureIOSApplication
import com.kmpshowcase.buildlogic.configureMultiplatform
import com.kmpshowcase.buildlogic.configureWebApplication
import com.kmpshowcase.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("org.jmailen.kotlinter")
        }
        val compose = extensions.getByType(ComposeExtension::class.java).dependencies
        extensions.configure<KotlinMultiplatformExtension> {
            configureIOSApplication()
            configureMultiplatform(libs)
            // FIXME: This target was removed because of poor/missing wasmJs support from various dependencies
            // FIXME: (eg. io.coil-kt.coil3.coil-network-ktor2, file handling, etc.)
//            configureWebApplication()
            configureCompose(compose, libs)
        }
        extensions.configure<AppExtension> {
            configureAndroidApplication()
        }

    }
}

