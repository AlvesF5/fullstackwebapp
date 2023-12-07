package com.br.fullstackapp.poc.application.port.input.recurso

import com.br.fullstackapp.poc.application.domain.recurso.RecursoDomain

interface RecursoUseCase {

    fun criarRecurso(recursoDomain: RecursoDomain): RecursoDomain
}