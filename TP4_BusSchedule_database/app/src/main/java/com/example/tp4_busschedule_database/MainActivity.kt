package com.example.tp4_busschedule_database

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tp4_busschedule_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
       try {
           super.onCreate(savedInstanceState)
           binding = ActivityMainBinding.inflate(layoutInflater)
           val view = binding.root

           val navHostFragment = supportFragmentManager
               .findFragmentById(R.id.fragment) as NavHostFragment
           navController = navHostFragment.navController

           Log.d("navcontroller", "done")
           setContentView(view)
       }catch (e:Exception ) {
            Log.d( "onCreateView", e.toString());
            throw e;
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        try{ Log.d( "onCreateView", "done")

    } catch (e:Exception ) {
        Log.d( "onCreateView", e.toString());
        throw e;
    }
        return super.onCreateView(name, context, attrs)
    }

}