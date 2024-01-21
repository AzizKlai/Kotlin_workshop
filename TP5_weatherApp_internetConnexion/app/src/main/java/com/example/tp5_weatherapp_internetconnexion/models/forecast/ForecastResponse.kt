package com.example.tp5_weatherapp_internetconnexion.models.forecast

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DayWeatherDetail>,
    val message: Double
)