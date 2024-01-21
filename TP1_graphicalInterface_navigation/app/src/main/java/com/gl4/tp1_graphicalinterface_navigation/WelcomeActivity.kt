package com.gl4.tp1_graphicalinterface_navigation

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.button.MaterialButton

class WelcomeActivity : AppCompatActivity() {
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val image = findViewById<ImageView>(R.id.image)

        val email= intent.getStringExtra("email")
        val txtWelcome=findViewById<TextView>(R.id.txtWelcome)
        txtWelcome.text="welcome $email"

        // load image
        val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val uriImage = result.data?.data
                image.setImageURI(uriImage)
            }
        }

        val loadImagebtn = findViewById<MaterialButton>(R.id.loadImagebtn)
        loadImagebtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImage.launch(intent)
        }
    }

    /*
    val pickImage= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
     if(result.resultCode== RESULT_OK) { //activity result contract
         val uriImage= result.data?.data   //activity result callback
         image.setImageURI(uriImage)
     }
    }
    fun loadImage(view: View){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImage.launch(intent)
    }*/
}



























