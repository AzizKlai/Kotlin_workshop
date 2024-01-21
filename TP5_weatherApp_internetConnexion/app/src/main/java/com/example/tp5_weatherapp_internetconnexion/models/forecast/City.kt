package com.example.tp5_weatherapp_internetconnexion.models.forecast

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)