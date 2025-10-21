package core.network.platform

import io.ktor.client.HttpClientConfig

// Declare an expect extension so platform source sets can install their own plugins
expect fun HttpClientConfig<*>.installPlatformPlugins(isDebug: Boolean)
