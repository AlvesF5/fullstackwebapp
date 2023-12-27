package com.br.fullstackapp.poc.adapter.input.web.model

data class UserLoginResponse(
    val user: User
){
    data class User(
        val id: String,
        val email: String,
        val name: String,
        val token: String
    )
}
