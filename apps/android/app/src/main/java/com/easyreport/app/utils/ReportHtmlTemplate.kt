package com.easyreport.app.utils

import com.easyreport.app.data.models.Report

object ReportHtmlTemplate {
    fun generate(report: Report): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    @page {
                        size: A4;
                        margin: 0;
                    }
                    body {
                        font-family: 'Times New Roman', serif;
                        margin: 0;
                        padding: 0;
                        background-color: #f0f0f0;
                    }
                    .page-wrapper {
                        background: white;
                        width: 210mm;
                        min-height: 297mm;
                        padding: 20mm;
                        margin: 10mm auto;
                        box-shadow: 0 0 10px rgba(0,0,0,0.1);
                        box-sizing: border-box;
                        position: relative;
                        overflow: hidden;
                    }
                    @media print {
                        body { background: none; }
                        .page-wrapper {
                            margin: 0;
                            box-shadow: none;
                            page-break-after: always;
                            width: 210mm;
                            height: 297mm;
                        }
                    }
                    .header-line {
                        border-bottom: 3px solid #1a237e;
                        margin-bottom: 20px;
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        padding-bottom: 10px;
                    }
                    .logo-img { height: 60px; }
                    .report-ref { font-size: 10pt; font-weight: bold; color: #1a237e; }
                    
                    h1 { color: #1a237e; text-align: center; font-size: 22pt; margin-top: 50px; text-transform: uppercase; }
                    h2 { color: #1a237e; font-size: 16pt; border-bottom: 2px solid #eee; padding-bottom: 5px; margin-top: 30px; }
                    h3 { color: #333; font-size: 13pt; margin-top: 20px; }
                    
                    p { line-height: 1.6; font-size: 11pt; text-align: justify; }
                    
                    .front-page-center {
                        text-align: center;
                        margin-top: 100px;
                    }
                    .project-title {
                        font-size: 18pt;
                        font-weight: bold;
                        margin: 20px 0;
                        color: #333;
                    }
                    .client-box {
                        margin-top: 150px;
                        border: 1px solid #ddd;
                        padding: 20px;
                        background: #f9f9f9;
                    }
                    
                    table { width: 100%; border-collapse: collapse; margin-top: 15px; }
                    th, td { border: 1px solid #000; padding: 6px; text-align: left; font-size: 9pt; }
                    th { background-color: #e8eaf6; font-weight: bold; }
                    
                    .footer {
                        position: absolute;
                        bottom: 20mm;
                        left: 20mm;
                        right: 20mm;
                        font-size: 9pt;
                        border-top: 1px solid #eee;
                        padding-top: 10px;
                        display: flex;
                        justify-content: space-between;
                    }
                    
                    .section-num {
                        background: #1a237e;
                        color: white;
                        padding: 0 8px;
                        margin-right: 10px;
                        display: inline-block;
                    }
                </style>
            </head>
            <body>
                <!-- Page 1: Front Page -->
                <div class="page-wrapper">
                    <div class="header-line">
                        <img src="edge2-logo.png" class="logo-img" onerror="this.style.display='none'">
                        <div class="report-ref">Ref: ${report.reportId}</div>
                    </div>
                    
                    <h1>GEOTECHNICAL INVESTIGATION REPORT</h1>
                    
                    <div class="front-page-center">
                        <p>PROPOSED CONSTRUCTION OF</p>
                        <div class="project-title">${report.projectType}</div>
                        <p>AT</p>
                        <p><b>${report.siteName}, ${report.siteAddress}</b></p>
                    </div>
                    
                    <div class="client-box">
                        <p style="text-align: center; margin-bottom: 5px;"><i>Submitted to:</i></p>
                        <h2 style="text-align: center; margin-top: 0; border: none;">${report.clientName}</h2>
                        <p style="text-align: center;">${report.clientAddress}</p>
                    </div>

                    <div class="footer">
                        <span>Date: ${report.createdOn}</span>
                        <span>EDGE2 Engineering Solutions India Pvt. Ltd.</span>
                    </div>
                </div>

                <!-- Page 1.1: Table of Contents -->
                <div class="page-wrapper">
                    <h2 style="text-align: center;">TABLE OF CONTENTS</h2>
                    <ul style="list-style: none; padding: 0; margin-top: 50px; border-top: 1px solid #eee;">
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between;">
                            <span>1.0 INTRODUCTION</span><span>2</span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between;">
                            <span>2.0 PROJECT DESCRIPTION</span><span>2</span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between;">
                            <span>3.0 SCOPE OF WORK</span><span>2</span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between;">
                            <span>4.0 FIELD INVESTIGATIONS</span><span>3</span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between;">
                            <span>5.0 RESULTS & DISCUSSIONS</span><span>4</span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between;">
                            <span>6.0 RECOMMENDATIONS & CONCLUSIONS</span><span>-</span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between; font-weight: bold; margin-top: 20px;">
                            <span>ANNEXURES</span><span></span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between; padding-left: 20px;">
                            <span>Borehole Log Sheets</span><span></span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between; padding-left: 20px;">
                            <span>Summary of Lab Results</span><span></span>
                        </li>
                        <li style="padding: 10px 0; border-bottom: 1px solid #eee; display: flex; justify-content: space-between; padding-left: 20px;">
                            <span>Site Photographs</span><span></span>
                        </li>
                    </ul>
                    <div class="footer">
                        <span>Table of Contents</span>
                        <span>Report Ref: ${report.reportId}</span>
                    </div>
                </div>

                <!-- Page 2: Introduction & Site Details -->
                <div class="page-wrapper">
                    <h2><span class="section-num">1.0</span> INTRODUCTION</h2>
                    <p>
                        Geotechnical investigations are essential for evaluating the sub-surface conditions and determining the suitabilities of soil and rock for the foundation of the proposed structures. 
                        EDGE2 Engineering Solutions India Pvt. Ltd. was entrusted with the task of carrying out the geotechnical investigation for the project: <b>${report.projectType}</b>.
                    </p>
                    
                    <h2><span class="section-num">2.0</span> PROJECT DESCRIPTION</h2>
                    <p>${report.projectDetails.ifEmpty { "The project consists of the construction of a new structure at the site specified. The investigation aims to provide data for the design of a safe and economical foundation system." }}</p>

                    <h2><span class="section-num">3.0</span> SCOPE OF WORK</h2>
                    <p>The scope of geotechnical investigation included the following:</p>
                    <ul>
                        <li>Mobilization of equipment and personnel to the site.</li>
                        <li>Sinking of borehole(s) at designated location(s).</li>
                        <li>Conducting Standard Penetration Tests (SPT) at regular intervals.</li>
                        <li>Collection of disturbed and undisturbed soil samples.</li>
                        <li>Observation and recording of the Ground Water Table (GWT).</li>
                        <li>Laboratory testing on collected samples.</li>
                        <li>Analysis of data and preparation of a technical report with foundation recommendations.</li>
                    </ul>

                    <div class="footer">
                        <span>Report Ref: ${report.reportId}</span>
                        <span>Page 2</span>
                    </div>
                </div>

                ${generateFieldInvestigations(report)}
                ${generateResultsPage(report)}
                ${generateBoreholeLogs(report)}
                ${generateDirectShearResults(report)}
                ${generateSBCDetails(report)}
                ${generateSitePhotos(report)}
                ${generateRecommendations(report)}
            </body>
            </html>
        """.trimIndent()
    }

    private fun generateFieldInvestigations(report: Report): String {
        return """
            <div class="page-wrapper">
                <h2><span class="section-num">4.0</span> FIELD INVESTIGATIONS</h2>
                <p>The field investigation work was carried out as per relevant Indian Standards. The details are summarized below.</p>
                
                <h3>4.1 Drilling / Boring</h3>
                <p>Boreholes of 100/150mm diameter were sunk to explore the sub-surface strata. The depth of exploration and borehole locations are listed in Table 4.1.</p>
                
                <h3 style="font-size: 10pt; color: #666; margin-bottom: 5px;">Table 4.1: Details of Boreholes</h3>
                <table>
                    <thead>
                        <tr style="background: #f2f2f2;">
                            <th>BH No.</th>
                            <th>Location / Description</th>
                            <th>Total Depth (m)</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${report.boreholeLogs.mapIndexed { i, bh -> 
                            "<tr><td>BH ${i + 1}</td><td>Project Site</td><td>${bh.lastOrNull()?.depth ?: "-"}</td></tr>"
                        }.joinToString("")}
                    </tbody>
                </table>

                <h3>4.2 Standard Penetration Tests (SPT)</h3>
                <p>Standard Penetration Tests (SPT) were conducted using split spoon sampler as per IS-2131-1981 at various depths in Boreholes to determine (N>100 blows for 30 cm penetrations) whichever encounters early.</p>
                
                <h3 style="font-size: 10pt; color: #666; margin-bottom: 5px;">Table 4.2 Details of SPT Tests conducted in Boreholes</h3>
                <table>
                    <thead>
                        <tr style="background: #f2f2f2;">
                            <th>BH No.</th>
                            <th>SPT Depth / Levels (m)</th>
                            <th>Remarks</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${report.boreholeLogs.mapIndexed { i, bh -> 
                            val depths = bh.filter { it.spt1.isNotEmpty() || it.spt2.isNotEmpty() }.joinToString(", ") { it.depth }
                            "<tr><td>BH ${i + 1}</td><td>${depths.ifEmpty { "-" }}</td><td>SPT</td></tr>"
                        }.joinToString("")}
                    </tbody>
                </table>

                <h3>4.3 Ground Water Table</h3>
                <p>${if (report.groundWaterTable.lowercase() == "found") "Ground water table was noticed during the investigation as follows." else "No ground water table noticed up to explored depth during the investigation period."}</p>
                
                <h3 style="font-size: 10pt; color: #666; margin-bottom: 5px;">Table 4.3 Details of Ground Water Table noticed</h3>
                <table>
                    <thead>
                        <tr style="background: #f2f2f2;">
                            <th>BH No.</th>
                            <th>Depth (m)</th>
                            <th>Remarks</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${report.boreholeLogs.mapIndexed { i, bh -> 
                            val wt = bh.find { it.waterTable }
                            if (wt != null) "<tr><td>BH ${i + 1}</td><td>${wt.depth}</td><td>Noticed at time of survey</td></tr>"
                            else "<tr><td>BH ${i + 1}</td><td>-</td><td>Not found</td></tr>"
                        }.joinToString("")}
                    </tbody>
                </table>

                <div class="footer">
                    <span>Report Ref: ${report.reportId}</span>
                    <span>Page 3</span>
                </div>
            </div>
        """.trimIndent()
    }

    private fun generateResultsPage(report: Report): String {
        return """
            <div class="page-wrapper">
                <h2><span class="section-num">5.0</span> RESULTS & DISCUSSIONS</h2>
                <p>The results of field and laboratory tests are presented in the following tables and annexures.</p>
                
                <h3>5.1 Soil Profile</h3>
                <p>Based on the borehole logs, the general sub-surface stratification is detailed in the borehole log sheets.</p>

                <h3>5.2 Chemical Analysis of Soil</h3>
                <table>
                    <thead>
                        <tr><th>Parameter</th><th>Result</th><th>Unit</th></tr>
                    </thead>
                    <tbody>
                        ${report.chemicalAnalysis.map { 
                            """
                            <tr><td>pH Value</td><td>${it.phValue}</td><td>-</td></tr>
                            <tr><td>Chlorides</td><td>${it.chlorides}</td><td>%</td></tr>
                            <tr><td>Sulphates</td><td>${it.sulphates}</td><td>%</td></tr>
                            """.trimIndent()
                        }.joinToString("")}
                    </tbody>
                </table>

                <div class="footer">
                    <span>Report Ref: ${report.reportId}</span>
                    <span>Page 4</span>
                </div>
            </div>
        """.trimIndent()
    }

    private fun generateBoreholeLogs(report: Report): String {
        val html = StringBuilder()
        
        // Detailed Borehole Log Sheets (Annexure Style)
        report.boreholeLogs.forEachIndexed { i, bh ->
            html.append("""
                <div class="page-wrapper">
                    <h3 style="color: #1a237e; border-bottom: 2px solid #1a237e; padding-bottom: 5px;">Borehole Log, Sub-Soil Profile and Laboratory Test Results: (BH ${i + 1})</h3>
                    
                    <table style="margin-top: 10px; border: 2px solid #000;">
                        <thead>
                            <tr style="background: #f2f2f2;">
                                <th colspan="3">Project Name:</th><td colspan="7">${report.projectType}</td>
                                <th colspan="2">Date:</th><td colspan="3">${report.surveyDate}</td>
                            </tr>
                            <tr style="background: #e8eaf6; text-align: center;">
                                <th rowspan="2">Depth (m)</th>
                                <th rowspan="2">Legend</th>
                                <th rowspan="2">Soil Description</th>
                                <th rowspan="2">Water</th>
                                <th colspan="4">SPT / Blows</th>
                                <th colspan="2">Shear</th>
                                <th rowspan="2">Core (m)</th>
                                <th rowspan="2">CR%</th>
                                <th rowspan="2">RQD%</th>
                                <th rowspan="2">SBC</th>
                            </tr>
                            <tr style="background: #e8eaf6; text-align: center;">
                                <th>15</th><th>30</th><th>45</th><th>N</th>
                                <th>C</th><th>Φ</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${generateDetailedBoreholeRows(bh)}
                        </tbody>
                    </table>
                    
                    <div class="footer">
                        <span>BH ${i + 1} Log</span>
                        <span>Report Ref: ${report.reportId}</span>
                    </div>
                </div>
            """.trimIndent())
        }

        // Summary of Laboratory Test Results Page
        report.labTestResults.forEachIndexed { i, results ->
            if (results.isNotEmpty()) {
                html.append("""
                    <div class="page-wrapper">
                        <h3 style="color: #1a237e ; border-bottom: 2px solid #1a237e; padding-bottom: 5px;">Summary of Laboratory Test Results: (BH ${i+1})</h3>
                        <table style="margin-top: 10px;">
                            <thead>
                                <tr style="background: #e8eaf6; text-align: center;">
                                    <th rowspan="2">Depth (m)</th>
                                    <th rowspan="2">Bulk Density (g/cc)</th>
                                    <th rowspan="2">Moisture content (%)</th>
                                    <th colspan="3">Grain Size Distribution (%)</th>
                                    <th colspan="3">Atterberg Limits (%)</th>
                                    <th rowspan="2">Specific Gravity</th>
                                    <th rowspan="2">Free Swell Index (%)</th>
                                </tr>
                                <tr style="background: #e8eaf6; text-align: center;">
                                    <th>Gravel</th><th>Sand</th><th>Silt & Clay</th>
                                    <th>LL</th><th>PL</th><th>PI</th>
                                </tr>
                            </thead>
                            <tbody>
                                ${generateLabResultRows(results)}
                            </tbody>
                        </table>
                        <div class="footer">
                            <span>BH ${i+1} Lab Summary</span>
                            <span>Report Ref: ${report.reportId}</span>
                        </div>
                    </div>
                """.trimIndent())
            }
        }
        
        return html.toString()
    }

    private fun generateDetailedBoreholeRows(bh: List<com.easyreport.app.data.models.BoreholeLog>): String {
        return bh.joinToString("") { log ->
            val nValue = (log.spt2.toIntOrNull() ?: 0) + (log.spt3.toIntOrNull() ?: 0)
            """
            <tr>
                <td align="center">${log.depth}</td>
                <td style="background: #eee;"></td>
                <td style="font-size: 8pt;">${log.soilType}</td>
                <td align="center">${if (log.waterTable) "Found" else "-"}</td>
                <td align="center">${log.spt1}</td>
                <td align="center">${log.spt2}</td>
                <td align="center">${log.spt3}</td>
                <td align="center"><b>${if (nValue > 0) nValue else "-"}</b></td>
                <td align="center">${log.shearParameters.cValue}</td>
                <td align="center">${log.shearParameters.phiValue}</td>
                <td align="center">${log.coreLength}</td>
                <td align="center">${log.coreRecovery}</td>
                <td align="center">${log.rqd}</td>
                <td align="center"><b>${log.sbc}</b></td>
            </tr>
            """.trimIndent()
        }
    }

    private fun generateLabResultRows(results: List<com.easyreport.app.data.models.LabTestResult>): String {
        return results.joinToString("") { res ->
            """
            <tr>
                <td align="center">${res.depth}</td>
                <td align="center">${res.bulkDensity}</td>
                <td align="center">${res.moistureContent}</td>
                <td align="center">${res.grainSizeDistribution.gravel}</td>
                <td align="center">${res.grainSizeDistribution.sand}</td>
                <td align="center">${res.grainSizeDistribution.siltAndClay}</td>
                <td align="center">${res.atterbergLimits.liquidLimit}</td>
                <td align="center">${res.atterbergLimits.plasticLimit}</td>
                <td align="center">${res.atterbergLimits.plasticityIndex}</td>
                <td align="center">${res.specificGravity}</td>
                <td align="center">${res.freeSwellIndex}</td>
            </tr>
            """.trimIndent()
        }
    }

    private fun generateDirectShearResults(report: Report): String {
        val html = StringBuilder()
        report.directShearResults.forEachIndexed { i, results ->
            if (results.isNotEmpty()) {
                html.append("""
                    <div class="page-wrapper">
                        <h3 style="color: #1a237e ; border-bottom: 2px solid #1a237e; padding-bottom: 5px;">Direct Shear Test Results: (BH ${i + 1})</h3>
                        <table style="margin-top: 10px;">
                            <thead>
                                <tr style="background: #e8eaf6; text-align: center;">
                                    <th>Depth (m)</th>
                                    <th>Cohesion 'c' (kg/cm²)</th>
                                    <th>Angle of Internal Friction 'Φ' (degrees)</th>
                                    <th>Type of Test</th>
                                </tr>
                            </thead>
                            <tbody>
                                ${results.joinToString("") { res ->
                                    """
                                    <tr>
                                        <td align="center" style="font-weight: bold;">${res.depthOfSample}</td>
                                        <td align="center">${res.cValue.ifEmpty { "-" }}</td>
                                        <td align="center">${res.phiValue.ifEmpty { "-" }}</td>
                                        <td align="center">Consolidated Undrained</td>
                                    </tr>
                                    """.trimIndent()
                                }}
                            </tbody>
                        </table>
                        <div class="footer">
                            <span>BH ${i+1} Direct Shear</span>
                            <span>Report Ref: ${report.reportId}</span>
                        </div>
                    </div>
                """.trimIndent())
            }
        }
        return html.toString()
    }

    private fun generateSBCDetails(report: Report): String {
        val html = StringBuilder()
        report.sbcDetails.forEachIndexed { i, details ->
            if (details.isNotEmpty()) {
                html.append("""
                    <div class="page-wrapper">
                        <h3 style="color: #1a237e ; border-bottom: 2px solid #1a237e; padding-bottom: 5px;">Safe Bearing Capacity (SBC) Calculations: (BH ${i + 1})</h3>
                        <table style="margin-top: 10px;">
                            <thead>
                                <tr style="background: #e8eaf6; text-align: center;">
                                    <th>Depth (m)</th>
                                    <th>Footing Dimension (m)</th>
                                    <th>SBC Value (kN/m²)</th>
                                    <th>Remarks</th>
                                </tr>
                            </thead>
                            <tbody>
                                ${details.joinToString("") { res ->
                                    """
                                    <tr>
                                        <td align="center" style="font-weight: bold;">${res.depth}</td>
                                        <td align="center">${res.footingDimension.ifEmpty { "-" }}</td>
                                        <td align="center" style="color: #1a237e; font-weight: bold;">${res.sbcValue.ifEmpty { "-" }}</td>
                                        <td align="left">${if (res.useForReport) "Selected for final recommendation" else "-"}</td>
                                    </tr>
                                    """.trimIndent()
                                }}
                            </tbody>
                        </table>
                        <div class="footer">
                            <span>BH ${i+1} SBC Calc</span>
                            <span>Report Ref: ${report.reportId}</span>
                        </div>
                    </div>
                """.trimIndent())
            }
        }
        return html.toString()
    }

    private fun generateSitePhotos(report: Report): String {
        if (report.sitePhotos.isEmpty()) return ""
        
        return """
            <div class="page-wrapper">
                <h3 style="color: #1a237e ; border-bottom: 2px solid #1a237e; padding-bottom: 10px; text-transform: uppercase;">Annexure: Site Photographs</h3>
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 30px; margin-top: 20px;">
                    ${report.sitePhotos.mapIndexed { index, photo ->
                        """
                        <div style="text-align: center; border: 1px solid #ddd; padding: 10px; background: #fff;">
                            <div style="height: 200px; display: flex; align-items: center; justify-content: center; overflow: hidden;">
                                <img src="${photo}" style="max-width: 100%; max-height: 100%; object-fit: contain;">
                            </div>
                            <p style="margin-top: 10px; font-weight: bold; font-size: 10pt;">Photo ${index + 1}: ${if (index == 0) "Location of Borehole" else "Site Evidence"}</p>
                        </div>
                        """.trimIndent()
                    }.joinToString("")}
                </div>
                <div class="footer">
                    <span>Annexure: Photos</span>
                    <span>Report Ref: ${report.reportId}</span>
                </div>
            </div>
        """.trimIndent()
    }

    private fun generateRecommendations(report: Report): String {
        return """
            <div class="page-wrapper">
                <h2><span class="section-num">6.0</span> RECOMMENDATIONS & CONCLUSIONS</h2>
                <div style="font-size: 11pt; line-height: 1.6;">
                    <p>${report.recommendations.ifEmpty { "Based on the investigation results, suitable foundations can be designed. Standard precautions and practices should be followed during construction." }}</p>
                    
                    <h3 style="color: #1a237e ; border-bottom: 1px solid #eee; padding-bottom: 5px;">6.1 Recommended Safe Bearing Capacity</h3>
                    <table>
                        <thead>
                            <tr style="background: #f2f2f2;">
                                <th>Sl.No</th>
                                <th>BH No.</th>
                                <th>Depth (m)</th>
                                <th>Strata Type</th>
                                <th>Recommended SBC (kN/m²)</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${generateRecommendationRows(report)}
                        </tbody>
                    </table>

                    <h3 style="margin-top: 30px;">Conclusions</h3>
                    <ul>
                        ${report.conclusions.joinToString("") { "<li>${it.value}</li>" }}
                    </ul>
                </div>

                <div style="margin-top: 60px; display: flex; justify-content: flex-end;">
                    <div style="text-align: center; width: 300px;">
                        <p style="font-size: 9pt; font-weight: bold; margin-bottom: 10px;">For EDGE2 Engineering Solutions India Pvt. Ltd.</p>
                        <div style="display: flex; justify-content: space-around; align-items: center;">
                            <img src="engineer-sign.jpeg" style="height: 50px;" onerror="this.style.display='none'">
                            <img src="company-seal.png" style="height: 50px;" onerror="this.style.display='none'">
                        </div>
                        <img src="engineer-seal.jpeg" style="height: 80px; margin-top: 10px;" onerror="this.style.display='none'">
                        <p style="margin-top: 5px; font-weight: bold;">Authorized Signatory</p>
                    </div>
                </div>

                <div class="footer">
                    <span>Final Page</span>
                    <span>EDGE2 Engineering Solutions India Pvt. Ltd.</span>
                </div>
            </div>
        """.trimIndent()
    }

    private fun generateRecommendationRows(report: Report): String {
        var slNo = 1
        val sb = StringBuilder()
        report.boreholeLogs.forEachIndexed { bhIdx, bh ->
            bh.filter { it.sbc.isNotEmpty() }.forEach { log ->
                sb.append("""
                    <tr>
                        <td>${slNo++}</td>
                        <td>BH ${bhIdx + 1}</td>
                        <td>${log.depth}</td>
                        <td>${log.soilType}</td>
                        <td style="font-weight: bold; color: #1a237e;">${log.sbc}</td>
                    </tr>
                """.trimIndent())
            }
        }
        return sb.toString()
    }
}
