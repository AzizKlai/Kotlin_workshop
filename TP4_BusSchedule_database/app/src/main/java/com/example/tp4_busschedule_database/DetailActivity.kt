package com.example.tp4_busschedule_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4_busschedule_database.database.BusScheduleApplication
import com.example.tp4_busschedule_database.viewmodels.BusAdapter
import com.example.tp4_busschedule_database.viewmodels.BusScheduleViewModel
import com.example.tp4_busschedule_database.viewmodels.BusScheduleViewModelFactory
import kotlinx.android.synthetic.main.details_bus_activity.*

class DetailActivity : AppCompatActivity() {
    private val busScheduleViewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory((application as BusScheduleApplication).database.getScheduleDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_bus_activity)
        val stopName = intent.getStringExtra("stopName")!!
        InitAdapter(stopName)
    }

    private fun InitAdapter(stopName: String) {
        val busAdapter = BusAdapter(null)
        SetupRecycleView(busAdapter)
        InitList(busAdapter, stopName)
    }
    private fun InitList(busClassAdapter: BusAdapter, stopName: String) {
        busScheduleViewModel
            .scheduleForStopName(stopName)
            .observe(this) {
                busClassAdapter.updateList(it)
            }
    }
    private fun SetupRecycleView(busClassAdapter: BusAdapter) {
        rcBusDetails.layoutManager = LinearLayoutManager(this@DetailActivity)
        rcBusDetails.adapter = busClassAdapter
    }

}