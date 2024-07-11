package com.br.fullstackapp.poc.application.port.output.user

import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.UserEntity
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain

interface UserRepositoryPort {
    fun createUser(userDomain: UserDomain, addressDomain: AddressDomain) : UserDomain

    fun listAllUsers() : ArrayList<UserDomain>?

    fun deleteUserById(userId: String)

    fun updateUserById(userId: String, userDomain: UserDomain, addressDomain: AddressDomain) : UserDomain?

    fun getUserById(id: String): UserEntity?
}