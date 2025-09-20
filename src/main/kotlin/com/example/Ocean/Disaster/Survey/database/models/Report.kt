package com.example.Ocean.Disaster.Survey.database.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("reports")
data class Report(
    @Id val id: ObjectId = ObjectId(),
    val title: String,
    val description: String,
    val hazardType: String,
    val urgency: String,
    val imageUrl: String?,
    val source: String? = "User",
    val time: String?,
    val status: String? = "Pending",
    val reportedBy: String,
    val reporterUserId: String
)

enum class Source{
    USER, REDDIT
}

enum class Status{
    PENDING, SOLVED, FAKE
}

enum class Urgency{
    LOW, MEDIUM, HIGH
}

/*
"hazardType": "pollution",
"urgency": "high",
"description": "Oil spill near the coastal area affecting marine life.",
"photos": [
"photo1.jpg",
"photo2.jpg"
]
}
 */