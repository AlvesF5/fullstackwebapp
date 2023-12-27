package com.br.fullstackapp.poc.adapter.output.firebase.model.response

data class LoginUserWhiteEmailAndPasswordResponse(
    val kind: String?="",
    val localId: String?="",
    val email: String?="",
    val displayName: String?="",
    val idToken: String?="",
    val registered: Boolean?=false,
    val refreshToken: String?="",
    val expiresIn: String?=""
)
