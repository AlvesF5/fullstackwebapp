package com.br.fullstackapp.poc.application.service.user

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.input.user.UserUseCase
import com.br.fullstackapp.poc.application.port.output.user.UserManagementAuthPort
import com.br.fullstackapp.poc.application.port.output.user.UserRepositoryPort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepositoryPort: UserRepositoryPort,
    val userManagementAuthPort: UserManagementAuthPort
) : UserUseCase {
    override fun createUser(userDomain: UserDomain): UserDomain {
        val response = userManagementAuthPort.createUserWhiteEmailAndPassword(userDomain)

        if (response!=null){
            userDomain.id=response.id
        }

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

    override fun loginUser(userLoginRequest: UserLoginRequest): ResponseEntity<UserLoginResponse> {
        return userManagementAuthPort.loginUserWhiteEmailAndPassword(userLoginRequest)
    }
}