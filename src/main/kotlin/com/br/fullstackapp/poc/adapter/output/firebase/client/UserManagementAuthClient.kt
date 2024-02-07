package com.br.fullstackapp.poc.adapter.output.firebase.client

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginResponse
import com.br.fullstackapp.poc.adapter.output.converter.toDomain
import com.br.fullstackapp.poc.adapter.output.firebase.model.request.CreateUserWithEmailAndPasswordRequest
import com.br.fullstackapp.poc.adapter.output.firebase.model.request.LoginUserWithEmailAndPasswordRequest
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.CreateUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.LoginUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.output.user.UserManagementAuthPort
import org.apache.coyote.BadRequestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
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
            displayName = userDomain.displayName!!,
            lastName = userDomain.lastName!!
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

    override fun loginUserWhiteEmailAndPassword(userLoginRequest: UserLoginRequest): ResponseEntity<UserLoginResponse> {
        val request = LoginUserWithEmailAndPasswordRequest(
            email = userLoginRequest.email!!,
            password = userLoginRequest.password!!
        )

        val response = restClient
            .post()
            .uri(":signInWithPassword?key=$firebaseApiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                throw BadRequestException("${response.statusText}: Usuário e/ou senha inválidos!")}
            .body(LoginUserWhiteEmailAndPasswordResponse::class.java)


          val userResp =  UserLoginResponse(
                UserLoginResponse.User(
                    id = response!!.localId!!,
                    email = response.email!!,
                    name = response.displayName!!,
                    token = response.idToken!!,
                    refreshToken = response.refreshToken!!
                )
            )

       return ResponseEntity.ok(userResp)
    }
}