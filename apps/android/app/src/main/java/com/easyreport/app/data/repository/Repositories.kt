package com.easyreport.app.data.repository

import com.easyreport.app.data.local.ClientDao
import com.easyreport.app.data.local.ReportDao
import com.easyreport.app.data.models.Client
import com.easyreport.app.data.models.Report
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    fun getReports(): Flow<List<Report>>
    suspend fun getReportById(id: String): Report?
    suspend fun saveReport(report: Report)
    suspend fun deleteReport(report: Report)
}

interface ClientRepository {
    fun getClients(): Flow<List<Client>>
    suspend fun saveClient(client: Client)
    suspend fun updateClient(client: Client)
    suspend fun deleteClient(client: Client)
}

class LocalReportRepository(private val reportDao: ReportDao) : ReportRepository {
    override fun getReports(): Flow<List<Report>> = reportDao.getAllReports()
    override suspend fun getReportById(id: String): Report? = reportDao.getReportById(id)
    override suspend fun saveReport(report: Report) = reportDao.insertReport(report)
    override suspend fun deleteReport(report: Report) = reportDao.deleteReport(report)
}

class LocalClientRepository(private val clientDao: ClientDao) : ClientRepository {
    override fun getClients(): Flow<List<Client>> = clientDao.getAllClients()
    override suspend fun saveClient(client: Client) = clientDao.insertClient(client)
    override suspend fun updateClient(client: Client) = clientDao.updateClient(client)
    override suspend fun deleteClient(client: Client) = clientDao.deleteClient(client)
}
