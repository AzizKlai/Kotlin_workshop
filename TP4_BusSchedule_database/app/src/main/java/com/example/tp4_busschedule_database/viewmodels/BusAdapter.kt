package com.example.tp4_busschedule_database.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4_busschedule_database.R
import com.example.tp4_busschedule_database.database.entities.Schedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class BusAdapter(private val onItemClick: ((Schedule) -> Unit)?) : RecyclerView.Adapter<BusAdapter.ViewHolder>() {
    private var dataSet : List<Schedule>  = listOf<Schedule>();

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stopText: TextView
        val timeText: TextView

        init {
            stopText = view.findViewById(R.id.stop)
            timeText = view.findViewById(R.id.time)
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.let { it1 -> it1(dataSet[position]) }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.stopText.text = dataSet[position].stopName
        val formattedDate = getDate(position)
        viewHolder.timeText.text = formattedDate.toString()
    }

    private fun getDate(position: Int): String {
        val timestamp = dataSet[position].arrivalTime.toLong() // Your timestamp value

        val date =
            Date(timestamp * 1000) // Convert timestamp to milliseconds and create Date object

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault() // Set the desired timezone, if needed

        return sdf.format(date)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(dataList: List<Schedule>){
        this.dataSet = dataList;
        notifyDataSetChanged();
        Log.d("datalist",dataList.toString())
    }
    override fun getItemCount() = dataSet.size
}