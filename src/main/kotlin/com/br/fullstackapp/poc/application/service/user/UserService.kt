package com.br.fullstackapp.poc.application.service.user

import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.adapter.output.converter.toDomain
import com.br.fullstackapp.poc.adapter.output.converter.toUserLoginDomain
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.toDomain
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.model.UserLoginDomain
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
    override fun createUser(userDomain: UserDomain, addressDomain: AddressDomain): UserDomain {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(FirebaseApp.getInstance())
        val response = userManagementAuthPort.createUserWhiteEmailAndPassword(userDomain)

        if (response!=null){
            userDomain.id=response.body?.id
            firebaseAuth!!.setCustomUserClaims(userDomain.id, Map.of<String, Any>("custom_claims", listOf("CUSTOMER")))
        }

        userRepositoryPort.createUser(userDomain, addressDomain).let {
            if (!it.id.isNullOrBlank()){
                val userLogged = userManagementAuthPort.loginUserWhiteEmailAndPassword(UserLoginRequest(email = userDomain.email, password = userDomain.password))
                userManagementAuthPort.sendVerifyEmailRequest(userLogged.body?.user?.token)
            }
            return it
        }


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

    override fun loginUser(userDomain: UserDomain): ResponseEntity<UserLoginDomain> {
        return userManagementAuthPort.loginUserWhiteEmailAndPassword(
            UserLoginRequest(
                email = userDomain.email,
                password = userDomain.password
        )).let { ResponseEntity.ok(it.body?.toUserLoginDomain()) }
    }

    override fun getAccountInfo(userDomain: UserDomain): ResponseEntity<UserDomain> {
        val userLogged = loginUser(userDomain)
        return userManagementAuthPort.getAccountInfo(userLogged.body?.user?.token).let {
            ResponseEntity.ok(it.body?.users?.first()?.toDomain())
        }
    }

    override fun sendPasswordResetEmail(email: String): ResponseEntity<UserDomain> {
        return userManagementAuthPort.sendPasswordResetEmail(email).let {
            ResponseEntity.ok(it.body?.toDomain())
        }
    }

    override fun sendVerificationEmail(userDomain: UserDomain): ResponseEntity<UserDomain> {
        return userManagementAuthPort.loginUserWhiteEmailAndPassword(UserLoginRequest(email = userDomain.email, password = userDomain.password)).let {
            val token = it.body?.user?.token
            ResponseEntity.ok(userManagementAuthPort.sendVerifyEmailRequest(token).body?.toDomain())
        }
    }
}