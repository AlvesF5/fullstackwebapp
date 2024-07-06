package com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request

import com.google.cloud.firestore.DocumentReference
import java.sql.Timestamp
import java.time.LocalDateTime

data class UpdateUserRequest(
    var id: String?="",
    val displayName: String?="",
    val lastName: String?="",
    val email: String?="",
    val password: String?="",
    val phone: String?="",
    val birthDate: Timestamp?=null,
    val documentNumber: String?="",
    val gender: String?="",
    val updatedAt: Timestamp = Timestamp.valueOf(LocalDateTime.now()),
    var addressId: DocumentReference?=null
)
