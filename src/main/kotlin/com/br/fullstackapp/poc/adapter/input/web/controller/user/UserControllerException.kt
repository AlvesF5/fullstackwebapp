package com.br.fullstackapp.poc.adapter.input.web.controller.user

import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserControllerException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun handleClientErrors(ex: BadRequestException): ResponseEntity<Map<String, List<String>>> {
        val errors = arrayListOf<String>(ex.localizedMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap(errors))
    }

    private fun errorsMap(errors: List<String>): Map<String, List<String>> {
        val errorResponse = hashMapOf<String, List<String>>()
        errorResponse["errors"] = errors
        return errorResponse
    }
}