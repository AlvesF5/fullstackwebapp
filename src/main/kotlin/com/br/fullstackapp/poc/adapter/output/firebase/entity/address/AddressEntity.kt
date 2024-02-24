package com.br.fullstackapp.poc.adapter.output.firebase.entity.address

import com.br.fullstackapp.poc.application.domain.address.UF

data class AddressEntity(
    val cep: String?="",
    val city: String?="",
    val neighborhood: String?="",
    val street: String?="",
    val complement: String?="",
    val state: UF
)
