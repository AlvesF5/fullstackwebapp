package com.br.fullstackapp.poc.adapter.input.web.controller.user

import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginRequest
import com.br.fullstackapp.poc.adapter.input.web.model.UserLoginResponse
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.input.user.UserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    fun loginUser(@RequestBody userLoginRequest: UserLoginRequest) : ResponseEntity<UserLoginResponse> {
        return userUseCase.loginUser(userLoginRequest)
    }

}