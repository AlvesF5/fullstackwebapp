package com.br.fullstackapp.poc.adapter.output.converter

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.CreateUserRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.CreateUserResponse
import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.UserEntity
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.CreateUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun CreateUserWhiteEmailAndPasswordResponse.toDomain() : UserDomain =
    UserDomain(
        id = localId,
        email = email,
    )

fun UserDomain.toEntity() : UserEntity =
    UserEntity(
        id = id,
        firstName = displayName,
        lastName = lastName,
        email = email,
        addressId = addressId,
        documentNumber = documentNumber,
        birthDate = convertToTimestamp(birthDate!!),
        gender = gender,
        phone = phone,
        updatedAt = updatedAt
    )

fun UserDomain.toCreateUserResponse() : CreateUserResponse =
    CreateUserResponse(
        id = id,
        firstName = displayName,
        email = email,
    )

fun CreateUserRequest.toDomain() : UserDomain =
    UserDomain(
        displayName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        phone = phone,
        birthDate = birthDate,
        documentNumber = documentNumber,
        gender = gender
    )

fun AddressDomain.toEntity() : AddressEntity =
    AddressEntity(
        cep = cep,
        city = city,
        neighborhood = neighborhood,
        street = street,
        number = number,
        complement = complement,
        state = state
    )

@Throws(ParseException::class)
fun convertToTimestamp(dateString: String?): Timestamp? {
    val format = SimpleDateFormat("yyyy-MM-dd")
    val parsedDate: Date = format.parse(dateString)
    return Timestamp(parsedDate.getTime())
}