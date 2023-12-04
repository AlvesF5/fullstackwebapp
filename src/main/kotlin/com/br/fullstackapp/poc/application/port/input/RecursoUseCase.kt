package com.br.fullstackapp.poc.application.port.input

import com.br.fullstackapp.poc.application.domain.RecursoDomain

interface RecursoUseCase {

    fun criarRecurso(recursoDomain: RecursoDomain): RecursoDomain
}