package com.example.tp5_weatherapp_internetconnexion.forecast

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp5_weatherapp_internetconnexion.databinding.FragmentForecastBinding
import com.example.tp5_weatherapp_internetconnexion.models.forecast.ForecastResponse
import com.squareup.picasso.Picasso

class ForecastFragment : Fragment() {
    lateinit var binding: FragmentForecastBinding
    private lateinit var viewModel:ForecastViewModel
    companion object {
        fun newInstance() = ForecastFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel=ViewModelProvider(this)[ForecastViewModel::class.java]
        binding= FragmentForecastBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city=arguments?.getString("city")
        if(city!=null)
            viewModel.changeCity(city)
        viewModel.forecast.observe(viewLifecycleOwner){
            if (it != null) {
                setForecast(it)
                val newlist=it.list.drop(1) //eleminate the first one : tomorrow
                binding.rvForecast.layoutManager = LinearLayoutManager(view.context)
                binding.rvForecast.adapter= ForecastAdapter(newlist)
            }
        }
    }
    fun setForecast(forecast: ForecastResponse){
        binding.tvDegree.text=("${forecast.list[0].temp.day.toString().substring(0,2)}°:")
        binding.tvDescription.text=forecast.list[0].weather[0].description
        val imgUrl="https://openweathermap.org/img/wn/${forecast.list[0].weather[0].icon}.png"
        Picasso.get().load(imgUrl).into(binding.ivImg)

    }

}