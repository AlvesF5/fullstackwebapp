package com.br.fullstackapp.poc.application.port.output.recurso

import com.br.fullstackapp.poc.application.domain.recurso.RecursoDomain

interface RecursoRepositoryPort {
    fun criarRecurso(recursoDomain: RecursoDomain) : RecursoDomain
}