package com.example.Ocean.Disaster.Survey.database.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Id val userId: ObjectId = ObjectId(),
    val username: String,
    val email: String,
    val hashedPassword: String,
    val role: String? = "User"
)

enum class Role{
    USER , ADMIN
}