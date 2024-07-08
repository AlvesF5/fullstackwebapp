package com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response

import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity

data class GetUserByIdResponse(
    var id: String?="",
    val firstName: String?="",
    val lastName: String?="",
    val email: String?="",
    val phone: String?="",
    var birthDate: String?="",
    val documentNumber: String?="",
    val gender: String?="",
    var createdAt: String? = "",
    var updatedAt: String? = "",
    val active: Boolean? = false,
    val address: String?=null
)
