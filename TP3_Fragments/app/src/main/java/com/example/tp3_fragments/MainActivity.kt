package com.example.tp3_fragments

import FragmentClock
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ActionMode.Callback {
    private  lateinit var actionMode: ActionMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var supf=supportFragmentManager
        supf.beginTransaction()
            .replace(R.id.fragment,FragmentClock(),null)
            .addToBackStack(null)
            .commit()

        btnSetTime.setOnClickListener{ setTime() }
        btnSetTime.setOnLongClickListener{
            actionMode = this@MainActivity.startActionMode(this@MainActivity)!!
            return@setOnLongClickListener true
        }

    }

    fun setTime() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragmentClock = FragmentClock()
        val bundle = Bundle()
        bundle.putBoolean("digitalOK", switchEnableTime.isChecked)
        fragmentClock.arguments = bundle
        transaction.replace(R.id.fragment, fragmentClock)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_switch)
        {
            switchEnableTime.isChecked = !switchEnableTime.isChecked
            setTime()
        }
        return super.onOptionsItemSelected(item)
    }


    //context_mode_menu actionMode.callback
    override fun onCreateActionMode(actionMode: ActionMode, menu: Menu?): Boolean {
        val inflater: MenuInflater = actionMode.menuInflater
        inflater.inflate(R.menu.context_mode_menu, menu)
        return true
    }

    override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menuItem: MenuItem?): Boolean {
        return when (menuItem?.itemId) {
            R.id.action_color -> {
                btnSetTime.setBackgroundColor(generateRandomColor())
                actionMode?.finish()
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(p0: ActionMode?) {
    }



    public fun generateRandomColor(): Int {
        // Generate a random color
        val random = java.util.Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }



}

