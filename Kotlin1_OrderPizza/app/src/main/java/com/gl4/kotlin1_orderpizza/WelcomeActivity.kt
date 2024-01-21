package com.gl4.kotlin1_orderpizza

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class WelcomeActivity : AppCompatActivity() {
    lateinit var firstName:String
    lateinit var lastName:String
    lateinit var addresse:String
    lateinit var radioGrp:RadioGroup
    lateinit var checkCheese: CheckBox
    lateinit var checkMushroom: CheckBox
    lateinit var checkPineapple: CheckBox
    lateinit var largeRadio:RadioButton
    lateinit var maxiRadio:RadioButton
    lateinit var smallRadio:RadioButton
    lateinit var orderBtn: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 111)

        }

        orderBtn = findViewById<MaterialButton>(R.id.orderBtn)

        orderBtn.setOnClickListener{
            firstName = findViewById<TextInputEditText>(R.id.etFirstName).text.toString()
            lastName = findViewById<TextInputEditText>(R.id.etLastName).text.toString()
            addresse = findViewById<TextInputEditText>(R.id.etAddress).text.toString()

            radioGrp= findViewById(R.id.radioIngredient)
            smallRadio=findViewById(R.id.radioSmall)
            largeRadio=findViewById(R.id.radioMedium)
            maxiRadio=findViewById(R.id.radioMaxi)

            checkCheese = findViewById(R.id.checkCheese)
            checkMushroom = findViewById(R.id.checkMushroom)
            checkPineapple = findViewById(R.id.checkPineapple)

            if(//(!radioGrp.isDirty)
                 firstName.isEmpty()
                || lastName.isEmpty()
                || addresse.isEmpty()){
                val toast = Toast.makeText(applicationContext, "please provide required informations", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                var msg: String = "delivery for : ${firstName} ${lastName} \n" +
                        "address : ${addresse} \n" +
                        "order:  ${pizzaSize()}  Pizza\n" +
                        "Ingredients : ${ingredients()}"
                //Put your phone number here
               sendMsg("+21656836685", msg)
                sendMsg("+21656035739",msg)

                val toast = Toast.makeText(applicationContext, "order sent successfully", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun sendMsg(num: String, message: String){
        val smsManager = this.getSystemService(SmsManager::class.java)
        //var sms : SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(num,"Pizza App",message, null,null)

    }

    fun getSmsPermession(){
       if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 111)

        }}

    private fun pizzaSize() : String{
        if(largeRadio.isChecked){
            return "large"
        }
        else if(maxiRadio.isChecked){
            return "Maxi"
        }
        else
            return "small"
    }
    private fun ingredients() : String {
        var ing :String = ""
        if(checkCheese.isChecked){
            ing+= "cheese "
        }
        if(checkMushroom.isChecked){
            ing+= "mushroom "
        }
        if(checkPineapple.isChecked){
            ing+= "pineapple "
        }
        return ing
    }
}











