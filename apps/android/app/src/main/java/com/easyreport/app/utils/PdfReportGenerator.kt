package com.easyreport.app.utils

import android.content.Context
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.easyreport.app.data.models.Report

class PdfReportGenerator(private val context: Context) {

    fun generateReport(report: Report) {
        val webView = WebView(context)
        
        // Construct the full HTML content
        val htmlContent = ReportHtmlTemplate.generate(report)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                createWebPrintJob(view!!)
            }
        }

        // Load HTML with base URL pointing to assets so images work
        webView.loadDataWithBaseURL("file:///android_asset/", htmlContent, "text/html", "utf-8", null)
    }

    private fun createWebPrintJob(webView: WebView) {
        val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val jobName = "Easy Report Document"
        val printAdapter = webView.createPrintDocumentAdapter(jobName)

        val builder = PrintAttributes.Builder()
        builder.setMediaSize(PrintAttributes.MediaSize.ISO_A4)
        
        printManager.print(jobName, printAdapter, builder.build())
    }
}
