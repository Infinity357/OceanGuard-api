package com.example.Ocean.Disaster.Survey.services

import com.example.Ocean.Disaster.Survey.database.models.RedditListing
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.SupportedCipherSuiteFilter
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Service
import kotlin.jvm.java

import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import javax.net.ssl.SSLException

/*
@Service
class RedditService {

    private val webClient = WebClient.builder()
        .baseUrl("https://oauth.reddit.com")
        .defaultHeader("User-Agent", "ocean-hazard-bot/0.1")
        .build()

    fun getPosts(subreddit: String, token: String): RedditListing? {
        return webClient.get()
            .uri("/r/$subreddit/new.json?limit=25")
            .header("Authorization", "Bearer $token")
            .retrieve()
            .bodyToMono(RedditListing::class.java)
            .block()
    }
}
*/


/*
@Service
class RedditService {

    private val webClient = WebClient.builder()
        .baseUrl("https://www.reddit.com")
        .defaultHeader("User-Agent", "ocean-hazard-bot/0.1")
        .build()

    fun getPosts(subreddit: String): RedditListing? {
        return webClient.get()
            .uri("/r/$subreddit.json?limit=25")
            .retrieve()
            .bodyToMono(RedditListing::class.java)
            .block()
    }
}
*/


@Service
class RedditService {

    private val webClient: WebClient

    init {
        val sslContext = try {
            SslContextBuilder.forClient()
                .protocols("TLSv1.2") // force TLS 1.2
                .ciphers(null, SupportedCipherSuiteFilter.INSTANCE) // let Netty pick best
                .build()
        } catch (e: SSLException) {
            throw RuntimeException("Failed to build SSL context", e)
        }

        val httpClient = HttpClient.create().secure { spec ->
            spec.sslContext(sslContext)
        }

        webClient = WebClient.builder()
            .baseUrl("https://www.reddit.com")
            .defaultHeader("User-Agent", "ocean-hazard-app/1.0 (by u/yourusername)")
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    fun getPosts(subreddit: String): RedditListing? {
        return webClient.get()
            .uri("/r/$subreddit.json?limit=25")
            .retrieve()
            .bodyToMono(RedditListing::class.java)
            .block()
    }
}

