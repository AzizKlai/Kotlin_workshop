package com.example.tp4_busschedule_database.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tp4_busschedule_database.database.dao.ScheduleDAO
import com.example.tp4_busschedule_database.database.entities.Schedule

class BusScheduleViewModel(private val scheduleDAO: ScheduleDAO): ViewModel() {
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDAO.getAll()

    fun scheduleForStopName(name: String): LiveData<List<Schedule>> = scheduleDAO.getByStopName(name)
}