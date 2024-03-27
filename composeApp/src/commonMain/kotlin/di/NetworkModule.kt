package di

import com.jpmobilelab.kmp.newsapp.BuildKonfig
import domain.interactors.GetHeadlinesArticlesInteractor
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import source.ApiService

fun networkModule() = module {
    single {
        HttpClient {
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildKonfig.API_BASE_HOST
                    parameters.append("limit", "5")
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

    single {
        ApiService(
            httpClient = get()
        )
    }
    single {
        GetHeadlinesArticlesInteractor(
            apiService = get()
        )
    }
}