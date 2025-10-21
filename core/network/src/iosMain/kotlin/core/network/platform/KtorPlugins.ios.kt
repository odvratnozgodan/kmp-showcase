package core.network.platform

import io.ktor.client.*
import sp.bvantur.inspektify.ktor.InspektifyKtor

actual fun HttpClientConfig<*>.installPlatformPlugins(isDebug: Boolean) {
    if (isDebug) {
        // Install Inspektify only on iOS when in debug builds
        install(InspektifyKtor)
    }
}
