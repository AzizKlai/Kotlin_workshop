package com.example.tp4_busschedule_database


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4_busschedule_database.database.BusScheduleApplication
import com.example.tp4_busschedule_database.databinding.FragmentBusScheduleBinding
import com.example.tp4_busschedule_database.databinding.FragmentDetailScheduleBinding
import com.example.tp4_busschedule_database.viewmodels.BusAdapter
import com.example.tp4_busschedule_database.viewmodels.BusScheduleViewModel
import com.example.tp4_busschedule_database.viewmodels.BusScheduleViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class FragmentBusSchedule : Fragment() {
    private lateinit var binding: FragmentBusScheduleBinding

   //input
    private val busScheduleViewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory((requireActivity().application as BusScheduleApplication).database.getScheduleDao())
    }

    companion object {
        fun newInstance() = FragmentBusSchedule()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("created","busschedule")
        binding = FragmentBusScheduleBinding.inflate(layoutInflater)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(view)

    }




    private fun initAdapter(view:View) {
        val busAdapter = BusAdapter { schedule ->
          /*  var intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("stopName", schedule.stopName)
            startActivity(intent);*/

            val bundle=Bundle()
            bundle.putString("stopName", schedule.stopName)
            findNavController().navigate(R.id.action_fragmentBusSchedule3_to_fragmentDetailSchedule2,bundle)
        }

        initList(busAdapter,view)
        setupRecycleView(busAdapter,view)
    }
    private fun initList(busAdapter: BusAdapter,view:View) {
        /*var list = listOf<Schedule>()
        Thread {
            // Background work
           list = busScheduleViewModel.fullSchedule()
            // Post the result to the main thread using aHandler
            Handler(Looper.getMainLooper()).post {
               // ajouter la fonction updateList dans votre  adaptateur
                busAdapter.updateList(list)
            }
        }.start()*/
        Log.d("datafirstfirst","it.toString()")
        busScheduleViewModel
            .fullSchedule()
            .observe(this) {
                Log.d("datafirst",it.toString())
                busAdapter.updateList(it)
            }
    }
    private fun setupRecycleView(busAdapter: BusAdapter,view:View) {
        binding.recycleView1.layoutManager = LinearLayoutManager(view.context)
        binding.recycleView1.adapter = busAdapter
    }

}
