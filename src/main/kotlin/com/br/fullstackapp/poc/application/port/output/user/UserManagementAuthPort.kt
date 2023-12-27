package com.br.fullstackapp.poc.application.port.output.user

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.output.firebase.model.request.CreateUserWithEmailAndPasswordRequest
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.LoginUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain

interface UserManagementAuthPort {
    fun createUserWhiteEmailAndPassword(request: UserDomain) : UserDomain?

    fun loginUserWhiteEmailAndPassword(userLoginRequest: UserLoginRequest) : LoginUserWhiteEmailAndPasswordResponse?
}