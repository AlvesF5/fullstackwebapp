package com.br.fullstackapp.poc.application.domain.user

import com.br.fullstackapp.poc.adapter.input.converter.formatLocalDate
import com.br.fullstackapp.poc.adapter.input.converter.formatTimestamp
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.GetUserByIdResponse
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserResetPassResponse
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.Timestamp

data class UserDomain(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val password: String?="",
    val phone: String?="",
    val birthDate: Timestamp?=null,
    val documentNumber: String?="",
    val gender: String?="",
    var createdAt: Timestamp? = null,
    var updatedAt: Timestamp? = null,
    val active: Boolean? = false,
    var addressId: DocumentReference?=null,
)

fun UserDomain.toResetPassResponse() = email?.let {
    UserResetPassResponse(
        email = it
    )
}

fun UserDomain.toGetUserByIdResponse() = GetUserByIdResponse(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone,
    birthDate = birthDate?.toDate()?.let { formatLocalDate(it) },
    documentNumber = documentNumber,
    gender = gender,
    createdAt = createdAt?.let { formatTimestamp(it) },
    updatedAt = updatedAt?.let { formatTimestamp(it) },
    active = active,
    addressId = addressId
)



