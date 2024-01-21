package com.gl4.tp1_graphicalinterface_navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var email: String
    lateinit var password : String
    lateinit var connectBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectBtn=findViewById(R.id.btnConnect)
        connectBtn.setOnClickListener{
            email=findViewById<TextInputEditText>(R.id.email).text.toString()
            password=findViewById<TextInputEditText>(R.id.password).text.toString()
            if(email=="aziz" && password=="00"){
                val toast=Toast.makeText(applicationContext, "welcome", Toast.LENGTH_LONG)
                toast.show()

                val intent = Intent(this,WelcomeActivity::class.java)
                intent.putExtra("email",email)
                startActivity(intent)

            }
            else{
                val toast=Toast.makeText(applicationContext, "verify your data", Toast.LENGTH_LONG)
                toast.show()
            }



        }

    }
}