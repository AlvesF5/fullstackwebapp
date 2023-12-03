package com.br.fullstackapp.poc.adapter.input.web.controller

import com.br.fullstackapp.poc.adapter.input.web.converter.toDomain
import com.br.fullstackapp.poc.adapter.input.web.model.RecursoRequest
import com.br.fullstackapp.poc.application.domain.RecursoDomain
import com.br.fullstackapp.poc.application.port.output.RecursoRepositoryPort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("recurso")
@RestController
class RecursoController(
    val recursoRepositoryPort: RecursoRepositoryPort
) {

    @PostMapping
    fun criarRecurso(@RequestBody recursoRequest: RecursoRequest) : RecursoDomain{
        return recursoRepositoryPort.criarRecurso(recursoRequest.toDomain())
    }
}