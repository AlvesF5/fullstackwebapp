package com.br.fullstackapp.poc.adapter.input.web.controller.recurso

import com.br.fullstackapp.poc.adapter.input.web.converter.toDomain
import com.br.fullstackapp.poc.adapter.input.web.model.RecursoRequest
import com.br.fullstackapp.poc.application.domain.recurso.RecursoDomain
import com.br.fullstackapp.poc.application.port.input.recurso.RecursoUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/recurso")
@RestController
class RecursoController(
    val recursoUseCase: RecursoUseCase
) {

    @PostMapping
    fun criarRecurso(@RequestBody recursoRequest: RecursoRequest) : RecursoDomain {
        return recursoUseCase.criarRecurso(recursoRequest.toDomain())
    }
}