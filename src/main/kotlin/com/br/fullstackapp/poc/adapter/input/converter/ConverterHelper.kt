package com.br.fullstackapp.poc.adapter.input.converter

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.application.domain.user.UserDomain

fun UserLoginRequest.toDomain() = UserDomain(
    email = email,
    password = password
)