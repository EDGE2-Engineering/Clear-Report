package com.easyreport.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.easyreport.app.data.models.Report
import com.easyreport.app.data.repository.ClientRepository
import com.easyreport.app.data.repository.ReportRepository
import com.easyreport.app.ui.components.DashboardScreen
import com.easyreport.app.ui.screens.ManageClientsScreen
import com.easyreport.app.ui.screens.NewReportScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    reportRepository: ReportRepository,
    clientRepository: ClientRepository
) {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("dashboard") }
    val scope = rememberCoroutineScope()
    
    val reports by reportRepository.getReports().collectAsState(initial = emptyList())
    val clients by clientRepository.getClients().collectAsState(initial = emptyList())

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentRoute == "dashboard",
                    onClick = { 
                        currentRoute = "dashboard"
                        navController.navigate("dashboard") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Reports") },
                    label = { Text("Reports") },
                    selected = currentRoute == "reports",
                    onClick = { 
                        currentRoute = "reports"
                        navController.navigate("reports") {
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Clients") },
                    label = { Text("Clients") },
                    selected = currentRoute == "clients",
                    onClick = { 
                        currentRoute = "clients"
                        navController.navigate("clients") {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("dashboard") {
                DashboardScreen(
                    reports = reports.take(5),
                    onCreateReport = { navController.navigate("create_report") },
                    onReportClick = { report -> navController.navigate("edit_report/${report.id}") }
                )
            }
            composable("create_report") {
                NewReportScreen(
                    onSave = { report ->
                        scope.launch {
                            reportRepository.saveReport(report)
                            navController.popBackStack()
                        }
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
            composable("edit_report/{reportId}") { backStackEntry ->
                val reportId = backStackEntry.arguments?.getString("reportId")
                val reportToEdit = reports.find { it.id == reportId }
                
                if (reportToEdit != null) {
                    NewReportScreen(
                        initialReport = reportToEdit,
                        onSave = { report ->
                            scope.launch {
                                reportRepository.saveReport(report)
                                navController.popBackStack()
                            }
                        },
                        onCancel = { navController.popBackStack() }
                    )
                }
            }
            composable("reports") {
                DashboardScreen(
                    reports = reports,
                    onCreateReport = { navController.navigate("create_report") },
                    onReportClick = { report -> navController.navigate("edit_report/${report.id}") }
                )
            }
            composable("clients") {
                ManageClientsScreen(
                    clients = clients,
                    onSave = { scope.launch { clientRepository.saveClient(it) } },
                    onDelete = { scope.launch { clientRepository.deleteClient(it) } },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
