package com.flowfactor.inventorypredictionservice.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.impl.client.HttpClients


@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate() : RestTemplate {
        val httpClient = HttpClients.custom().setSSLHostnameVerifier(NoopHostnameVerifier()).build()

        val requestFactory = HttpComponentsClientHttpRequestFactory()
        requestFactory.httpClient = httpClient

        val restTemplate = RestTemplate()

        return restTemplate
    }
}