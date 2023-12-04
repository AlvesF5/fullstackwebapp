package com.br.fullstackapp.poc.adapter.output.converter

import com.br.fullstackapp.poc.adapter.output.firebase.entity.RecursoEntity
import com.br.fullstackapp.poc.application.domain.RecursoDomain

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