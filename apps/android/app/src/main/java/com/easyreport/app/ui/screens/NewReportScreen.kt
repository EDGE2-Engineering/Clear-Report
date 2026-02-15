package com.easyreport.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyreport.app.data.models.BoreholeLog
import com.easyreport.app.data.models.Report

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReportScreen(
    onSave: (Report) -> Unit,
    onCancel: () -> Unit
) {
    var report by remember { mutableStateOf(Report()) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Basic Info", "Site Details", "Borehole Logs")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create New Report") },
                actions = {
                    Button(onClick = { onSave(report) }) {
                        Text("Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (selectedTab) {
                    0 -> BasicInfoTab(report) { report = it }
                    1 -> SiteDetailsTab(report) { report = it }
                    2 -> BoreholeLogsTab(report) { report = it }
                }
            }
        }
    }
}

@Composable
fun BasicInfoTab(report: Report, onReportChange: (Report) -> Unit) {
    OutlinedTextField(
        value = report.reportId,
        onValueChange = { onReportChange(report.copy(reportId = it)) },
        label = { Text("Report ID") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = report.projectType,
        onValueChange = { onReportChange(report.copy(projectType = it)) },
        label = { Text("Project Type") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = report.clientName,
        onValueChange = { onReportChange(report.copy(clientName = it)) },
        label = { Text("Client Name") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = report.projectDetails,
        onValueChange = { onReportChange(report.copy(projectDetails = it)) },
        label = { Text("Project Details") },
        modifier = Modifier.fillMaxWidth(),
        minLines = 3
    )
}

@Composable
fun SiteDetailsTab(report: Report, onReportChange: (Report) -> Unit) {
    OutlinedTextField(
        value = report.siteId,
        onValueChange = { onReportChange(report.copy(siteId = it)) },
        label = { Text("Site ID") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = report.siteName,
        onValueChange = { onReportChange(report.copy(siteName = it)) },
        label = { Text("Site Name") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = report.latitude,
        onValueChange = { onReportChange(report.copy(latitude = it)) },
        label = { Text("Latitude") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = report.longitude,
        onValueChange = { onReportChange(report.copy(longitude = it)) },
        label = { Text("Longitude") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = report.surveyDate,
        onValueChange = { onReportChange(report.copy(surveyDate = it)) },
        label = { Text("Survey Date") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BoreholeLogsTab(report: Report, onReportChange: (Report) -> Unit) {
    // Simplified borehole log entry
    val logs = report.boreholeLogs.firstOrNull() ?: emptyList()

    logs.forEachIndexed { index, log ->
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Borehole Log #${index + 1}", style = MaterialTheme.typography.titleMedium)
                    IconButton(onClick = {
                        val newLogs = logs.toMutableList().apply { removeAt(index) }
                        onReportChange(report.copy(boreholeLogs = listOf(newLogs)))
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
                
                OutlinedTextField(
                    value = log.depth,
                    onValueChange = { d ->
                        val newLogs = logs.toMutableList().apply { 
                            this[index] = this[index].copy(depth = d)
                        }
                        onReportChange(report.copy(boreholeLogs = listOf(newLogs)))
                    },
                    label = { Text("Depth (m)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = log.soilType,
                    onValueChange = { s ->
                        val newLogs = logs.toMutableList().apply { 
                            this[index] = this[index].copy(soilType = s)
                        }
                        onReportChange(report.copy(boreholeLogs = listOf(newLogs)))
                    },
                    label = { Text("Soil Type") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    Button(
        onClick = {
            val newLogs = logs.toMutableList().apply { add(BoreholeLog()) }
            onReportChange(report.copy(boreholeLogs = listOf(newLogs)))
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text("Add Borehole Log")
    }
}
