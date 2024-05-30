package com.br.fullstackapp.poc.adapter.output.firebase.model.request

data class UserVerifyEmailRequest(
    val requestType: String,
    val idToken: String
)
