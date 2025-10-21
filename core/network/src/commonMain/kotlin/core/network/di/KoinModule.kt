package core.network.di

import core.common.utils.isDebug
import core.network.NetworkConfigs
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import core.network.platform.installPlatformPlugins

val coreModuleNetwork = module {
    factory(named("baseHttpClient")) {
        HttpClient {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        core.common.logger.Logger.v(message)
                    }
                }
                level = LogLevel.ALL
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
            // Install platform-specific plugins only on Android and iOS
            installPlatformPlugins(isDebug())

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = NetworkConfigs.API_BASE_URL
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single(named("defaultHttpClient")) {
        get<HttpClient>(qualifier = named("baseHttpClient"))
    }
}
