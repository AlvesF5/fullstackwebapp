package com.br.fullstackapp.poc.adapter.input.web.controller.user

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginResponse
import com.br.fullstackapp.poc.adapter.output.firebase.model.response.LoginUserWhiteEmailAndPasswordResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.input.user.UserUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(value = ["http://localhost:3000/"])
@RestController
@RequestMapping("/v1/user")
class UserController(
    val userUseCase: UserUseCase
) {

    @PostMapping("/create")
    fun createUser(@RequestBody userDomain: UserDomain): UserDomain{
        return userUseCase.createUser(userDomain)
    }

    @GetMapping("/list")
    fun listAllUsers() : ArrayList<UserDomain>?{
        return userUseCase.listAllUsers()
    }

    @GetMapping("{userId}")
    fun getUserById(@PathVariable userId: String) : UserDomain?{
        return userUseCase.getUserById(userId)
    }

    @DeleteMapping("/delete/{userId}")
    fun deleteUserById(@PathVariable userId: String){
        userUseCase.deleteUserById(userId)
    }
    @PutMapping("/update/{userId}")
    fun updateUserById(@PathVariable userId: String, @RequestBody userDomain: UserDomain) : UserDomain?{
        return  userUseCase.updateUserById(userId,userDomain)
    }
    @PostMapping("/login")
    fun loginUser(@RequestBody userLoginRequest: UserLoginRequest) : UserLoginResponse? {
        val response = userUseCase.loginUser(userLoginRequest)
        if (response != null) {
            return UserLoginResponse(
                UserLoginResponse.User(
                    id = response.localId!!,
                    email = response.email!!,
                    name = response.displayName!!,
                    token = response.idToken!!,
                    refreshToken = response.refreshToken!!
                )
            )
        }

        return null
    }

}