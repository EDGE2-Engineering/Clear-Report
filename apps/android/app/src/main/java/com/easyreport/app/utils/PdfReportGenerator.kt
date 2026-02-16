package com.easyreport.app.utils

import android.content.Context
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.util.Log
import android.widget.Toast
import com.easyreport.app.data.models.Report

class PdfReportGenerator(private val context: Context) {
    private var activeWebView: WebView? = null
    
    companion object {
        private const val TAG = "PdfReportGenerator"
        private const val CHART_RENDER_DELAY = 2000L
    }

    fun generateReport(report: Report) {
        try {
            Log.d(TAG, "Starting PDF generation for report: ${report.reportId}")
            
            // Validate report has content
            if (report.reportId.isEmpty()) {
                Log.w(TAG, "Report ID is empty")
                showToast("Please fill in report details before generating PDF")
                return
            }
            
            showToast("Generating PDF report...")
            
            val webView = WebView(context)
            activeWebView = webView
            
            // Ensure webview has some size or it might not render/fire onPageFinished
            webView.layout(0, 0, 1024, 1024) 
            
            webView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                allowFileAccess = true
                allowContentAccess = true
                useWideViewPort = true
                loadWithOverviewMode = true
            }
            
            Log.d(TAG, "WebView configured, generating HTML content...")
            
            // Construct the full HTML content
            val htmlContent = try {
                ReportHtmlTemplate.generate(report)
            } catch (e: Exception) {
                Log.e(TAG, "Error generating HTML template", e)
                showToast("Error generating report template")
                return
            }
            
            Log.d(TAG, "HTML content generated (${htmlContent.length} chars)")

            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    Log.d(TAG, "WebView page finished loading")
                    // Use Handler instead of view.postDelayed since WebView may not be attached to window
                    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                        Log.d(TAG, "Chart render delay completed, creating print job...")
                        view?.let { 
                            try {
                                createWebPrintJob(it)
                            } catch (e: Exception) {
                                Log.e(TAG, "Error creating print job", e)
                                showToast("Error opening print dialog: ${e.message}")
                            }
                        }
                    }, CHART_RENDER_DELAY)
                }
                
                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    Log.e(TAG, "WebView error: $errorCode - $description")
                    showToast("Error loading report content")
                }
            }

            // Add a ChromeClient for potential console log debugging (via Logcat)
            webView.webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    Log.d(TAG, "JS Console: ${consoleMessage?.message()}")
                    return true
                }
            }

            // Load HTML with base URL pointing to assets so images work
            Log.d(TAG, "Loading HTML content into WebView...")
            webView.loadDataWithBaseURL("file:///android_asset/", htmlContent, "text/html", "utf-8", null)
            
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error in generateReport", e)
            showToast("Failed to generate PDF: ${e.message}")
        }
    }

    private fun createWebPrintJob(webView: WebView) {
        try {
            Log.d(TAG, "Creating print job...")
            
            val printManager = context.getSystemService(Context.PRINT_SERVICE) as? PrintManager
            if (printManager == null) {
                Log.e(TAG, "PrintManager not available")
                showToast("Print service not available")
                return
            }
            
            val jobName = "Easy Report Document"
            val printAdapter = webView.createPrintDocumentAdapter(jobName)

            val builder = PrintAttributes.Builder()
            builder.setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            
            Log.d(TAG, "Opening print dialog...")
            printManager.print(jobName, printAdapter, builder.build())
            Log.d(TAG, "Print dialog opened successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error in createWebPrintJob", e)
            throw e
        }
    }
    
    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

