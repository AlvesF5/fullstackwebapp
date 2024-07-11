package com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request

import TimestampDeserializer
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.google.cloud.Timestamp

data class UpdateUserRequest(
    val firstName: String?="",
    val lastName: String?="",
    val phone: String?="",
    @JsonDeserialize(using = TimestampDeserializer::class)
    val birthDate: Timestamp?=null,
    val documentNumber: String?="",
    val gender: String?="",
    val address: AddressDomain?=null,
    var updatedAt: Timestamp? = null,
)
