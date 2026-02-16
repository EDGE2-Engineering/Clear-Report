package com.easyreport.app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "clients")
data class Client(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val clientName: String = "",
    val clientAddress: String = "",
    val email: String = "",
    val phone: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)

data class KeyValue(
    val key: String = "",
    val value: String = ""
)

data class ValueWrapper(
    val value: String = ""
)

data class RecommendationTypes(
    val rock: Boolean = false,
    val soil: Boolean = true
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

data class GrainSizeDistribution(
    val gravel: String = "",
    val sand: String = "",
    val siltAndClay: String = ""
)

data class AtterbergLimits(
    val liquidLimit: String = "",
    val plasticLimit: String = "",
    val plasticityIndex: String = ""
)

data class LabTestResult(
    val depth: String = "",
    val bulkDensity: String = "",
    val moistureContent: String = "",
    val grainSizeDistribution: GrainSizeDistribution = GrainSizeDistribution(),
    val atterbergLimits: AtterbergLimits = AtterbergLimits(),
    val specificGravity: String = "",
    val freeSwellIndex: String = ""
)

data class ChemicalAnalysis(
    val phValue: String = "",
    val sulphates: String = "",
    val chlorides: String = "",
    val additionalKeys: List<KeyValue> = emptyList()
)

data class GrainSizeRow(
    val depth: String = "",
    val sieve1: String = "",
    val sieve2: String = "",
    val sieve3: String = "",
    val sieve4: String = "",
    val sieve5: String = "",
    val sieve6: String = "",
    val sieve7: String = "",
    val sieve8: String = "",
    val sieve9: String = ""
)

data class SBCRow(
    val depth: String = "",
    val footingDimension: String = "",
    val useForReport: Boolean = false,
    val sbcValue: String = ""
)

data class SubSoilRow(
    val depth: String = "",
    val description: String = ""
)

data class StressReading(
    val normalStress: String = "",
    val shearStress: String = ""
)

data class DirectShearTest(
    val shearBoxSize: String = "",
    val depthOfSample: String = "",
    val cValue: String = "",
    val phiValue: String = "",
    val stressReadings: List<StressReading> = emptyList()
)

data class PointLoadReading(
    val loadAtFailure: String = "",
    val d50: String = "",
    val d: String = "",
    val ucs: String = ""
)

data class PointLoadTest(
    val depth: String = "",
    val readings: List<PointLoadReading> = emptyList()
)

data class PointLoadLumpReading(
    val loadAtFailure: String = "",
    val d50: String = "",
    val d: String = "",
    val w: String = "",
    val ucs: String = ""
)

data class PointLoadLumpTest(
    val depth: String = "",
    val readings: List<PointLoadLumpReading> = emptyList()
)

data class RockFormationRow(
    val rock: String = "",
    val strength: String = "",
    val rqd: String = "",
    val spacingDiscontinuity: String = "",
    val conditionOfDiscontinuity: String = "",
    val gwtCondition: String = "",
    val discontinuityOrientation: String = "",
    val rockGrade: String = "",
    val inferredNetSbp: String = ""
)

data class FoundationRockLevel(
    val rows: List<RockFormationRow> = emptyList()
)

@Entity(tableName = "reports")
data class Report(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val reportId: String = "", // Custom ID like "EDGE2/GEO/..."
    val projectType: String = "",
    val projectDetails: String = "",
    val clientId: String = "",
    val clientName: String = "",
    val clientImage: String = "", // Base64
    val clientAddress: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val siteId: String = "",
    val anchorId: String = "",
    val siteName: String = "",
    val siteAddress: String = "",
    val surveyDate: String = "",
    val groundWaterTable: String = "",
    val surveyReportNote: String = "",
    val includeSurveyReportNote: Boolean = false,
    val depthOfFoundation: String = "",
    val recommendations: String = "",
    val status: String = "Draft",
    
    // Complex nested data stored as JSON via TypeConverters
    val isCodes: List<KeyValue> = emptyList(),
    val surveyReport: List<KeyValue> = emptyList(),
    val conclusions: List<ValueWrapper> = emptyList(),
    val recommendationTypes: RecommendationTypes = RecommendationTypes(),
    val sitePhotos: List<String> = emptyList(), // Base64 strings
    val boreholeLogs: List<List<BoreholeLog>> = emptyList(),
    val labTestResults: List<List<LabTestResult>> = emptyList(),
    val chemicalAnalysis: List<ChemicalAnalysis> = emptyList(),
    val grainSizeAnalysis: List<List<GrainSizeRow>> = emptyList(),
    val sbcDetails: List<List<SBCRow>> = emptyList(),
    val subSoilProfile: List<List<SubSoilRow>> = emptyList(),
    val directShearResults: List<List<DirectShearTest>> = emptyList(),
    val pointLoadStrength: List<List<PointLoadTest>> = emptyList(),
    val pointLoadStrengthLump: List<List<PointLoadLumpTest>> = emptyList(),
    val foundationRockFormations: List<FoundationRockLevel> = emptyList(),
    
    val createdOn: String = java.time.LocalDate.now().toString(),
    val updatedAt: Long = System.currentTimeMillis()
)

data class Service(
    val id: String,
    val serviceType: String,
    val price: Double,
    val unit: String,
    val qty: Double,
    val sampling: String? = null
)
