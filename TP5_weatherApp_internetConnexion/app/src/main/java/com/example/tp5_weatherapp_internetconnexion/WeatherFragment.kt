package com.example.tp5_weatherapp_internetconnexion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.tp5_weatherapp_internetconnexion.databinding.FragmentWeatherBinding
import com.example.tp5_weatherapp_internetconnexion.models.weather.WeatherResponse
import com.squareup.picasso.Picasso


class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    lateinit var viewModel: WeatherViewModel
    var currentCity:String="Japan"
    companion object {
        fun newInstance() = WeatherFragment()
    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            viewModel=ViewModelProvider(this)[WeatherViewModel::class.java]
            binding = FragmentWeatherBinding.inflate(layoutInflater)

            // addlistener to view next 16 days forecast
            binding.btnForecast.setOnClickListener{
                val bundle=Bundle()
                bundle.putString("city",currentCity)
                findNavController().navigate(R.id.action_weatherFragment_to_forecastFragment,bundle)
            }
            return binding.root
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Use the ViewModel
            viewModel.weather.observe(viewLifecycleOwner) {
                if (it != null)
                    changeWeather(it)
            }

            // Spinner setup
            val spinnerCity = binding.spinnerCity
            val cities = listOf("Tunis", "London", "Japan")

            // Adapter setup moved to onViewCreated
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, cities)
            spinnerCity.adapter = adapter

            // Spinner item selection listener
            spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    currentCity=cities[position]
                    viewModel.changeCity(cities[position])
                    if (viewModel.weather.value != null) {
                        changeWeather(viewModel.weather.value!!)
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                }
            }
        }





        fun changeWeather(weather: WeatherResponse){
            Log.d("TAg","here done")
            Log.d("TAg","here done")
            Log.d("TAg",weather.toString()+weather.name.toString())
            binding.tvMain.text=weather.name
            binding.tvDegree.text=("${weather.main.temp.toString()}°C")
            binding.tvDescription.text=weather.weather[0].description
            binding.tvHumidityPressure.text="H : ${weather.main.humidity}, P : ${weather.main.pressure}"
            val imgUrl="https://openweathermap.org/img/wn/${weather.weather[0].icon}.png    "
            Picasso.get().load(imgUrl).into(binding.ivImg)

        }



}
















