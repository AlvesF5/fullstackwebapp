package com.br.fullstackapp.poc.adapter.output.firebase.model.response

data class UpdatePasswordResponse(
    val localId: String?,
    val email: String?,
    val displayName: String?,
    val idToken: String?,
    val refreshToken: String?
)
