package com.example.Ocean.Disaster.Survey

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
// import util.SslUtils.disableSslVerification

@SpringBootApplication
class OceanDisasterSurveyApplication

fun main(args: Array<String>) {
//    disableSslVerification()
 //   System.setProperty("https.protocols", "TLSv1.2")
	runApplication<OceanDisasterSurveyApplication>(*args)
}
