package com.example.tp5_weatherapp_internetconnexion.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5_weatherapp_internetconnexion.R
import com.example.tp5_weatherapp_internetconnexion.models.forecast.DayWeatherDetail
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ForecastAdapter(private val forecastList: List<DayWeatherDetail>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastItem = forecastList[position]
        holder.bind(forecastItem,position)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.tvDay)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvForcastDescription)
        private val minMaxTempTextView: TextView = itemView.findViewById(R.id.tvTempHum)
        private val imageView: ImageView = itemView.findViewById(R.id.ivImg)

        fun bind(forecastItem: DayWeatherDetail,position: Int) {
           dayTextView.text = dayFormatter(position+2)
           descriptionTextView.text = forecastItem.weather[0].description
           minMaxTempTextView.text = "${forecastItem.temp.min.toString().substring(0,2)}°:${forecastItem.temp.max.toString().substring(0,2)}°"
            val iconUrl="https://openweathermap.org/img/wn/${forecastItem.weather[0].icon}.png"
            Picasso.get().load(iconUrl).into(imageView)
        }

        //@RequiresApi(Build.VERSION_CODES.O)
        fun dayFormatter(daysToAdd: Int):String{
            val currentDate= LocalDate.now()
            val futureDate = currentDate.plusDays(daysToAdd.toLong())
            val formatter = DateTimeFormatter.ofPattern("E", Locale.ENGLISH)
            val formattedDate = futureDate.format(formatter)
            return formattedDate.toString()

        }
    }
}