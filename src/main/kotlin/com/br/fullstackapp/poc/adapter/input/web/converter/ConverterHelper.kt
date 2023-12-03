package com.br.fullstackapp.poc.adapter.input.web.converter

import com.br.fullstackapp.poc.adapter.input.web.model.RecursoRequest
import com.br.fullstackapp.poc.application.domain.RecursoDomain

fun RecursoRequest.toDomain() : RecursoDomain =
    RecursoDomain(
        nome = nome,
        chave = chave
    )