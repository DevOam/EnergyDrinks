package com.EnergyDrinks

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var choice:AutoCompleteTextView
    lateinit var total:TextView
    lateinit var submit:Button
    lateinit var toolbar:Toolbar
    val redBull = "Red Bull"
    val monster = "Monster"
    val burn = "Burn"
    val c4 = "c4"
    val sanCarlo = "san Carlo"
    val drinksArray = listOf(redBull, monster, burn, c4, sanCarlo)
    val values = mapOf(
        redBull to "18 DH",
        monster to "30 DH",
        burn to "10 DH",
        c4 to "280 DH",
        sanCarlo to "38 DH"
    )
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialisation()
        val adapter = ArrayAdapter(this, R.layout.model_view, drinksArray)
        choice.setAdapter(adapter)

        choice.setOnItemClickListener { adapterView, view, i, l ->
            val recupere = values[choice.text.toString()]
            total.setText(recupere.toString())
        }

//        toolbar.inflateMenu(R.menu.menu_app)
        submit.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_SUBJECT,"price Drinks")
            intent.putExtra(Intent.EXTRA_TEXT,"your drink is ${choice.text} is equal to ${total.text}")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("redalakouas03@gmail.com"))
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }else{
                Toast.makeText(this, "error, handle is not found", Toast.LENGTH_SHORT).show()
            }


        }
//        submit.setOnClickListener {
//            Toast.makeText(this, "submit clicked", Toast.LENGTH_SHORT).show()
//
//        }
    }
    private fun initialisation(){
        choice= findViewById(R.id.choice)
        total = findViewById(R.id.total)
        submit = findViewById(R.id.btn)
        toolbar = findViewById(R.id.tol)
    }
}