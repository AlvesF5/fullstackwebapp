package com.br.fullstackapp.poc.application.port.input.user

import com.br.fullstackapp.poc.application.domain.user.UserDomain

interface UserUseCase {
    fun createUser(userDomain: UserDomain) : UserDomain

    fun listAllUsers() : ArrayList<UserDomain>?

    fun getUserById(userId: String) : UserDomain?

    fun deleteUserById(userId: String)

    fun updateUserById(userId: String, userDomain: UserDomain) : UserDomain?
}