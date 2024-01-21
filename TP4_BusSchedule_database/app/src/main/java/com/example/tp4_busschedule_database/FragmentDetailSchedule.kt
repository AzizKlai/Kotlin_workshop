package com.example.tp4_busschedule_database


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4_busschedule_database.database.BusScheduleApplication
import com.example.tp4_busschedule_database.databinding.ActivityMainBinding.inflate
import com.example.tp4_busschedule_database.databinding.FragmentBusScheduleBinding
import com.example.tp4_busschedule_database.databinding.FragmentDetailScheduleBinding
import com.example.tp4_busschedule_database.viewmodels.BusAdapter
import com.example.tp4_busschedule_database.viewmodels.BusScheduleViewModel
import com.example.tp4_busschedule_database.viewmodels.BusScheduleViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.details_bus_activity.*
import kotlinx.android.synthetic.main.item_layout.*

class FragmentDetailSchedule : Fragment() {
    private lateinit var binding: FragmentDetailScheduleBinding

    private val busScheduleViewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory((requireActivity().application as BusScheduleApplication).database.getScheduleDao())
    }
    companion object {
        fun newInstance() = FragmentDetailSchedule()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailScheduleBinding.inflate(layoutInflater)
        val stopName=arguments?.getString("stopName")
        val view=binding.root
        if(stopName!=null)
            initAdapter(stopName)
        return view
    }




private fun initAdapter(stopName: String) {
    val busAdapter = BusAdapter(null)
    initList(busAdapter, stopName)
    setupRecycleView(busAdapter)
}
private fun initList(busClassAdapter: BusAdapter, stopName: String) {
    busScheduleViewModel
        .scheduleForStopName(stopName)
        .observe(this) {
            busClassAdapter.updateList(it)
        }
}
private fun setupRecycleView(busClassAdapter: BusAdapter) {
    binding.recycleView2.layoutManager = LinearLayoutManager(requireContext())
    binding.recycleView2.adapter = busClassAdapter
}

}
