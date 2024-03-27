package com.example.login_kotlin_att

import android.app.Application
import com.example.login_kotlin_att.model.Role
import com.example.login_kotlin_att.model.User

class App : Application() {
    private lateinit var activeUser: User
    private var users = mutableListOf<User>()

    override fun onCreate() {
        super.onCreate()
        activeUser = User("admin", "1234", Role.ADMIN)
        users.add(activeUser)
    }

    fun login(username : String, password : String) : Boolean {
        for (user in users) {
            if (user.getUsername() == username && user.getPassword() == password) {
                activeUser = user
                return true
            }
        }
        return false
    }

    fun register(username : String, password : String, role : Role) : Boolean {
        for (user in users) {
            if (user.getUsername() == username) {
                return false
            }
        }

        val newUser = User(username, password, role)
        users.add(newUser)
        return true
    }

    fun getActiveUser() : User {
        return activeUser
    }

    fun updateActiveUser(username : String, password : String){
        activeUser.update(username, password)
    }

}