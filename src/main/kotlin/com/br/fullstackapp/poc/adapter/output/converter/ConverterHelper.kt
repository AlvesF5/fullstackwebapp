package com.br.fullstackapp.poc.adapter.output.converter

import com.br.fullstackapp.poc.adapter.output.firebase.entity.recurso.RecursoEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.UserEntity
import com.br.fullstackapp.poc.application.domain.recurso.RecursoDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain

fun RecursoEntity.toDomain() : RecursoDomain =
    RecursoDomain(
        nome = nome,
        chave = chave
    )

fun RecursoDomain.toEntity() : RecursoEntity =
    RecursoEntity(
        nome = nome,
        chave = chave
    )

fun UserDomain.toEntity() : UserEntity =
    UserEntity(
        id= id,
        firstName = firstName,
        lastName = lastName,
        email = email
    )

