package com.br.fullstackapp.poc.application.service

import com.br.fullstackapp.poc.application.domain.RecursoDomain
import com.br.fullstackapp.poc.application.port.input.RecursoUseCase
import com.br.fullstackapp.poc.application.port.output.RecursoRepositoryPort
import org.springframework.stereotype.Service

@Service
class RecursoService(
    val recursoRepositoryPort: RecursoRepositoryPort
) : RecursoUseCase {

    override fun criarRecurso(recursoDomain: RecursoDomain): RecursoDomain {
        return recursoRepositoryPort.criarRecurso(recursoDomain)
    }
}