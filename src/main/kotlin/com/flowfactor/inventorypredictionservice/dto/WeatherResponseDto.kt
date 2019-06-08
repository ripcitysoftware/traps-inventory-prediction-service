package com.flowfactor.inventorypredictionservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherResponseDto(
    @JsonProperty("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>,
    @JsonProperty("latt_long")
    val lattLong: String, // 51.506321,-0.12714
    @JsonProperty("location_type")
    val locationType: String, // City
    val parent: Parent,
    val sources: List<Source>,
    @JsonProperty("sun_rise")
    val sunRise: String, // 2019-06-08T04:44:50.211130+01:00
    @JsonProperty("sun_set")
    val sunSet: String, // 2019-06-08T21:14:39.981997+01:00
    val time: String, // 2019-06-08T05:06:28.793538+01:00
    val timezone: String, // Europe/London
    @JsonProperty("timezone_name")
    val timezoneName: String, // LMT
    val title: String, // London
    val woeid: Int // 44418
)
