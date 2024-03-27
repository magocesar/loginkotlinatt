package com.example.login_kotlin_att

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var username_input : EditText
    lateinit var password_input : EditText
    lateinit var login_button : Button
    lateinit var register_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username_input = findViewById(R.id.username_input)
        password_input = findViewById(R.id.password_input)
        login_button = findViewById(R.id.login_button)
        register_button = findViewById(R.id.register_button)
        val app = application as App

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Pass
            }

            override fun afterTextChanged(s: Editable?) {
                login_button.isEnabled = username_input.text.isNotEmpty() && password_input.text.isNotEmpty()
            }

        }

        username_input.addTextChangedListener(textWatcher)
        password_input.addTextChangedListener(textWatcher)

        login_button.setOnClickListener(){
            val username = username_input.text.toString()
            val password = password_input.text.toString()

            if(app.login(username, password)){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Login Successful")
                builder.setMessage("Welcome!")
                builder.setPositiveButton("OK", null)
                builder.show()
                val intent = Intent(this, LoggedActivity::class.java)
                startActivity(intent)
            }
        }

        register_button.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}