package com.easyreport.app.data.models

import java.util.UUID

data class Client(
    val id: String = UUID.randomUUID().toString(),
    val clientName: String,
    val clientAddress: String = "",
    val email: String = "",
    val phone: String = ""
)

data class KeyValue(
    val key: String = "",
    val value: String = ""
)

data class ShearParameters(
    val cValue: String = "",
    val phiValue: String = ""
)

data class BoreholeLog(
    val depth: String = "",
    val natureOfSampling: String = "",
    val soilType: String = "",
    val waterTable: Boolean = false,
    val spt1: String = "",
    val spt2: String = "",
    val spt3: String = "",
    val shearParameters: ShearParameters = ShearParameters(),
    val coreLength: String = "",
    val coreRecovery: String = "",
    val rqd: String = "",
    val sbc: String = ""
)

data class Report(
    val id: String = UUID.randomUUID().toString(),
    val reportId: String = "", // Custom ID like "EDGE2/GEO/..."
    val projectType: String = "",
    val projectDetails: String = "",
    val clientId: String = "",
    val clientName: String = "",
    val clientAddress: String = "",
    val siteId: String = "",
    val siteName: String = "",
    val siteAddress: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val surveyDate: String = "",
    val groundWaterTable: String = "",
    val depthOfFoundation: String = "",
    val recommendations: String = "",
    val status: String = "Draft",
    val isCodes: List<KeyValue> = emptyList(),
    val surveyReport: List<KeyValue> = emptyList(),
    val boreholeLogs: List<List<BoreholeLog>> = emptyList(), // Nested levels
    val createdOn: String = java.time.LocalDate.now().toString(),
    val updatedAt: String = java.time.Instant.now().toString()
)

data class Service(
    val id: String,
    val serviceType: String,
    val price: Double,
    val unit: String,
    val qty: Double,
    val sampling: String? = null
)
