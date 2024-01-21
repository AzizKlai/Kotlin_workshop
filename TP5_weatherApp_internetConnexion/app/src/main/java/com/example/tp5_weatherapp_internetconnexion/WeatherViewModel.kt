package com.example.tp5_weatherapp_internetconnexion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp5_weatherapp_internetconnexion.models.weather.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel: ViewModel() {
    private val weatherReponse = MutableLiveData<WeatherResponse>()
    var weather : LiveData<WeatherResponse> = weatherReponse

    fun changeCity(ville:String){
        getWeather(ville)
        this.weather=this.weatherReponse
    }

    init {
        changeCity("Japan")
    }
    private fun getWeather(city : String){
        RetrofitHelper.retrofitService.getWeather(city).enqueue(
            object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if(response.isSuccessful){
                        weatherReponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.d("TAG","failed getting weather")

                }

            }
        )
    }
}


