package com.example.login_kotlin_att.model

class User(private var username: String, private var password: String, private var role: Role) {
    fun getUsername(): String {
        return username
    }

    fun getPassword(): String {
        return password
    }

    fun getRole(): Role {
        return role
    }

    fun update(username: String, password: String){
        this.username = username
        this.password = password
    }


}