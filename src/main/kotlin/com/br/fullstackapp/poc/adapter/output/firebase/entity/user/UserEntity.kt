package com.br.fullstackapp.poc.adapter.output.firebase.entity.user

import com.google.cloud.firestore.DocumentReference

import java.sql.Timestamp
import java.time.LocalDateTime

data class UserEntity(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val phone: String?="",
    val birthDate: Timestamp?=null,
    val documentNumber: String?="",
    val gender: String?="",
    val createdAt: Timestamp = Timestamp.valueOf(LocalDateTime.now()),
    val updatedAt: Timestamp? = null,
    val addressId: DocumentReference?=null
)
