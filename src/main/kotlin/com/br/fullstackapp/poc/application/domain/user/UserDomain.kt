package com.br.fullstackapp.poc.application.domain.user

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserResetPassResponse
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.cloud.firestore.DocumentReference
import java.util.Date;

data class UserDomain(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val password: String?="",
    val phone: String?="",
    val birthDate: Date? = null,
    val documentNumber: String?="",
    val gender: String?="",
    val updatedAt: Date? = null,
    val isActive: Boolean? = false,
    var addressId: DocumentReference?=null,
)

fun UserDomain.toResetPassResponse() = email?.let {
    UserResetPassResponse(
        email = it
    )
}



