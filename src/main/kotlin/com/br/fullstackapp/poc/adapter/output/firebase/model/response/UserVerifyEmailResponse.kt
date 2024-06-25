package com.br.fullstackapp.poc.adapter.output.firebase.model.response

import com.br.fullstackapp.poc.application.domain.user.UserDomain

data class UserVerifyEmailResponse(
    val email: String
)

fun UserVerifyEmailResponse.toDomain() = UserDomain(
    email = email
)
