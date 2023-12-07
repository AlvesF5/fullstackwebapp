package com.br.fullstackapp.poc.application.domain.user

import java.util.*

data class UserDomain(
    val id: String= UUID.randomUUID().toString(),
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
)
