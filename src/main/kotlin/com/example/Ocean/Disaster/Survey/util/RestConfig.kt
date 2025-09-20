// import org.apache.hc.client5.http.ssl.TrustAllStrategy
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

/*
@Configuration
open class RestConfig {

    @Bean
    open fun restTemplate(): RestTemplate {
        val sslContext = SSLContextBuilder.create()
            .loadTrustMaterial(TrustAllStrategy.INSTANCE) // Use the proper instance for TrustAllStrategy
            .build()

        val requestFactory = HttpComponentsClientHttpRequestFactory(
            org.apache.http.impl.client.HttpClients.custom()
                .setSSLContext(sslContext)
                .build()
        )

        return RestTemplate(requestFactory)
    }
}
*/