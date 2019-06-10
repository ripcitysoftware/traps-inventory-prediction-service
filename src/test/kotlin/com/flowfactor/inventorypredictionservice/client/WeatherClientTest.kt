package com.flowfactor.inventorypredictionservice.client

import com.flowfactor.inventorypredictionservice.config.RestTemplateConfig
import io.specto.hoverfly.junit.core.Hoverfly
import io.specto.hoverfly.junit5.HoverflyExtension
import io.specto.hoverfly.junit5.api.HoverflyConfig
import io.specto.hoverfly.junit5.api.HoverflySimulate
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.RestTemplate
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import java.net.URI


//@HoverflyCapture(path = "src/test/resources/hoverfly",
//        filename = "captured-simulation.json",
//        config = HoverflyConfig(captureAllHeaders = true, proxyLocalHost = true))
@HoverflySimulate(source = HoverflySimulate.Source(value = "src/test/resources/hoverfly/captured-simulation.json",
        type = HoverflySimulate.SourceType.FILE),
        config = HoverflyConfig(captureAllHeaders = true, proxyLocalHost = true),
        enableAutoCapture = true)//, disableTlsVerification = true, statefulCapture = true
@ExtendWith(SpringExtension::class, HoverflyExtension::class)
@SpringBootTest(classes = arrayOf(RestTemplateConfig::class, WeatherClient::class))
class WeatherClientTest {
    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    @Autowired
    lateinit var restTemplate: RestTemplate
    @Autowired
    lateinit var weatherClient: WeatherClient

    @Test
    fun captureWeather() {
        val weatherResponseDto = weatherClient.getWeatherByLocation("london")
        assertNotNull(weatherResponseDto)
    }

    @Test
    fun captureLocation() {
        val locationResponseDto = weatherClient.getLocation("london")
        assertNotNull(locationResponseDto)
        assertEquals(locationResponseDto, "[{\"title\":\"London\",\"location_type\":\"City\",\"woeid\":44418,\"latt_long\":\"51.506321,-0.12714\"}]")

    }

}


