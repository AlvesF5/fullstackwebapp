package com.br.fullstackapp.poc.adapter.output.firebase.client

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserLoginResponse
import com.br.fullstackapp.poc.adapter.output.converter.toDomain
import com.br.fullstackapp.poc.adapter.output.firebase.model.request.*
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.*
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

    override fun createUserWhiteEmailAndPassword(userDomain: UserDomain): ResponseEntity<UserDomain> {
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
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                throw BadRequestException("Usuário com e-mail já cadastrado!")}
            .body(CreateUserWhiteEmailAndPasswordResponse::class.java)

        return ResponseEntity.ok(response?.toDomain())
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
                throw BadRequestException("Usuário e/ou senha inválidos!")}
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

    override fun sendVerifyEmailRequest(idToken: String?): ResponseEntity<UserVerifyEmailResponse> {
        val request = UserVerifyEmailRequest(
            requestType = "VERIFY_EMAIL",
            idToken = idToken!!
        )
        val response = restClient
            .post()
            .uri(":sendOobCode?key=$firebaseApiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                throw BadRequestException("Erro ao enviar e-mail de verificação!")
            }
            .body(UserVerifyEmailResponse::class.java)

        println("Email: ${response?.email}")

        return ResponseEntity.ok(response)
    }

    override fun sendPasswordResetEmail(email: String): ResponseEntity<UserResetPassResponse> {
        val request = UserResetPassRequest(
            requestType = "PASSWORD_RESET",
            email = email
        )
        val response = restClient
            .post()
            .uri(":sendOobCode?key=$firebaseApiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                throw BadRequestException("O e-mail inserido não foi encontrado em nossa base de dados!")
            }
            .body(UserResetPassResponse::class.java)

        return ResponseEntity.ok(response)
    }

    override fun getAccountInfo(idToken: String?): ResponseEntity<UserGetAccountInfoResponse> {
        val request = UserGetAccountInfoRequest(idToken = idToken!!)
        val response = restClient
            .post()
            .uri(":lookup?key=$firebaseApiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                throw BadRequestException("Erro ao obter informações da conta!")
            }
            .body(UserGetAccountInfoResponse::class.java)

        val emailVerified = response?.users?.firstOrNull()?.emailVerified ?: false
        println("Email verificado: $emailVerified")
        return ResponseEntity.ok(response)
    }
}