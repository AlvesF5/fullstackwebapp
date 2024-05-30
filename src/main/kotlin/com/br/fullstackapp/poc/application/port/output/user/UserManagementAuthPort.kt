package com.br.fullstackapp.poc.application.port.output.user

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserLoginResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.UserGetAccountInfoResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.UserVerifyEmailResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import org.springframework.http.ResponseEntity

interface UserManagementAuthPort {
    fun createUserWhiteEmailAndPassword(request: UserDomain) : ResponseEntity<UserDomain>
    fun loginUserWhiteEmailAndPassword(userLoginRequest: UserLoginRequest) : ResponseEntity<UserLoginResponse>
    fun sendVerifyEmailRequest(idToken: String?): ResponseEntity<UserVerifyEmailResponse>
    fun getAccountInfo(idToken: String?): ResponseEntity<UserGetAccountInfoResponse>
}