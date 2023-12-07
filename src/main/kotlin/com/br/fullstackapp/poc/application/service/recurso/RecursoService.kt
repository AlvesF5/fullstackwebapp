package com.br.fullstackapp.poc.application.service.recurso

import com.br.fullstackapp.poc.application.domain.recurso.RecursoDomain
import com.br.fullstackapp.poc.application.port.input.recurso.RecursoUseCase
import com.br.fullstackapp.poc.application.port.output.recurso.RecursoRepositoryPort
import org.springframework.stereotype.Service

@Service
class RecursoService(
    val recursoRepositoryPort: RecursoRepositoryPort
) : RecursoUseCase {

    override fun criarRecurso(recursoDomain: RecursoDomain): RecursoDomain {
        return recursoRepositoryPort.criarRecurso(recursoDomain)
    }
}