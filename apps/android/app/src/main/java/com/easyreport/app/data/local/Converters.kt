package com.easyreport.app.data.local

import androidx.room.TypeConverter
import com.easyreport.app.data.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromKeyValueList(value: List<KeyValue>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toKeyValueList(value: String): List<KeyValue> = 
        gson.fromJson(value, object : TypeToken<List<KeyValue>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromValueWrapperList(value: List<ValueWrapper>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toValueWrapperList(value: String): List<ValueWrapper> = 
        gson.fromJson(value, object : TypeToken<List<ValueWrapper>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromStringList(value: List<String>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toStringList(value: String): List<String> = 
        gson.fromJson(value, object : TypeToken<List<String>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromRecommendationTypes(value: RecommendationTypes?): String = gson.toJson(value)
    
    @TypeConverter
    fun toRecommendationTypes(value: String): RecommendationTypes = 
        gson.fromJson(value, RecommendationTypes::class.java) ?: RecommendationTypes()

    @TypeConverter
    fun fromBoreholeLogNestedList(value: List<List<BoreholeLog>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toBoreholeLogNestedList(value: String): List<List<BoreholeLog>> = 
        gson.fromJson(value, object : TypeToken<List<List<BoreholeLog>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromLabTestResultNestedList(value: List<List<LabTestResult>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toLabTestResultNestedList(value: String): List<List<LabTestResult>> = 
        gson.fromJson(value, object : TypeToken<List<List<LabTestResult>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromChemicalAnalysisList(value: List<ChemicalAnalysis>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toChemicalAnalysisList(value: String): List<ChemicalAnalysis> = 
        gson.fromJson(value, object : TypeToken<List<ChemicalAnalysis>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromGrainSizeRowNestedList(value: List<List<GrainSizeRow>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toGrainSizeRowNestedList(value: String): List<List<GrainSizeRow>> = 
        gson.fromJson(value, object : TypeToken<List<List<GrainSizeRow>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromSBCRowNestedList(value: List<List<SBCRow>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toSBCRowNestedList(value: String): List<List<SBCRow>> = 
        gson.fromJson(value, object : TypeToken<List<List<SBCRow>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromSubSoilRowNestedList(value: List<List<SubSoilRow>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toSubSoilRowNestedList(value: String): List<List<SubSoilRow>> = 
        gson.fromJson(value, object : TypeToken<List<List<SubSoilRow>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromDirectShearTestNestedList(value: List<List<DirectShearTest>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toDirectShearTestNestedList(value: String): List<List<DirectShearTest>> = 
        gson.fromJson(value, object : TypeToken<List<List<DirectShearTest>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromPointLoadTestNestedList(value: List<List<PointLoadTest>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toPointLoadTestNestedList(value: String): List<List<PointLoadTest>> = 
        gson.fromJson(value, object : TypeToken<List<List<PointLoadTest>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromPointLoadLumpTestNestedList(value: List<List<PointLoadLumpTest>>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toPointLoadLumpTestNestedList(value: String): List<List<PointLoadLumpTest>> = 
        gson.fromJson(value, object : TypeToken<List<List<PointLoadLumpTest>>>() {}.type) ?: emptyList()

    @TypeConverter
    fun fromFoundationRockLevelList(value: List<FoundationRockLevel>?): String = gson.toJson(value)
    
    @TypeConverter
    fun toFoundationRockLevelList(value: String): List<FoundationRockLevel> = 
        gson.fromJson(value, object : TypeToken<List<FoundationRockLevel>>() {}.type) ?: emptyList()
}
