package com.br.fullstackapp.poc.application.domain.address

data class AddressDomain(
    val cep: String?="",
    val city: String?="",
    val neighborhood: String?="",
    val street: String?="",
    val number: String?="",
    val complement: String?="",
    val state: UF
)
enum class UF{
    AC, AL, AP,AM,BA,CE,DF,ES,GO,MA,MT,MS,MG,PA,PB,PR,PE,PI,RJ,RN,RS,RO,RR,SC
}