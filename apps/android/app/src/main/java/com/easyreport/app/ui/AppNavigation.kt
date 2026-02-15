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
import com.easyreport.app.ui.components.DashboardScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("dashboard") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentRoute == "dashboard",
                    onClick = { 
                        currentRoute = "dashboard"
                        navController.navigate("dashboard") 
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Reports") },
                    label = { Text("Reports") },
                    selected = currentRoute == "reports",
                    onClick = { 
                        currentRoute = "reports"
                        navController.navigate("reports") 
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Clients") },
                    label = { Text("Clients") },
                    selected = currentRoute == "clients",
                    onClick = { 
                        currentRoute = "clients"
                        navController.navigate("clients") 
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
                    reports = listOf(
                        Report(reportId = "REP-01", clientName = "Example Client", surveyDate = "2024-02-15", status = "Draft")
                    ),
                    onCreateReport = { navController.navigate("create_report") }
                )
            }
            composable("create_report") {
                com.easyreport.app.ui.screens.NewReportScreen(
                    onSave = { report ->
                        // In a real app, we'd use a ViewModel and Repository here
                        println("Saving report: ${report.reportId}")
                        navController.popBackStack()
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
            composable("reports") {
                // Reports List
                Text("Reports List Screen", modifier = Modifier.padding(innerPadding))
            }
            composable("clients") {
                // Clients List
                Text("Clients List Screen", modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
