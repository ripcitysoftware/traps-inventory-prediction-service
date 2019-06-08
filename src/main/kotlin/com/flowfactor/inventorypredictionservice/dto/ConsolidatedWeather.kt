package com.flowfactor.inventorypredictionservice.dto
import com.fasterxml.jackson.annotation.JsonProperty

data class ConsolidatedWeather(
    @JsonProperty("air_pressure")
    val airPressure: Double, // 1014.36
    @JsonProperty("applicable_date")
    val applicableDate: String, // 2019-06-13
    val created: String, // 2019-06-08T02:59:04.922754Z
    val humidity: Int, // 59
    val id: Long, // 6325900960333824
    @JsonProperty("max_temp")
    val maxTemp: Double, // 18.67
    @JsonProperty("min_temp")
    val minTemp: Double, // 10.715
    val predictability: Int, // 73
    @JsonProperty("the_temp")
    val theTemp: Double, // 17.75
    val visibility: Double, // 9.997862483098704
    @JsonProperty("weather_state_abbr")
    val weatherStateAbbr: String, // s
    @JsonProperty("weather_state_name")
    val weatherStateName: String, // Showers
    @JsonProperty("wind_direction")
    val windDirection: Double, // 192.99999999999997
    @JsonProperty("wind_direction_compass")
    val windDirectionCompass: String, // SSW
    @JsonProperty("wind_speed")
    val windSpeed: Double // 4.986099379623002
)
