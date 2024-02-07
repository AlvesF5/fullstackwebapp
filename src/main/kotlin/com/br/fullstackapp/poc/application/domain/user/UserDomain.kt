package com.br.fullstackapp.poc.application.domain.user

data class UserDomain(
    var id: String?="",
    val displayName: String?="",
    val lastName: String?="",
    val email: String?="",
    val password: String?="",
)
