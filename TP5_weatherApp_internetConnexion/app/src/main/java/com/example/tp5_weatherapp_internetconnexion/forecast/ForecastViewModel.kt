package com.example.tp5_weatherapp_internetconnexion.forecast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp5_weatherapp_internetconnexion.RetrofitHelper
import com.example.tp5_weatherapp_internetconnexion.models.forecast.ForecastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel : ViewModel() {
    private val forecastReponse = MutableLiveData<ForecastResponse>()
    var forecast : LiveData<ForecastResponse> = forecastReponse //expose it as immutable livedata


    init {
        changeCity("Japan")
    }


    fun changeCity(ville:String){
        getForecast(ville)
        this.forecast=this.forecastReponse
    }
    private fun getForecast(city : String){
        RetrofitHelper.retrofitService.getForecast(city).enqueue(
            object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if(response.isSuccessful){
                        forecastReponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.d("TAG","failed getting forecast")

                }

            }
        )
    }
}