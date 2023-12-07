package com.br.fullstackapp.poc.application.port.output.user

import com.br.fullstackapp.poc.application.domain.user.UserDomain

interface UserRepositoryPort {
    fun createUser(userDomain: UserDomain) : UserDomain

    fun listAllUsers() : ArrayList<UserDomain>?

    fun getUserById(userId: String) : UserDomain?
}