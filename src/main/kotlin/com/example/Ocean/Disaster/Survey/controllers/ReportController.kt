package com.example.Ocean.Disaster.Survey.controllers

import com.example.Ocean.Disaster.Survey.database.models.Report
import com.example.Ocean.Disaster.Survey.database.repository.ReportRepository
import com.example.Ocean.Disaster.Survey.database.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/*
   @Id val id: ObjectId = ObjectId(),
    val title: String,
    val description: String,
    val imageUrl: String,
    val source: Source,
    val time: String,
    val status: Status,
    val reportedBy: String
* */

/*
{
  "hazardType": "pollution",
  "urgency": "high",
  "description": "Oil spill near the coastal area affecting marine life.",
  "photos": [
    "photo1.jpg",
    "photo2.jpg"
  ]
}

 */




@RestController
@RequestMapping("/report")
class ReportController (
    private val reportRepository: ReportRepository,
    private val userRepository: UserRepository
){

    data class hazardPostRequest(
        val id: String?,
        val userId: String,
        val hazardType: String,
        val title: String?,
        val description: String,
        val time: String?,
        val imageUrl: String?,
        val status: String? = "Pending",
        val urgency: String,
        val source: String?
    )

    data class hazardPostResponse(
        val id: String,
        val reportedBy: String,
        val reporterId: String,
        val title: String? = "",
        val hazardType: String,
        val description: String,
        val time: String?,
        val imageUrl: String?,
        val status: String? = "Pending"
    )

    data class deleteRequest(
        val hazardId: String,
        val userId: String
    )

    @PostMapping
    fun save(
        @RequestBody body: hazardPostRequest
    ): hazardPostResponse{
        val report = reportRepository.save(
            Report(
                title = body.title,
                description = body.description,
                hazardType = body.hazardType,
                urgency = body.urgency,
                imageUrl = body.imageUrl,
                source = body.source,
                time = body.time,
                status = body.status,
                reportedBy = userRepository.findByUserId(ObjectId(body.userId))?.username ?: "",
                reporterUserId = body.userId
            )
        )
        return report.toResponse()
    }

    @GetMapping

    @DeleteMapping
    fun deleteByItemId(
        @RequestBody body: deleteRequest
    ){
        val user = userRepository.findByUserId(ObjectId(body.userId))
        if(user?.role == "ADMIN" || user?.userId?.toHexString().equals(body.userId))
            reportRepository.deleteById(ObjectId(body.hazardId))
        else throw NotEnoughClearnace("Not Enough Clearance")

    }

    // GET all hazard reports
    @GetMapping
    fun getAllReports(): List<hazardPostResponse> {
        return reportRepository.findAll().map { it.toResponse() }
    }

    private fun Report.toResponse(): hazardPostResponse{
        return hazardPostResponse(
            id = id.toHexString(),
            title = title,
            reportedBy = reportedBy,
            reporterId = reporterUserId,
            hazardType = hazardType,
            description = description,
            time = time,
            imageUrl = imageUrl,
            status = status
        )
    }
}

class NotEnoughClearnace(message: String) : RuntimeException(message)