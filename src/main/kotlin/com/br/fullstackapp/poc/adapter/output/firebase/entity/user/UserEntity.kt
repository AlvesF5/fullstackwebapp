package com.br.fullstackapp.poc.adapter.output.firebase.entity.user

import com.google.cloud.firestore.DocumentReference

data class UserEntity(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val phone: String?="",
    val birthDate: String?="",
    val documentNumber: String?="",
    val gender: String?="",
    val addressId: DocumentReference?=null
)
