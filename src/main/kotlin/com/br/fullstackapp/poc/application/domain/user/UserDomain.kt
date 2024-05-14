package com.br.fullstackapp.poc.application.domain.user

import com.google.cloud.firestore.DocumentReference

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
    var addressId: DocumentReference?=null,
)


