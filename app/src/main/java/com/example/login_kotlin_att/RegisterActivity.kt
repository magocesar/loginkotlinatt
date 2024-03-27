package com.example.login_kotlin_att

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.login_kotlin_att.model.Role

class RegisterActivity : AppCompatActivity() {

    lateinit var user_input : EditText
    lateinit var password_input : EditText
    lateinit var confirm_password_input : EditText
    lateinit var spinner : Spinner
    lateinit var register_button : Button
    lateinit var login_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        user_input = findViewById(R.id.user_input)
        password_input = findViewById(R.id.password_input)
        confirm_password_input = findViewById(R.id.password_confirm_input)
        spinner = findViewById(R.id.role_spinner)
        register_button = findViewById(R.id.register_button)
        login_button = findViewById(R.id.return_button)
        val app = application as App

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Pass
            }

            override fun afterTextChanged(s: Editable?) {
                register_button.isEnabled = user_input.text.isNotEmpty() && password_input.text.isNotEmpty() && confirm_password_input.text.isNotEmpty() && password_input.text.toString() == confirm_password_input.text.toString()
            }

        }

        user_input.addTextChangedListener(textWatcher)
        password_input.addTextChangedListener(textWatcher)
        confirm_password_input.addTextChangedListener(textWatcher)


        val roles = Role.entries.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        login_button.setOnClickListener(){
            finish()
        }

        register_button.setOnClickListener(){
            val username = user_input.text.toString()
            val password = password_input.text.toString()
            val role = spinner.selectedItem as Role
            val builder = android.app.AlertDialog.Builder(this)

            if(app.register(username, password, role)){
                builder.setTitle("Registration Successful")
                builder.setMessage("Welcome!")
                builder.setPositiveButton("OK", null)
                builder.show()
                finish()
            }else{
                builder.setTitle("Registration Failed")
                builder.setMessage("Username already exists")
                builder.setPositiveButton("OK", null)
                builder.show()
            }
        }


    }




}