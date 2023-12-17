package com.br.fullstackapp.poc.application.service.user

import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.input.user.UserUseCase
import com.br.fullstackapp.poc.application.port.output.user.UserRepositoryPort
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepositoryPort: UserRepositoryPort
) : UserUseCase {
    override fun createUser(userDomain: UserDomain): UserDomain {
        return userRepositoryPort.createUser(userDomain)
    }

    override fun listAllUsers(): ArrayList<UserDomain>? {
        return userRepositoryPort.listAllUsers()
    }

    override fun getUserById(userId: String): UserDomain? {
        return userRepositoryPort.getUserById(userId)
    }

    override fun deleteUserById(userId: String) {
        userRepositoryPort.deleteUserById(userId)
    }

    override fun updateUserById(userId: String, userDomain: UserDomain): UserDomain? {
        return userRepositoryPort.updateUserById(userId,userDomain)
    }
}