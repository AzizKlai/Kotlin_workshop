package com.example.tp5_weatherapp_internetconnexion


import com.example.tp5_weatherapp_internetconnexion.models.forecast.ForecastResponse
import com.example.tp5_weatherapp_internetconnexion.models.weather.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?APPID=17db59488cadcad345211c36304a9266&units=metric")
    fun getWeather(@Query("q") ville: String) : Call<WeatherResponse>

    @GET("forecast/daily?&cnt=16&APPID=17db59488cadcad345211c36304a9266&units=metric")
    fun getForecast(@Query("q") ville: String) : Call<ForecastResponse>
}