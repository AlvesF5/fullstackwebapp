package com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request

import com.br.fullstackapp.poc.application.domain.address.AddressDomain

data class CreateUserRequest(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val password: String?="",
    val phone: String?="",
    val birthDate: String?="",
    val documentNumber: String?="",
    val gender: String?="",
    val address: AddressDomain?=null
)
