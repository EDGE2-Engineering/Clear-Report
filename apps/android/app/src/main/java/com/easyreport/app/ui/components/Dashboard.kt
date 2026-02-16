package com.easyreport.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyreport.app.data.models.Report

@Composable
fun DashboardScreen(
    reports: List<Report>,
    onCreateReport: () -> Unit,
    onReportClick: (Report) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateReport) {
                Icon(Icons.Default.Add, contentDescription = "Create Report")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Recent Reports", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(reports) { report ->
                    ReportItem(report, onClick = { onReportClick(report) })
                }
            }
        }
    }
}

@Composable
fun ReportItem(report: Report, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(report.reportId.ifEmpty { "New Report" }, style = MaterialTheme.typography.titleMedium)
                Text(report.status, color = MaterialTheme.colorScheme.primary)
            }
            Text(report.surveyDate, style = MaterialTheme.typography.bodySmall)
        }
    }
}
