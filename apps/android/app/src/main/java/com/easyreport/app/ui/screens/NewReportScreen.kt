package com.easyreport.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.easyreport.app.data.models.*
import java.util.*
import java.text.SimpleDateFormat

// Reusable components for tabs
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun FieldRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReportScreen(
    initialReport: Report = Report(),
    onSave: (Report) -> Unit,
    onCancel: () -> Unit
) {
    var report by remember { mutableStateOf(initialReport) }
    var selectedTab by remember { mutableIntStateOf(0) }
    var showRandomDataDialog by remember { mutableStateOf(false) }
    
    val tabs = listOf(
        "Basic", "Site", "Survey", "Borehole", "Lab", 
        "Chemical", "Grain", "SBC", "Profile", "Shear", 
        "Point", "Rock", "Final"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report: ${report.reportId.ifEmpty { "New" }}") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.Default.Close, contentDescription = "Cancel")
                    }
                },
                actions = {
                    IconButton(onClick = { showRandomDataDialog = true }) {
                        Icon(Icons.Default.Build, contentDescription = "Random Sample Data")
                    }
                    IconButton(onClick = { onSave(report) }) {
                        Icon(Icons.Default.Check, contentDescription = "Save")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 16.dp
            ) {
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
                    2 -> SurveyTab(report) { report = it }
                    3 -> BoreholeLogsTab(report) { report = it }
                    4 -> LabTestsTab(report) { report = it }
                    5 -> ChemicalAnalysisTab(report) { report = it }
                    6 -> GrainSizeTab(report) { report = it }
                    7 -> SBCDetailsTab(report) { report = it }
                    8 -> SubSoilProfileTab(report) { report = it }
                    9 -> DirectShearTab(report) { report = it }
                    10 -> PointLoadTab(report) { report = it }
                    11 -> RockFormationsTab(report) { report = it }
                    12 -> FinalReportTab(report) { report = it }
                }
                
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }

    if (showRandomDataDialog) {
        AlertDialog(
            onDismissRequest = { showRandomDataDialog = false },
            title = { Text("Fill with Random Data?") },
            text = { Text("This will overwrite any current changes with sample data. Proceed?") },
            confirmButton = {
                TextButton(onClick = {
                    report = generateRandomSampleData()
                    showRandomDataDialog = false
                }) { Text("Yes") }
            },
            dismissButton = {
                TextButton(onClick = { showRandomDataDialog = false }) { Text("Cancel") }
            }
        )
    }
}

@Composable
fun BasicInfoTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionTitle("Project Identification")
        OutlinedTextField(
            value = report.reportId,
            onValueChange = { onUpdate(report.copy(reportId = it)) },
            label = { Text("Report ID (EDGE2/GEO/...)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = report.projectType,
            onValueChange = { onUpdate(report.copy(projectType = it)) },
            label = { Text("Project Type") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = report.projectDetails,
            onValueChange = { onUpdate(report.copy(projectDetails = it)) },
            label = { Text("Project Details") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )
        
        SectionTitle("Client Information")
        OutlinedTextField(
            value = report.clientName,
            onValueChange = { onUpdate(report.copy(clientName = it)) },
            label = { Text("Client Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = report.clientAddress,
            onValueChange = { onUpdate(report.copy(clientAddress = it)) },
            label = { Text("Client Address") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2
        )
    }
}

@Composable
fun SiteDetailsTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionTitle("Location Details")
        FieldRow {
            OutlinedTextField(
                value = report.latitude,
                onValueChange = { onUpdate(report.copy(latitude = it)) },
                label = { Text("Latitude") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = report.longitude,
                onValueChange = { onUpdate(report.copy(longitude = it)) },
                label = { Text("Longitude") },
                modifier = Modifier.weight(1f)
            )
        }
        OutlinedTextField(
            value = report.siteId,
            onValueChange = { onUpdate(report.copy(siteId = it)) },
            label = { Text("Site ID / Anchor ID") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = report.siteName,
            onValueChange = { onUpdate(report.copy(siteName = it)) },
            label = { Text("Site Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = report.siteAddress,
            onValueChange = { onUpdate(report.copy(siteAddress = it)) },
            label = { Text("Site Address") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2
        )
        
        SectionTitle("Investigation Dates")
        OutlinedTextField(
            value = report.surveyDate,
            onValueChange = { onUpdate(report.copy(surveyDate = it)) },
            label = { Text("Survey Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = report.groundWaterTable,
            onValueChange = { onUpdate(report.copy(groundWaterTable = it)) },
            label = { Text("Ground Water Table") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SurveyTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionTitle("IS Codes Used")
        report.isCodes.forEachIndexed { index, kv ->
            FieldRow {
                OutlinedTextField(
                    value = kv.key,
                    onValueChange = { newVal ->
                        val newList = report.isCodes.toMutableList()
                        newList[index] = kv.copy(key = newVal)
                        onUpdate(report.copy(isCodes = newList))
                    },
                    label = { Text("Code") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = kv.value,
                    onValueChange = { newVal ->
                        val newList = report.isCodes.toMutableList()
                        newList[index] = kv.copy(value = newVal)
                        onUpdate(report.copy(isCodes = newList))
                    },
                    label = { Text("Description") },
                    modifier = Modifier.weight(2f)
                )
                IconButton(onClick = {
                    val newList = report.isCodes.toMutableList()
                    newList.removeAt(index)
                    onUpdate(report.copy(isCodes = newList))
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(isCodes = report.isCodes + KeyValue()))
        }) {
            Icon(Icons.Default.Add, contentDescription = null)
            Text("Add IS Code")
        }

        SectionTitle("Survey Report Details")
        report.surveyReport.forEachIndexed { index, kv ->
            FieldRow {
                OutlinedTextField(
                    value = kv.key,
                    onValueChange = { newVal ->
                        val newList = report.surveyReport.toMutableList()
                        newList[index] = kv.copy(key = newVal)
                        onUpdate(report.copy(surveyReport = newList))
                    },
                    label = { Text("Field") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = kv.value,
                    onValueChange = { newVal ->
                        val newList = report.surveyReport.toMutableList()
                        newList[index] = kv.copy(value = newVal)
                        onUpdate(report.copy(surveyReport = newList))
                    },
                    label = { Text("Value") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    val newList = report.surveyReport.toMutableList()
                    newList.removeAt(index)
                    onUpdate(report.copy(surveyReport = newList))
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(surveyReport = report.surveyReport + KeyValue()))
        }) {
            Icon(Icons.Default.Add, contentDescription = null)
            Text("Add Survey Item")
        }
    }
}

@Composable
fun BoreholeLogsTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.boreholeLogs.forEachIndexed { levelIndex, logs ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Borehole Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            val newList = report.boreholeLogs.toMutableList()
                            newList.removeAt(levelIndex)
                            onUpdate(report.copy(boreholeLogs = newList))
                        }) { Icon(Icons.Default.Delete, contentDescription = null) }
                    }
                    
                    logs.forEachIndexed { logIndex, log ->
                        Divider()
                        Text("Sample ${logIndex + 1}", style = MaterialTheme.typography.bodySmall)
                        FieldRow {
                            OutlinedTextField(value = log.depth, onValueChange = { newVal ->
                                val newList = updateBoreholeLog(report.boreholeLogs, levelIndex, logIndex) { it.copy(depth = newVal) }
                                onUpdate(report.copy(boreholeLogs = newList))
                            }, label = { Text("Depth") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = log.soilType, onValueChange = { newVal ->
                                val newList = updateBoreholeLog(report.boreholeLogs, levelIndex, logIndex) { it.copy(soilType = newVal) }
                                onUpdate(report.copy(boreholeLogs = newList))
                            }, label = { Text("Soil Type") }, modifier = Modifier.weight(1f))
                        }
                        FieldRow {
                            OutlinedTextField(value = log.spt1, onValueChange = { newVal ->
                                val newList = updateBoreholeLog(report.boreholeLogs, levelIndex, logIndex) { it.copy(spt1 = newVal) }
                                onUpdate(report.copy(boreholeLogs = newList))
                            }, label = { Text("SPT1") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = log.spt2, onValueChange = { newVal ->
                                val newList = updateBoreholeLog(report.boreholeLogs, levelIndex, logIndex) { it.copy(spt2 = newVal) }
                                onUpdate(report.copy(boreholeLogs = newList))
                            }, label = { Text("SPT2") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = log.spt3, onValueChange = { newVal ->
                                val newList = updateBoreholeLog(report.boreholeLogs, levelIndex, logIndex) { it.copy(spt3 = newVal) }
                                onUpdate(report.copy(boreholeLogs = newList))
                            }, label = { Text("SPT3") }, modifier = Modifier.weight(1f))
                        }
                    }
                    Button(onClick = {
                        val newList = report.boreholeLogs.map { it.toMutableList() }.toMutableList()
                        newList[levelIndex].add(BoreholeLog())
                        onUpdate(report.copy(boreholeLogs = newList))
                    }) { Text("Add Sample to Level") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(boreholeLogs = report.boreholeLogs + listOf(listOf(BoreholeLog()))))
        }) { Text("Add New Borehole Level") }
    }
}

fun updateBoreholeLog(list: List<List<BoreholeLog>>, lIdx: Int, sIdx: Int, update: (BoreholeLog) -> BoreholeLog): List<List<BoreholeLog>> {
    val newList = list.map { it.toMutableList() }.toMutableList()
    newList[lIdx][sIdx] = update(newList[lIdx][sIdx])
    return newList
}

@Composable
fun LabTestsTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.labTestResults.forEachIndexed { levelIndex, results ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Lab Test Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium)
                    results.forEachIndexed { resIndex, res ->
                        Divider()
                        FieldRow {
                            OutlinedTextField(value = res.depth, onValueChange = { newVal ->
                                val newList = updateLabTest(report.labTestResults, levelIndex, resIndex) { it.copy(depth = newVal) }
                                onUpdate(report.copy(labTestResults = newList))
                            }, label = { Text("Depth") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = res.bulkDensity, onValueChange = { newVal ->
                                val newList = updateLabTest(report.labTestResults, levelIndex, resIndex) { it.copy(bulkDensity = newVal) }
                                onUpdate(report.copy(labTestResults = newList))
                            }, label = { Text("Bulk Density") }, modifier = Modifier.weight(1f))
                        }
                        FieldRow {
                            OutlinedTextField(value = res.moistureContent, onValueChange = { newVal ->
                                val newList = updateLabTest(report.labTestResults, levelIndex, resIndex) { it.copy(moistureContent = newVal) }
                                onUpdate(report.copy(labTestResults = newList))
                            }, label = { Text("Moisture %") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = res.specificGravity, onValueChange = { newVal ->
                                val newList = updateLabTest(report.labTestResults, levelIndex, resIndex) { it.copy(specificGravity = newVal) }
                                onUpdate(report.copy(labTestResults = newList))
                            }, label = { Text("Specific Gravity") }, modifier = Modifier.weight(1f))
                        }
                    }
                    Button(onClick = {
                        val newList = report.labTestResults.map { it.toMutableList() }.toMutableList()
                        newList[levelIndex].add(LabTestResult())
                        onUpdate(report.copy(labTestResults = newList))
                    }) { Text("Add Lab Test Row") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(labTestResults = report.labTestResults + listOf(listOf(LabTestResult()))))
        }) { Text("Add Lab Test Level") }
    }
}

fun updateLabTest(list: List<List<LabTestResult>>, lIdx: Int, rIdx: Int, update: (LabTestResult) -> LabTestResult): List<List<LabTestResult>> {
    val newList = list.map { it.toMutableList() }.toMutableList()
    newList[lIdx][rIdx] = update(newList[lIdx][rIdx])
    return newList
}

@Composable
fun ChemicalAnalysisTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.chemicalAnalysis.forEachIndexed { index, analysis ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row {
                        Text("Analysis ${index + 1}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            val newList = report.chemicalAnalysis.toMutableList()
                            newList.removeAt(index)
                            onUpdate(report.copy(chemicalAnalysis = newList))
                        }) { Icon(Icons.Default.Delete, contentDescription = null) }
                    }
                    FieldRow {
                        OutlinedTextField(value = analysis.phValue, onValueChange = { newVal ->
                            val newList = report.chemicalAnalysis.toMutableList()
                            newList[index] = analysis.copy(phValue = newVal)
                            onUpdate(report.copy(chemicalAnalysis = newList))
                        }, label = { Text("PH Value") }, modifier = Modifier.weight(1f))
                        OutlinedTextField(value = analysis.sulphates, onValueChange = { newVal ->
                            val newList = report.chemicalAnalysis.toMutableList()
                            newList[index] = analysis.copy(sulphates = newVal)
                            onUpdate(report.copy(chemicalAnalysis = newList))
                        }, label = { Text("Sulphates") }, modifier = Modifier.weight(1f))
                    }
                    OutlinedTextField(value = analysis.chlorides, onValueChange = { newVal ->
                        val newList = report.chemicalAnalysis.toMutableList()
                        newList[index] = analysis.copy(chlorides = newVal)
                        onUpdate(report.copy(chemicalAnalysis = newList))
                    }, label = { Text("Chlorides") }, modifier = Modifier.fillMaxWidth())
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(chemicalAnalysis = report.chemicalAnalysis + ChemicalAnalysis()))
        }) { Text("Add Chemical Analysis") }
    }
}

@Composable
fun GrainSizeTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.grainSizeAnalysis.forEachIndexed { levelIndex, rows ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Grain Size Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium)
                    rows.forEachIndexed { rowIndex, row ->
                        Divider()
                        FieldRow {
                            OutlinedTextField(value = row.depth, onValueChange = { newVal ->
                                val newList = updateGrainSize(report.grainSizeAnalysis, levelIndex, rowIndex) { it.copy(depth = newVal) }
                                onUpdate(report.copy(grainSizeAnalysis = newList))
                            }, label = { Text("Depth") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = row.sieve1, onValueChange = { newVal ->
                                val newList = updateGrainSize(report.grainSizeAnalysis, levelIndex, rowIndex) { it.copy(sieve1 = newVal) }
                                onUpdate(report.copy(grainSizeAnalysis = newList))
                            }, label = { Text("Sieve 1") }, modifier = Modifier.weight(1f))
                        }
                    }
                    Button(onClick = {
                        val newList = report.grainSizeAnalysis.map { it.toMutableList() }.toMutableList()
                        newList[levelIndex].add(GrainSizeRow())
                        onUpdate(report.copy(grainSizeAnalysis = newList))
                    }) { Text("Add Grain Size Row") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(grainSizeAnalysis = report.grainSizeAnalysis + listOf(listOf(GrainSizeRow()))))
        }) { Text("Add Grain Size Level") }
    }
}

fun updateGrainSize(list: List<List<GrainSizeRow>>, lIdx: Int, rIdx: Int, update: (GrainSizeRow) -> GrainSizeRow): List<List<GrainSizeRow>> {
    val newList = list.map { it.toMutableList() }.toMutableList()
    newList[lIdx][rIdx] = update(newList[lIdx][rIdx])
    return newList
}

@Composable
fun SBCDetailsTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.sbcDetails.forEachIndexed { levelIndex, rows ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("SBC Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium)
                    rows.forEachIndexed { rowIndex, row ->
                        Divider()
                        FieldRow {
                            OutlinedTextField(value = row.depth, onValueChange = { newVal ->
                                val newList = updateSBC(report.sbcDetails, levelIndex, rowIndex) { it.copy(depth = newVal) }
                                onUpdate(report.copy(sbcDetails = newList))
                            }, label = { Text("Depth") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = row.sbcValue, onValueChange = { newVal ->
                                val newList = updateSBC(report.sbcDetails, levelIndex, rowIndex) { it.copy(sbcValue = newVal) }
                                onUpdate(report.copy(sbcDetails = newList))
                            }, label = { Text("SBC Value") }, modifier = Modifier.weight(1f))
                        }
                    }
                    Button(onClick = {
                        val newList = report.sbcDetails.map { it.toMutableList() }.toMutableList()
                        newList[levelIndex].add(SBCRow())
                        onUpdate(report.copy(sbcDetails = newList))
                    }) { Text("Add SBC Row") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(sbcDetails = report.sbcDetails + listOf(listOf(SBCRow()))))
        }) { Text("Add SBC Level") }
    }
}

fun updateSBC(list: List<List<SBCRow>>, lIdx: Int, rIdx: Int, update: (SBCRow) -> SBCRow): List<List<SBCRow>> {
    val newList = list.map { it.toMutableList() }.toMutableList()
    newList[lIdx][rIdx] = update(newList[lIdx][rIdx])
    return newList
}

@Composable
fun SubSoilProfileTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.subSoilProfile.forEachIndexed { levelIndex, rows ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Sub-Soil Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium)
                    rows.forEachIndexed { rowIndex, row ->
                        Divider()
                        OutlinedTextField(value = row.depth, onValueChange = { newVal ->
                            val newList = updateSubSoil(report.subSoilProfile, levelIndex, rowIndex) { it.copy(depth = newVal) }
                            onUpdate(report.copy(subSoilProfile = newList))
                        }, label = { Text("Depth Range") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = row.description, onValueChange = { newVal ->
                            val newList = updateSubSoil(report.subSoilProfile, levelIndex, rowIndex) { it.copy(description = newVal) }
                            onUpdate(report.copy(subSoilProfile = newList))
                        }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                    }
                    Button(onClick = {
                        val newList = report.subSoilProfile.map { it.toMutableList() }.toMutableList()
                        newList[levelIndex].add(SubSoilRow())
                        onUpdate(report.copy(subSoilProfile = newList))
                    }) { Text("Add Profile Row") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(subSoilProfile = report.subSoilProfile + listOf(listOf(SubSoilRow()))))
        }) { Text("Add Sub-Soil Level") }
    }
}

fun updateSubSoil(list: List<List<SubSoilRow>>, lIdx: Int, rIdx: Int, update: (SubSoilRow) -> SubSoilRow): List<List<SubSoilRow>> {
    val newList = list.map { it.toMutableList() }.toMutableList()
    newList[lIdx][rIdx] = update(newList[lIdx][rIdx])
    return newList
}

@Composable
fun DirectShearTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.directShearResults.forEachIndexed { levelIndex, tests ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Direct Shear Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium)
                    tests.forEachIndexed { testIndex, test ->
                        Divider()
                        FieldRow {
                            OutlinedTextField(value = test.depthOfSample, onValueChange = { newVal ->
                                val newList = updateShear(report.directShearResults, levelIndex, testIndex) { it.copy(depthOfSample = newVal) }
                                onUpdate(report.copy(directShearResults = newList))
                            }, label = { Text("Depth") }, modifier = Modifier.weight(1f))
                            OutlinedTextField(value = test.cValue, onValueChange = { newVal ->
                                val newList = updateShear(report.directShearResults, levelIndex, testIndex) { it.copy(cValue = newVal) }
                                onUpdate(report.copy(directShearResults = newList))
                            }, label = { Text("C Value") }, modifier = Modifier.weight(1f))
                        }
                    }
                    Button(onClick = {
                        val newList = report.directShearResults.map { it.toMutableList() }.toMutableList()
                        newList[levelIndex].add(DirectShearTest())
                        onUpdate(report.copy(directShearResults = newList))
                    }) { Text("Add Shear Test") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(directShearResults = report.directShearResults + listOf(listOf(DirectShearTest()))))
        }) { Text("Add Shear Level") }
    }
}

fun updateShear(list: List<List<DirectShearTest>>, lIdx: Int, tIdx: Int, update: (DirectShearTest) -> DirectShearTest): List<List<DirectShearTest>> {
    val newList = list.map { it.toMutableList() }.toMutableList()
    newList[lIdx][tIdx] = update(newList[lIdx][tIdx])
    return newList
}

@Composable
fun PointLoadTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.pointLoadStrength.forEachIndexed { levelIndex, tests ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Point Load Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium)
                    tests.forEachIndexed { testIndex, test ->
                        Divider()
                        OutlinedTextField(value = test.depth, onValueChange = { newVal ->
                            val newList = updatePointLoad(report.pointLoadStrength, levelIndex, testIndex) { it.copy(depth = newVal) }
                            onUpdate(report.copy(pointLoadStrength = newList))
                        }, label = { Text("Depth") }, modifier = Modifier.fillMaxWidth())
                    }
                    Button(onClick = {
                        val newList = report.pointLoadStrength.map { it.toMutableList() }.toMutableList()
                        newList[levelIndex].add(PointLoadTest())
                        onUpdate(report.copy(pointLoadStrength = newList))
                    }) { Text("Add Point Load Row") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(pointLoadStrength = report.pointLoadStrength + listOf(listOf(PointLoadTest()))))
        }) { Text("Add Point Load Level") }
    }
}

fun updatePointLoad(list: List<List<PointLoadTest>>, lIdx: Int, tIdx: Int, update: (PointLoadTest) -> PointLoadTest): List<List<PointLoadTest>> {
    val newList = list.map { it.toMutableList() }.toMutableList()
    newList[lIdx][tIdx] = update(newList[lIdx][tIdx])
    return newList
}

@Composable
fun RockFormationsTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        report.foundationRockFormations.forEachIndexed { levelIndex, level ->
            Card {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Rock Level ${levelIndex + 1}", style = MaterialTheme.typography.titleMedium)
                    level.rows.forEachIndexed { rowIndex, row ->
                        Divider()
                        OutlinedTextField(value = row.rock, onValueChange = { newVal ->
                            val newList = report.foundationRockFormations.toMutableList()
                            val newRows = level.rows.toMutableList()
                            newRows[rowIndex] = row.copy(rock = newVal)
                            newList[levelIndex] = level.copy(rows = newRows)
                            onUpdate(report.copy(foundationRockFormations = newList))
                        }, label = { Text("Rock Type") }, modifier = Modifier.fillMaxWidth())
                    }
                    Button(onClick = {
                        val newList = report.foundationRockFormations.toMutableList()
                        val newRows = level.rows.toMutableList()
                        newRows.add(RockFormationRow())
                        newList[levelIndex] = level.copy(rows = newRows)
                        onUpdate(report.copy(foundationRockFormations = newList))
                    }) { Text("Add Rock Row") }
                }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(foundationRockFormations = report.foundationRockFormations + FoundationRockLevel(listOf(RockFormationRow()))))
        }) { Text("Add Rock Level") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalReportTab(report: Report, onUpdate: (Report) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionTitle("Conclusions")
        report.conclusions.forEachIndexed { index, valueWrapper ->
            FieldRow {
                OutlinedTextField(
                    value = valueWrapper.value,
                    onValueChange = { newVal ->
                        val newList = report.conclusions.toMutableList()
                        newList[index] = valueWrapper.copy(value = newVal)
                        onUpdate(report.copy(conclusions = newList))
                    },
                    label = { Text("Conclusion ${index + 1}") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    val newList = report.conclusions.toMutableList()
                    newList.removeAt(index)
                    onUpdate(report.copy(conclusions = newList))
                }) { Icon(Icons.Default.Delete, contentDescription = null) }
            }
        }
        Button(onClick = {
            onUpdate(report.copy(conclusions = report.conclusions + ValueWrapper()))
        }) { Text("Add Conclusion") }

        SectionTitle("Final Recommendations")
        OutlinedTextField(
            value = report.recommendations,
            onValueChange = { onUpdate(report.copy(recommendations = it)) },
            label = { Text("Recommendations") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 5
        )
        
        SectionTitle("Status")
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Report Status:")
            FilterChip(
                selected = report.status == "Draft",
                onClick = { onUpdate(report.copy(status = "Draft")) },
                label = { Text("Draft") }
            )
            FilterChip(
                selected = report.status == "Completed",
                onClick = { onUpdate(report.copy(status = "Completed")) },
                label = { Text("Completed") }
            )
        }
    }
}

fun generateRandomSampleData(): Report {
    val projectTypes = listOf("Commercial Building (G+4)", "Residential Apartment (Stilt+10)", "Industrial Shed", "Bridge Abutment", "Overhead Water Tank")
    val clients = listOf("Prestige Constructions", "Brigade Group", "Sobha Ltd", "Total Environment", "Adani Realty")
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val randomId = (100..999).random()

    val soilTypes = listOf(
        "Filled-up Soil", "Brownish Gravelly Soil", "Grayish Gravelly Soil", "Open Rock",
        "Brownish Silty Sand (SM)", "Brownish Silt (ML)", "Grayish Silt (ML)", "Light Yellowish Silt (ML)",
        "Grayish Silty Sand (SM)", "Grayish Silty Gravels (GM)", "Brownish Silty Gravel (GM)",
        "Grayish Clayey Gravel (GC)", "Brownish Clayey Gravel (GC)", "Poorly Graded Gravel (GP)",
        "Poorly Graded Sand (SP)", "Brownish Clayey Sand (SC)", "Grayish Clayey Sand (SC)",
        "Brownish Clay of Low Plasticity (CL)", "Grayish Clay of Low Plasticity (CL)",
        "Grayish Clay of High Plasticity (CH)", "Black Clay of High Plasticity (CH)",
        "Soft Disintegrated Weathered Rock", "Weathered Rock", "Lateritic Rock", "Laterite Hard Gravels",
        "Rock Pebbles/Hard Morum", "Basalt Rock", "Fractured Basalt Rock", "Hard Rock",
        "Medium Hard Rock", "Reddish Gravelly Soil", "Reddish Silty Sand (SM)", "Reddish Silty Gravel (GM)",
        "Reddish Silt (ML)", "Reddish Clayey Gravel (GC)", "Reddish Clayey Sand (SC)",
        "Reddish Clay of Low Plasticity (CL)", "Others"
    )

    val numBH = (2..3).random()
    
    val boreholeLogs = (1..numBH).map {
        (1..4).mapIndexed { i, _ ->
            BoreholeLog(
                depth = (i * 1.5 + 1.5).toString(),
                natureOfSampling = if (i % 2 == 0) "Undisturbed" else "Disturbed",
                soilType = soilTypes.random(),
                waterTable = Math.random() > 0.8,
                spt1 = (10..20).random().toString(),
                spt2 = (15..25).random().toString(),
                spt3 = (20..30).random().toString(),
                shearParameters = ShearParameters(
                    cValue = String.format("%.2f", 0.1 + Math.random() * 0.5),
                    phiValue = (15..30).random().toString()
                ),
                sbc = (150..300).random().toString()
            )
        }
    }

    val labTestResults = boreholeLogs.map { bh ->
        bh.map { log ->
            LabTestResult(
                depth = log.depth,
                bulkDensity = String.format("%.2f", 1.6 + Math.random() * 0.4),
                moistureContent = String.format("%.1f", 10.0 + Math.random() * 20.0),
                grainSizeDistribution = GrainSizeDistribution(
                    gravel = String.format("%.1f", 5.0 + Math.random() * 15.0),
                    sand = String.format("%.1f", 20.0 + Math.random() * 40.0),
                    siltAndClay = String.format("%.1f", 30.0 + Math.random() * 30.0)
                ),
                atterbergLimits = AtterbergLimits(
                    liquidLimit = String.format("%.1f", 30.0 + Math.random() * 20.0),
                    plasticLimit = String.format("%.1f", 15.0 + Math.random() * 10.0),
                    plasticityIndex = String.format("%.1f", 10.0 + Math.random() * 15.0)
                ),
                specificGravity = String.format("%.2f", 2.6 + Math.random() * 0.1),
                freeSwellIndex = (10..30).random().toString()
            )
        }
    }

    val grainSizeAnalysis = boreholeLogs.map { bh ->
        bh.take(2).map { log ->
            GrainSizeRow(
                depth = log.depth,
                sieve1 = String.format("%.1f", 95.0 + Math.random() * 5.0),
                sieve2 = String.format("%.1f", 85.0 + Math.random() * 10.0),
                sieve3 = String.format("%.1f", 75.0 + Math.random() * 10.0),
                sieve4 = String.format("%.1f", 65.0 + Math.random() * 10.0),
                sieve5 = String.format("%.1f", 55.0 + Math.random() * 10.0),
                sieve6 = String.format("%.1f", 45.0 + Math.random() * 10.0),
                sieve7 = String.format("%.1f", 35.0 + Math.random() * 10.0),
                sieve8 = String.format("%.1f", 25.0 + Math.random() * 10.0),
                sieve9 = String.format("%.1f", 25.0 + Math.random() * 10.0)
            )
        }
    }

    val sbcDetails = boreholeLogs.map { bh ->
        bh.map { log ->
            SBCRow(
                depth = log.depth,
                footingDimension = "1.5m x 1.5m",
                useForReport = Math.random() > 0.5,
                sbcValue = log.sbc
            )
        }
    }

    val subSoilProfile = boreholeLogs.mapIndexed { bhIdx, bh ->
        bh.take(2).mapIndexed { i, log ->
            SubSoilRow(
                depth = if (i == 0) "0.0 to ${log.depth}" else "${bh[i - 1].depth} to ${log.depth}",
                description = log.soilType
            )
        }
    }

    val directShearResults = (1..2).map { levelIdx ->
        (1..2).map { testIdx ->
            DirectShearTest(
                shearBoxSize = "6cm x 6cm",
                depthOfSample = String.format("%.1f", 1.5 + levelIdx * 3.0 + testIdx * 1.5),
                cValue = String.format("%.2f", 0.15 + Math.random() * 0.1),
                phiValue = (25..30).random().toString(),
                stressReadings = listOf(
                    StressReading("0.5", String.format("%.2f", 0.3 + Math.random() * 0.1)),
                    StressReading("1.0", String.format("%.2f", 0.6 + Math.random() * 0.1)),
                    StressReading("1.5", String.format("%.2f", 0.9 + Math.random() * 0.1))
                )
            )
        }
    }

    val pointLoadStrength = boreholeLogs.map { bh ->
        bh.take(2).map { log ->
            PointLoadTest(
                depth = log.depth,
                readings = (1..3).map {
                    PointLoadReading(
                        loadAtFailure = String.format("%.1f", 4.0 + Math.random() * 5.0),
                        d50 = "50",
                        d = "50",
                        ucs = (40..90).random().toString()
                    )
                }
            )
        }
    }

    val pointLoadStrengthLump = boreholeLogs.map { bh ->
        bh.take(2).map { log ->
            PointLoadLumpTest(
                depth = log.depth,
                readings = (1..3).map {
                    PointLoadLumpReading(
                        loadAtFailure = String.format("%.1f", 3.0 + Math.random() * 4.0),
                        d50 = "45",
                        d = "45",
                        w = "40",
                        ucs = (30..70).random().toString()
                    )
                }
            )
        }
    }

    val foundationRockFormations = (1..2).map {
        FoundationRockLevel(
            rows = (1..3).mapIndexed { i, _ ->
                RockFormationRow(
                    rock = when (i) { 0 -> "Granite"; 1 -> "Gneiss"; else -> "Schist" },
                    strength = if (i == 0) "Hard" else "Medium",
                    rqd = (50..90).random().toString() + "%",
                    spacingDiscontinuity = (100..300).random().toString() + "mm",
                    conditionOfDiscontinuity = "Tight",
                    gwtCondition = "Dry",
                    discontinuityOrientation = "Horizontal",
                    rockGrade = if (i == 0) "Grade II" else "Grade III",
                    inferredNetSbp = (2000..3000).random().toString() + " kN/m²"
                )
            }
        )
    }

    val chemicalAnalysis = (1..2).map {
        ChemicalAnalysis(
            phValue = String.format("%.1f", 6.5 + Math.random() * 2.0),
            sulphates = String.format("%.1f", 20.0 + Math.random() * 30.0),
            chlorides = String.format("%.1f", 50.0 + Math.random() * 50.0)
        )
    }

    return Report(
        projectType = projectTypes.random(),
        reportId = "EDGE2/GEO/$year/$randomId",
        projectDetails = "GBT 40m - Multi-storey structure",
        clientName = clients.random(),
        clientAddress = "123, MG Road, Residency Area, Bengaluru - 560001",
        latitude = String.format("%.6f", 12.9 + Math.random() * 0.1),
        longitude = String.format("%.6f", 77.5 + Math.random() * 0.1),
        siteId = "SITE-${(1000..9999).random()}",
        anchorId = "ANC-${(100..999).random()}",
        siteName = "Project Green Meadows",
        siteAddress = "Sy No. 45/2, Ullal Village, Yeshwanthpur Hobli, Bengaluru North",
        surveyDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()),
        groundWaterTable = "Not Encountered",
        recommendations = "Based on the investigation, isolated foundation is recommended. Protective drainage should be provided. Fill material should be well compacted.",
        depthOfFoundation = "1.5",
        boreholeLogs = boreholeLogs,
        labTestResults = labTestResults,
        grainSizeAnalysis = grainSizeAnalysis,
        sbcDetails = sbcDetails,
        subSoilProfile = subSoilProfile,
        directShearResults = directShearResults,
        pointLoadStrength = pointLoadStrength,
        pointLoadStrengthLump = pointLoadStrengthLump,
        foundationRockFormations = foundationRockFormations,
        chemicalAnalysis = chemicalAnalysis
    )
}
