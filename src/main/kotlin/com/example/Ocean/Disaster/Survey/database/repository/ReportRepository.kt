package com.example.Ocean.Disaster.Survey.database.repository

import com.example.Ocean.Disaster.Survey.database.models.Report
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ReportRepository: MongoRepository<Report, ObjectId> {
}