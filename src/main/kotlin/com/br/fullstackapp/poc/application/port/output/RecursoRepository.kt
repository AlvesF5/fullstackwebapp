package com.br.fullstackapp.poc.application.port.output

import com.br.fullstackapp.poc.application.domain.RecursoDomain

interface RecursoRepositoryPort {
    fun criarRecurso(recursoDomain: RecursoDomain) : RecursoDomain
}