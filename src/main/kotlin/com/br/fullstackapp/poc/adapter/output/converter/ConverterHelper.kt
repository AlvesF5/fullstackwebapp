package com.br.fullstackapp.poc.adapter.output.converter

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.CreateUserRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.CreateUserResponse
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserLoginResponse
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserSendVerificationEmailResponse
import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.UserEntity
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.CreateUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.UserGetAccountInfoResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.UserInfo
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.UserResetPassResponse
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.model.UserLoginDomain
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
        firstName = firstName,
        lastName = lastName,
        email = email,
        addressId = addressId,
        documentNumber = documentNumber,
        birthDate = birthDate,
        gender = gender,
        phone = phone,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

fun UserDomain.toCreateUserResponse() : CreateUserResponse =
    CreateUserResponse(
        id = id,
        firstName = firstName,
        email = email,
    )

fun UserDomain.toUserAccountInformationResp() = UserGetAccountInfoResponse(
    users = listOf(UserInfo(email= email, emailVerified = active))
)

fun UserDomain.toSendVerificationEmailResponse() = UserSendVerificationEmailResponse(
    email = email!!
)
fun CreateUserRequest.toDomain() : UserDomain =
    UserDomain(
        firstName = firstName,
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

fun UserLoginResponse.toUserLoginDomain() = UserLoginDomain(
    user = UserLoginDomain.User(
        id = user.id,
        email = user.email,
        name = user.name,
        token = user.token,
        refreshToken = user.refreshToken
    )
)

fun UserResetPassResponse.toDomain() = UserDomain(
    email = email
)

fun UserLoginDomain.toUserLoginResponse() = UserLoginResponse(
    user = UserLoginResponse.User(
        id = user.id,
        email = user.email,
        name = user.name,
        token = user.token,
        refreshToken = user.refreshToken
    )
)

fun UserInfo.toDomain() = UserDomain(
    email = email,
    active = emailVerified
)