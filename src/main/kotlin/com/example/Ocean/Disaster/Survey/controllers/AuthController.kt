package com.example.Ocean.Disaster.Survey.controllers

import com.example.Ocean.Disaster.Survey.database.models.User
import com.example.Ocean.Disaster.Survey.database.repository.UserRepository
import com.example.Ocean.Disaster.Survey.security.HashEncoder
import org.apache.http.auth.InvalidCredentialsException
import org.bson.types.ObjectId
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userRepository:UserRepository,
    private val hashEncoder: HashEncoder
) {
    data class registerRequest(
        val username: String,
        val email: String,
        val password: String,
        val role: String?,
    )

    data class registerReturn(
        val userId: String
    )

    data class authLoginRequest(
        val email: String,
        val password: String
    )

    data class loginReturn(
        val email: String,
        val userId: String
    )

    data class UserIdFind(
        val email: String
    )

    @PostMapping("/register")
    fun register(
        @RequestBody body: registerRequest
    ): registerReturn{
        if(userRepository.findByEmail(body.email) != null){
            throw EmailAlreadyExistsException("Email already in use")
        }
        userRepository.save(
            User(
                username = body.username,
                email = body.email,
                hashedPassword = hashEncoder.encode(body.password),
                role = body.role
            )
        )
        val user = userRepository.findByEmail(body.email) ?:
        throw EmailAlreadyExistsException("Invalid")
        return registerReturn(
            userId = user.userId.toHexString()
        )
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: authLoginRequest
    ): loginReturn{
        val user = userRepository.findByEmail(body.email) ?: throw InvalidCredentialsException("Invalid credentials")

        if(!hashEncoder.matches(body.password , user.hashedPassword)){
            throw BadCredentialsException("Invalid Credentials")
        }

        return loginReturn(
            email = user.email,
            userId = user.userId.toHexString()
        )
    }

    @GetMapping
    fun findByUserId(
        @RequestParam(required = true) userId: String
    ): UserIdFind {
        val user = userRepository.findByUserId(ObjectId(userId))?:
        throw InvalidCredentialsException("Invalid userId")

        return UserIdFind(
            email = user.email
        )
    }
}



class EmailAlreadyExistsException(message: String) : RuntimeException(message)
class InvalidCredentialsException(message: String): RuntimeException(message)
