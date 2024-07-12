package com.br.fullstackapp.poc.adapter.output.firebase.entity.address

import com.br.fullstackapp.poc.application.domain.address.UF

data class AddressEntity(
    val id: String?="",
    val cep: String?="",
    val street: String?="",
    val number: String?="",
    val state: UF?=null,
    val city: String?="",
    val neighborhood: String?="",
    val complement: String?="",
){
    override fun toString(): String {
        return "Rua/Logradouro: $street, Núm: $number, Estado: ${state?.name}, Cidade: $city, Bairro: $neighborhood, Complemento: $complement, CEP: $cep."
    }
}
