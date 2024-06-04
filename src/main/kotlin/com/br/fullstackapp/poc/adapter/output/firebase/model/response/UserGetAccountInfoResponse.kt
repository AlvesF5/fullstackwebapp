package com.br.fullstackapp.poc.adapter.output.firebase.model.response


data class UserGetAccountInfoResponse(
    val users: List<UserInfo>
)

data class UserInfo(
    val email: String?,
    val emailVerified: Boolean?
)
