package core.network.di

import core.common.utils.isDebug
import core.network.NetworkConfigs
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import sp.bvantur.inspektify.ktor.InspektifyKtor

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
            if (isDebug()) {
                install(InspektifyKtor)
            }
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
            .config {
            }
    }
}
