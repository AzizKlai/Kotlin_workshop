package com.example.tp5_weatherapp_internetconnexion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tp5_weatherapp_internetconnexion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        val navHostFragment= supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController=navHostFragment.navController

    }
}
