package com.br.fullstackapp.poc.application.domain.user

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserResetPassResponse
import com.google.cloud.firestore.DocumentReference
import java.sql.Timestamp

data class UserDomain(
    var id: String?="",
    val displayName: String?="",
    val lastName: String?="",
    val email: String?="",
    val password: String?="",
    val phone: String?="",
    val birthDate: String?="",
    val documentNumber: String?="",
    val gender: String?="",
    val updatedAt: Timestamp? = null,
    val isActive: Boolean? = false,
    var addressId: DocumentReference?=null,
)

fun UserDomain.toResetPassResponse() = email?.let {
    UserResetPassResponse(
        email = it
    )
}



