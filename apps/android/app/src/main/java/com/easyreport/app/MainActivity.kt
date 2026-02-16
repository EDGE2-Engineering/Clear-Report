package com.easyreport.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.easyreport.app.data.local.AppDatabase
import com.easyreport.app.data.repository.LocalClientRepository
import com.easyreport.app.data.repository.LocalReportRepository
import com.easyreport.app.data.repository.ClientRepository
import com.easyreport.app.data.repository.ReportRepository
import com.easyreport.app.ui.theme.EasyReportTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Auth (kept for legacy, but we use local storage now)
        com.easyreport.app.data.AuthManager.initialize(applicationContext)

        // Initialize Database and Repositories
        val database = AppDatabase.getDatabase(applicationContext)
        val reportRepository = LocalReportRepository(database.reportDao())
        val clientRepository = LocalClientRepository(database.clientDao())

        setContent {
            EasyReportTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(reportRepository, clientRepository)
                }
            }
        }
    }
}

@Composable
fun MainScreen(reportRepository: ReportRepository, clientRepository: ClientRepository) {
    com.easyreport.app.ui.AppNavigation(reportRepository, clientRepository)
}
