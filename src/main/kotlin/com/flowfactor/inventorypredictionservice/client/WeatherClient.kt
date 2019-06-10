package com.flowfactor.inventorypredictionservice.client

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.flowfactor.inventorypredictionservice.config.RestTemplateConfig
import com.flowfactor.inventorypredictionservice.dto.LocationDTOs
import com.flowfactor.inventorypredictionservice.dto.LocationResponseDto
import com.flowfactor.inventorypredictionservice.dto.WeatherResponseDto
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.net.URI

@Service
class WeatherClient(
    @Autowired
    private val restTemplate: RestTemplate,
    @Autowired
    private val restTemplateConfig: RestTemplateConfig) {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    fun getLocation(place: String) : String {
        logger.info("Getting location information from metaweather")

        val url = "https://www.metaweather.com/api/location/search/?query="+place //london
        val getBookingResponse = restTemplate.exchange(URI.create(url), HttpMethod.GET, null, String::class.java)
        return getBookingResponse.body!!

    }
    fun getWeatherByLocation(location: String) :WeatherResponseDto {
        logger.info("Getting weather from metaweather")

        val url = "https://www.metaweather.com/api/location/44418" //london
        val getBookingResponse = restTemplate.getForEntity(URI.create(url), WeatherResponseDto::class.java)
        return getBookingResponse.body!!
    }
}

inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}