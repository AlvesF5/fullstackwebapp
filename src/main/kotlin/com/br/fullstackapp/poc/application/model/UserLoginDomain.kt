package com.br.fullstackapp.poc.application.model

data class UserLoginDomain(
    val user: User
){
    data class User(
        val id: String?,
        val email: String?,
        val name: String?,
        val token: String?,
        val refreshToken: String?
    )
}
