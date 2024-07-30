package com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request

import com.br.fullstackapp.poc.application.domain.user.UserDomain

data class UpdatePasswordRequest(
    val email: String,
    val password: String,
    val newPassword: String
){
    constructor() : this("", "", "")
}

fun UpdatePasswordRequest.toDomain() = UserDomain(
    email = email,
    password = password
)
