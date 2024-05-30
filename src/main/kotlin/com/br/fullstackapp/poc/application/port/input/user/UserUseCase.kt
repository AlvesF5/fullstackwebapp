package com.br.fullstackapp.poc.application.port.input.user

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.UserLoginResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.UserGetAccountInfoResponse
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import org.springframework.http.ResponseEntity

interface UserUseCase {
    fun createUser(userDomain: UserDomain, addressDomain: AddressDomain) : UserDomain

    fun listAllUsers() : ArrayList<UserDomain>?

    fun getUserById(userId: String) : UserDomain?

    fun deleteUserById(userId: String)

    fun updateUserById(userId: String, userDomain: UserDomain) : UserDomain?

    fun loginUser(userLoginRequest: UserLoginRequest) : ResponseEntity<UserLoginResponse>

    fun getAccountInfo(userLoginRequest: UserLoginRequest): ResponseEntity<UserGetAccountInfoResponse>
}