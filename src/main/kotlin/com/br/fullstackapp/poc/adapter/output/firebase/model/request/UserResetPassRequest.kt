package com.br.fullstackapp.poc.adapter.output.firebase.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class UserResetPassRequest(
    @NotNull @NotBlank
    val requestType: String,
    @NotNull @NotBlank
    val email: String
)
