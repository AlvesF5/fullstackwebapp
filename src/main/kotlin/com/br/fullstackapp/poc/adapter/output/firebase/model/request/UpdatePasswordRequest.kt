package com.br.fullstackapp.poc.adapter.output.firebase.model.request

data class UpdatePasswordRequest(
    val idToken: String,
    val password: String,
    val returnSecureToken: Boolean = true
)