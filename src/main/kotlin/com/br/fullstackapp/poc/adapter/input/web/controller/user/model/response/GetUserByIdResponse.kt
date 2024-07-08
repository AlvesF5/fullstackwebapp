package com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response

import com.google.cloud.firestore.DocumentReference

data class GetUserByIdResponse(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val phone: String?="",
    var birthDate: String?="",
    val documentNumber: String?="",
    val gender: String?="",
    var createdAt: String? = "",
    var updatedAt: String? = "",
    val active: Boolean? = false,
    val addressId: DocumentReference?=null
)
