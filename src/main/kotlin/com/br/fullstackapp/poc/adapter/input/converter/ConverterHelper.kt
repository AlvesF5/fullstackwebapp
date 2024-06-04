package com.br.fullstackapp.poc.adapter.input.converter

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UpdateUserRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.application.domain.user.UserDomain

fun UserLoginRequest.toDomain() = UserDomain(
    email = email,
    password = password
)

fun UpdateUserRequest.toDomain() = UserDomain(
    id = id,
    displayName = displayName,
    lastName = lastName,
    email = email,
    password = password,
    phone = phone,
    birthDate = birthDate,
    documentNumber = documentNumber,
    gender = gender,
    updatedAt = updatedAt,
    addressId = addressId
)