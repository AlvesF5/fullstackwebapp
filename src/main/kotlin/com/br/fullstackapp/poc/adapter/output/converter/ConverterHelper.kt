package com.br.fullstackapp.poc.adapter.output.converter

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.CreateUserRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.CreateUserResponse
import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.recurso.RecursoEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.UserEntity
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.CreateUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.recurso.RecursoDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain

fun CreateUserWhiteEmailAndPasswordResponse.toDomain() : UserDomain =
    UserDomain(
        id = localId,
        email = email,
    )

fun RecursoDomain.toEntity() : RecursoEntity =
    RecursoEntity(
        nome = nome,
        chave = chave
    )

fun UserDomain.toEntity() : UserEntity =
    UserEntity(
        id = id,
        firstName = displayName,
        lastName = lastName,
        email = email,
        addressId = addressId,
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
        password = password
    )

fun AddressDomain.toEntity() : AddressEntity =
    AddressEntity(
        cep = cep,
        city = city,
        neighborhood = neighborhood,
        street = street,
        complement = complement,
        state = state
    )

