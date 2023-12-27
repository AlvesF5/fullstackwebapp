package com.br.fullstackapp.poc.adapter.output.firebase.model.request

data class LoginUserWithEmailAndPasswordRequest(
    val email: String,
    val password: String,
    val returnSecureToken : Boolean=true
)
