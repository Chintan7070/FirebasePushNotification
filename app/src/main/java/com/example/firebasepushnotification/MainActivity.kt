package com.example.firebasepushnotification

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var firebase: Firebase
    lateinit var firebaseDatabase: FirebaseDatabase
    val TAG = "MainActivity"

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseMessaging.getInstance().subscribeToTopic("news").addOnSuccessListener {
            Toast.makeText(
                applicationContext,
                "Success",
                Toast.LENGTH_LONG
            ).show()
        }


        /*firebaseDatabase = Firebase.database
        insert.setOnClickListener {
           var name =  name.text.toString();
           var surname = surname.text.toString()

        }*/
       /* google.setOnClickListener {

        }*/

    }
}