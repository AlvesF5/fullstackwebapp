package com.br.fullstackapp.poc.adapter.input.web.controller.user

import com.br.fullstackapp.poc.application.exception.NotFoundException
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserControllerException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class, IllegalArgumentException::class)
    fun handleClientErrors(ex: BadRequestException): ResponseEntity<Map<String, List<String>>> {
        val errors = arrayListOf<String>(ex.localizedMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap(errors))
    }

    private fun errorsMap(errors: List<String>): Map<String, List<String>> {
        val errorResponse = hashMapOf<String, List<String>>()
        errorResponse["errors"] = errors
        return errorResponse
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleUserNotFoundException(ex: NotFoundException): ResponseEntity<Map<String, List<String>>> {
        val errors = arrayListOf<String>(ex.localizedMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsMap(errors))
    }
}