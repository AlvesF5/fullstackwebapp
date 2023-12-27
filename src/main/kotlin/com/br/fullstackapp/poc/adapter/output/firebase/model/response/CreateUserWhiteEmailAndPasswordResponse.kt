package com.br.fullstackapp.poc.adapter.output.firebase.model.response

data class CreateUserWhiteEmailAndPasswordResponse(
    val localId: String,
    val email: String,
    val idToken: String,
    val kind: String,
    val refreshToken: String,
    val expiresIn: String
)
