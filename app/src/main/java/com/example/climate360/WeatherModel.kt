package com.example.climate360
data class WeatherModel(
    val cityName: String,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val conditionIconUrl: String,
    val locationName: String,
    val locationRegion: String,
    val isDay: String
)
