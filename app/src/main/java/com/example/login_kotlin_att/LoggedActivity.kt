package com.example.login_kotlin_att

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoggedActivity : AppCompatActivity() {

    lateinit var text : TextView
    lateinit var username_edit : EditText
    lateinit var password_edit : EditText
    lateinit var att_button : Button
    lateinit var return_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logged_activity)

        username_edit = findViewById(R.id.username_inputt)
        password_edit = findViewById(R.id.password_inputt)
        att_button = findViewById(R.id.att_button)
        return_button = findViewById(R.id.ret_button)
        text = findViewById(R.id.inicial_text)
        val app = application as App

        return_button.setOnClickListener(){
            finish()
        }

        text.setText("Welcome " + app.getActiveUser().getUsername() + "Role: " + app.getActiveUser().getRole())

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Pass
            }

            override fun afterTextChanged(s: Editable?) {
                att_button.isEnabled = username_edit.text.isNotEmpty() && password_edit.text.isNotEmpty()
            }
        }

        username_edit.addTextChangedListener(textWatcher)
        password_edit.addTextChangedListener(textWatcher)
        att_button.setOnClickListener(){
            val username = username_edit.text.toString()
            val password = password_edit.text.toString()
            app.updateActiveUser(username, password)
            text.text = "Welcome " + app.getActiveUser().getUsername() + "Role: " + app.getActiveUser().getRole()
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Success")
            builder.setMessage("User updated")
            builder.setPositiveButton("Ok", null)
            builder.show()
        }
    }
}