package com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request

data class UserResetPassRequest(
    val requestType: String,
    val email: String
)
