package com.br.fullstackapp.poc.adapter.output.firebase.entity.address

import com.br.fullstackapp.poc.application.domain.address.UF

data class AddressEntity(
    val cep: String?="",
    val street: String?="",
    val number: String?="",
    val state: UF?=null,
    val city: String?="",
    val neighborhood: String?="",
    val complement: String?="",
){
    override fun toString(): String {
        return "CEP: $cep, Rua/Logradouro: $street, Num.: $number, Estado: ${state?.name}, Cidade: $city, Bairro: $neighborhood, Complemento: $complement"
    }
}
