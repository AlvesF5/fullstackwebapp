package com.br.fullstackapp.poc.adapter.input.web.controller.user

import com.br.fullstackapp.poc.adapter.input.converter.toDomain
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.CreateUserRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UpdateUserRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.request.UserResetPassRequest
import com.br.fullstackapp.poc.adapter.input.web.controller.user.model.response.*
import com.br.fullstackapp.poc.adapter.output.converter.*
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.UserGetAccountInfoResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.domain.user.toGetUserByIdResponse
import com.br.fullstackapp.poc.application.domain.user.toResetPassResponse
import com.br.fullstackapp.poc.application.port.input.user.UserUseCase
import com.br.fullstackapp.poc.application.port.output.address.AddressRepositoryPort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(value = ["http://localhost:3000/"])
@RestController
@RequestMapping("/v1/user")
class UserController(
    val userUseCase: UserUseCase,
    val addressRepositoryPort: AddressRepositoryPort
) {

    @PostMapping("/create")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse {
        return userUseCase.createUser(createUserRequest.toDomain(), createUserRequest.address!!).toCreateUserResponse()
    }

    @GetMapping("/list")
    fun listAllUsers() : ArrayList<UserDomain>?{
        return userUseCase.listAllUsers()
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: String) : ResponseEntity<GetUserByIdResponse>{
        return userUseCase.getUserById(userId).let {
            ResponseEntity.ok(it.body?.toGetUserByIdResponse(addressRepositoryPort))
        }
    }

    @DeleteMapping("/delete/{userId}")
    fun deleteUserById(@PathVariable userId: String){
        userUseCase.deleteUserById(userId)
    }
    @PutMapping("/update/{userId}")
    fun updateUserById(@PathVariable userId: String, @RequestBody updateUserRequest: UpdateUserRequest) : UserDomain?{
        return updateUserRequest.address?.let { userUseCase.updateUserById(userId,updateUserRequest.toDomain(), it) }
    }
    @PostMapping("/login")
    fun loginUser(@RequestBody userLoginRequest: UserLoginRequest) : ResponseEntity<UserLoginResponse> {
        return userUseCase.loginUser(userLoginRequest.toDomain()).let {
            ResponseEntity.ok(it.body?.toUserLoginResponse())
        }
    }

    @PostMapping("/info")
    fun infoUser(@RequestBody userLoginRequest: UserLoginRequest) : ResponseEntity<UserGetAccountInfoResponse> {
        return userUseCase.getAccountInfo(userLoginRequest.toDomain()).let {
            ResponseEntity.ok(it.body?.toUserAccountInformationResp())
        }
    }

    @PostMapping("/resetPassword")
    fun resetUserPassword(@RequestBody userResetPassRequest: UserResetPassRequest) : ResponseEntity<UserResetPassResponse>{
        return userUseCase.sendPasswordResetEmail(userResetPassRequest.email).let {
            ResponseEntity.ok(it.body?.toResetPassResponse())
        }
    }

    @PostMapping("/sendVerificationEmail")
    fun userSendVerificationEmail(@RequestBody userLoginRequest: UserLoginRequest) : ResponseEntity<UserSendVerificationEmailResponse>{
        return ResponseEntity.ok(userUseCase.sendVerificationEmail(userLoginRequest.toDomain()).body?.toSendVerificationEmailResponse())
    }
}