package com.easyreport.app.utils

import com.easyreport.app.data.models.Report

object ReportHtmlTemplate {
    fun generate(report: Report): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: sans-serif; margin: 0; padding: 0; color: #333; }
                    .page { width: 210mm; height: 297mm; padding: 15mm; margin: auto; box-sizing: border-box; position: relative; page-break-after: always; }
                    .header { display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #29299a; padding-bottom: 10px; margin-bottom: 20px; }
                    .logo { height: 60px; }
                    .report-id { color: #29299a; font-weight: bold; }
                    h1 { color: #29299a; font-size: 18pt; text-align: center; margin-top: 40px; }
                    h2 { color: #29299a; font-size: 14pt; border-bottom: 1px solid #ccc; padding-bottom: 5px; margin-top: 20px; }
                    table { width: 100%; border-collapse: collapse; margin-top: 10px; }
                    th, td { border: 1px solid #ccc; padding: 8px; text-align: left; font-size: 10pt; }
                    th { background-color: #f2f2f2; font-weight: bold; }
                    .footer { position: absolute; bottom: 15mm; left: 15mm; right: 15mm; border-top: 1px solid #ccc; padding-top: 5px; font-size: 8pt; text-align: center; }
                    .client-info { text-align: center; margin: 30px 0; }
                    .client-name { font-size: 16pt; font-weight: bold; color: #29299a; }
                </style>
            </head>
            <body>
                <div class="page">
                    <div class="header">
                        <img src="edge2-logo.png" class="logo">
                        <div class="report-id">ID: ${report.reportId}</div>
                    </div>
                    
                    <h1>GEOTECHNICAL INVESTIGATION REPORT</h1>
                    
                    <div class="client-info">
                        <p>Submitted to</p>
                        <div class="client-name">${report.clientName}</div>
                        <p>${report.clientAddress}</p>
                    </div>
                    
                    <h2>Project Information</h2>
                    <table>
                        <tr><th>Project Type</th><td>${report.projectType}</td></tr>
                        <tr><th>Site Name</th><td>${report.siteName}</td></tr>
                        <tr><th>Site Address</th><td>${report.siteAddress}</td></tr>
                        <tr><th>Survey Date</th><td>${report.surveyDate}</td></tr>
                    </table>
                    
                    <div class="footer">
                        EDGE2 Engineering Solutions India Pvt. Ltd. | www.edge2.in
                    </div>
                </div>

                <!-- Borehole Logs Page -->
                <div class="page">
                    <h2>Borehole Logs</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Depth (m)</th>
                                <th>Soil Type</th>
                                <th>Nature</th>
                                <th>SBC</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${generateBoreholeRows(report)}
                        </tbody>
                    </table>
                </div>

                <!-- Conclusions -->
                <div class="page">
                    <h2>Conclusions & Recommendations</h2>
                    <ul>
                        ${report.conclusions.joinToString("") { "<li>${it.value}</li>" }}
                    </ul>
                    <p style="margin-top: 40px;"><b>Recommendations:</b></p>
                    <p>${report.recommendations}</p>
                    
                    <div style="margin-top: 60px; text-align: right;">
                        <img src="engineer-sign.jpeg" style="height: 80px;"><br>
                        <b>Authorized Signatory</b>
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
    }

    private fun generateBoreholeRows(report: Report): String {
        val rows = StringBuilder()
        report.boreholeLogs.forEachIndexed { index, bh ->
            rows.append("<tr><th colspan='4' style='background: #ddd;'>Borehole ${index + 1}</th></tr>")
            bh.forEach { log ->
                rows.append("""
                    <tr>
                        <td>${log.depth}</td>
                        <td>${log.soilType}</td>
                        <td>${log.natureOfSampling}</td>
                        <td>${log.sbc}</td>
                    </tr>
                """.trimIndent())
            }
        }
        return rows.toString()
    }
}
