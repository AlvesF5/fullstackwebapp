package com.br.fullstackapp.poc.adapter.output.firebase.model.request

data class UserResetPassRequest(
    val requestType: String,
    val email: String
)
