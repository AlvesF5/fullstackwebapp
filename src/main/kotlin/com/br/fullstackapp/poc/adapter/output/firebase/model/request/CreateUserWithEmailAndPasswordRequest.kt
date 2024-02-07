package com.br.fullstackapp.poc.adapter.output.firebase.model.request

data class CreateUserWithEmailAndPasswordRequest(
    val email: String,
    val password: String,
    val displayName: String,
    val lastName: String,
    val returnSecureToken : Boolean = true
)