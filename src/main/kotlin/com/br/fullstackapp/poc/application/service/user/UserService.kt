package com.br.fullstackapp.poc.application.service.user

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.input.user.UserUseCase
import com.br.fullstackapp.poc.application.port.output.user.UserManagementAuthPort
import com.br.fullstackapp.poc.application.port.output.user.UserRepositoryPort
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Map

@Service
@RequiredArgsConstructor
class UserService(
    val userRepositoryPort: UserRepositoryPort,
    val userManagementAuthPort: UserManagementAuthPort
) : UserUseCase {
    override fun createUser(userDomain: UserDomain): UserDomain {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(FirebaseApp.getInstance())
        val response = userManagementAuthPort.createUserWhiteEmailAndPassword(userDomain)

        if (response!=null){
            userDomain.id=response.id
            firebaseAuth!!.setCustomUserClaims(userDomain.id, Map.of<String, Any>("custom_claims", listOf("CUSTOMER")))
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