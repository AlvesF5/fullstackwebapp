package com.br.fullstackapp.poc.adapter.output.firebase.client

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.output.converter.toDomain
import com.br.fullstackapp.poc.adapter.output.firebase.model.request.CreateUserWithEmailAndPasswordRequest
import com.br.fullstackapp.poc.adapter.output.firebase.model.request.LoginUserWithEmailAndPasswordRequest
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.CreateUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.LoginUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.output.user.UserManagementAuthPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class UserManagementAuthClient : UserManagementAuthPort{

    @Value("\${user.management.auth.client.base-url}")
    lateinit var userManagementAuthClientBaseUrl : String
    @Value("\${firebase.api.key}")
    lateinit var firebaseApiKey : String
    val restClient : RestClient = RestClient.builder().baseUrl("https://identitytoolkit.googleapis.com/v1/accounts").build()

    override fun createUserWhiteEmailAndPassword(userDomain: UserDomain): UserDomain? {
        val request = CreateUserWithEmailAndPasswordRequest(
            email = userDomain.email!!,
            password = userDomain.password!!,
            displayName = userDomain.firstName!!
        )
        val response = restClient
            .post()
            .uri(":signUp?key=$firebaseApiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .body(CreateUserWhiteEmailAndPasswordResponse::class.java)

        return response?.toDomain() ?: null
    }

    override fun loginUserWhiteEmailAndPassword(userLoginRequest: UserLoginRequest): LoginUserWhiteEmailAndPasswordResponse? {
        val request = LoginUserWithEmailAndPasswordRequest(
            email = userLoginRequest.email!!,
            password = userLoginRequest.password!!
        )

        return restClient
            .post()
            .uri(":signInWithPassword?key=$firebaseApiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .body(LoginUserWhiteEmailAndPasswordResponse::class.java)
    }
}