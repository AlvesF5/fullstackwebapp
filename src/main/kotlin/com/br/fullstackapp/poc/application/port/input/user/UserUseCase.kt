package com.br.fullstackapp.poc.application.port.input.user

import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.model.UserLoginDomain
import org.springframework.http.ResponseEntity

interface UserUseCase {
    fun createUser(userDomain: UserDomain, addressDomain: AddressDomain) : UserDomain

    fun listAllUsers() : ArrayList<UserDomain>?

    fun getUserById(userId: String) : UserDomain?

    fun deleteUserById(userId: String)

    fun updateUserById(userId: String, userDomain: UserDomain) : UserDomain?

    fun loginUser(userDomain: UserDomain) : ResponseEntity<UserLoginDomain>

    fun getAccountInfo(userDomain: UserDomain): ResponseEntity<UserDomain>

    fun sendPasswordResetEmail(email: String): ResponseEntity<UserDomain>

    fun sendVerificationEmail(userDomain: UserDomain): ResponseEntity<UserDomain>
}