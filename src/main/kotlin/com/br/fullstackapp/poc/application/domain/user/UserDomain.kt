package com.br.fullstackapp.poc.application.domain.user

import com.br.fullstackapp.poc.adapter.input.converter.formatTimestamp
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.GetUserByIdResponse
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserResetPassResponse
import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity
import com.br.fullstackapp.poc.application.port.output.address.AddressRepositoryPort
import com.google.cloud.Timestamp
import com.google.cloud.firestore.DocumentReference

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

fun UserDomain.toGetUserByIdResponse(addressRepositoryPort: AddressRepositoryPort) = GetUserByIdResponse(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone,
    birthDate = birthDate?.let { formatTimestamp(it) },
    documentNumber = documentNumber,
    gender = gender,
    createdAt = createdAt?.let { formatTimestamp(it) },
    updatedAt = updatedAt?.let { formatTimestamp(it) },
    active = active,
    address = addressId?.let { getUserAddress(it.id, addressRepositoryPort) },
    addressString = addressId?.let { getUserAddress(it.id, addressRepositoryPort).toString() }
)

fun getUserAddress(addressId: String, addressRepositoryPort: AddressRepositoryPort) : AddressEntity?{
    return addressRepositoryPort.getAddressById(addressId)
}



