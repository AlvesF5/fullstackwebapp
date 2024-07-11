package com.br.fullstackapp.poc.adapter.input.converter

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UpdateUserRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.google.cloud.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun UserLoginRequest.toDomain() = UserDomain(
    email = email,
    password = password
)

fun UpdateUserRequest.toDomain() = UserDomain(
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    birthDate = birthDate,
    documentNumber = documentNumber,
    gender = gender,
    updatedAt = updatedAt,
    addressId = address?.id
)

fun formatLocalDate(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(date)
}

fun formatTimestamp(timestamp: Timestamp): String {
    val date = timestamp.toDate()

    val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss 'UTC'XXX", Locale("pt", "BR"))

    dateFormat.timeZone = TimeZone.getTimeZone("America/Sao_Paulo")

    return dateFormat.format(date)
}
