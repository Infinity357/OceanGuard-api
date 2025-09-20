package com.example.Ocean.Disaster.Survey.database.repository

import com.example.Ocean.Disaster.Survey.database.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
    fun findByUserId(userId: ObjectId): User?
}