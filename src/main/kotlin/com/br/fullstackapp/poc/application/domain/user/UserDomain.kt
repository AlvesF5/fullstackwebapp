package com.br.fullstackapp.poc.application.domain.user

data class UserDomain(
    var id: String?="",
    val displayName: String?="",
    val lastName: String?="",
    val email: String?="",
    val password: String?="",
    val address: Address
)

data class Address(
    var id: String?="",
    val cep: String?="",
    val city: String?="",
    val neighborhood: String?="",
    val street: String="",
    val state: State
)

data class State(
    var id: String?="",
    val name: String?="",
    val uf: UF
)

enum class UF{
    AC, AL, AP,AM,BA,CE,DF,ES,GO,MA,MT,MS,MG,PA,PB,PR,PE,PI,RJ,RN,RS,RO,RR,SC
}
