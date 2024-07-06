package com.br.fullstackapp.poc.adapter.output.firebase.entity.user

import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.google.cloud.firestore.DocumentReference
import java.sql.Timestamp
import java.util.*

data class UserEntity(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val phone: String?="",
    var birthDate: Timestamp?=null,
    val documentNumber: String?="",
    val gender: String?="",
    var createdAt: Timestamp? = null,
    var updatedAt: Timestamp? = null,
    val active: Boolean? = false,
    val addressId: DocumentReference?=null
){
    fun insertBirthDate(date: Date) {
        this.birthDate = Timestamp(date.time)
    }

    fun insertCreatedAtDate(date: Date) {
        this.createdAt = Timestamp(date.time)
    }

    fun insertUpdatedAt(date: Date){
        this.updatedAt = Timestamp(date.time)
    }

}

fun UserEntity.toDomain() = UserDomain(
    id = id,
    firstName= firstName,
    lastName= lastName,
    email = email,
    phone = phone,
    birthDate = birthDate,
    documentNumber = documentNumber,
    gender = gender,
    createdAt = createdAt,
    updatedAt = updatedAt,
    active = active,
    addressId = addressId
)
