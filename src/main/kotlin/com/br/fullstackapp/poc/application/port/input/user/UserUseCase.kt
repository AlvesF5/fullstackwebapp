package com.br.fullstackapp.poc.application.port.input.user

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import org.springframework.http.ResponseEntity

interface UserUseCase {
    fun createUser(userDomain: UserDomain) : UserDomain

    fun listAllUsers() : ArrayList<UserDomain>?

    fun getUserById(userId: String) : UserDomain?

    fun deleteUserById(userId: String)

    fun updateUserById(userId: String, userDomain: UserDomain) : UserDomain?

    fun loginUser(userLoginRequest: UserLoginRequest) : ResponseEntity<UserLoginResponse>
}