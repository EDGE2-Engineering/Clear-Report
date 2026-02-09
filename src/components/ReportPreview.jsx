import React from 'react';
import logo from '@/assets/edge2-logo.png';
import { X, Printer, FileText } from 'lucide-react';
import { Button } from '@/components/ui/button';

const ReportPreview = ({ formData, onClose }) => {
    if (!formData) return null;

    const handlePrint = () => {
        window.print();
    };

    return (
        <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/60 backdrop-blur-sm p-4 print:p-0 print:bg-white overflow-hidden">
            <div className="bg-white w-full max-w-5xl h-[95vh] rounded-lg shadow-2xl flex flex-col print:h-auto print:rounded-none print:shadow-none print:max-w-none">
                {/* Header/Controls */}
                <div className="flex items-center justify-between p-4 border-b print:hidden bg-white rounded-t-lg">
                    <div className="flex items-center space-x-4">
                        <div className="p-2 bg-blue-50 rounded-lg">
                            <FileText className="w-5 h-5 text-blue-600" />
                        </div>
                        <div>
                            <h2 className="text-xl font-bold text-gray-800">Report Preview</h2>
                            <p className="text-xs text-gray-500 font-medium tracking-wide flex items-center">
                                <span className="uppercase mr-2">A4 Paper Format</span>
                                <span className="h-1 w-1 bg-gray-300 rounded-full mr-2"></span>
                                <span className="uppercase">Precise Layout Control</span>
                            </p>
                        </div>
                    </div>
                    <div className="flex items-center space-x-3">
                        <Button variant="outline" size="sm" onClick={handlePrint} className="flex items-center space-x-2 border-primary/20 hover:bg-primary/5">
                            <Printer className="w-4 h-4" />
                            <span>Print / Save PDF</span>
                        </Button>
                        <Button variant="ghost" size="icon" onClick={onClose} className="rounded-full hover:bg-red-50 hover:text-red-600 transition-colors">
                            <X className="w-5 h-5" />
                        </Button>
                    </div>
                </div>

                {/* Preview Area */}
                <div className="flex-1 overflow-auto bg-gray-100 p-8 print:p-0 print:bg-white print:overflow-visible">
                    <div className="flex flex-col items-center space-y-12 print:space-y-0">

                        {/* Page 1: Front Sheet */}
                        <div className="a4-page shadow-2xl print:shadow-none bg-white relative overflow-hidden"
                            style={{ width: '210mm', height: '297mm', padding: '10mm', minHeight: '297mm', boxSizing: 'border-box' }}>
                            <div className="border-[0.5pt] border-black h-[277mm] relative p-12 flex flex-col items-center">

                                <div className="text-center mt-12 mb-16">
                                    <h1 className="text-[22pt] text-[#29299a] font-bold leading-tight mb-4">
                                        Geo-Technical Investigation Report for <br />
                                        Construction of {formData.projectType || '________________'}
                                    </h1>
                                    <p className="text-[#29299a] text-[16pt] font-semibold italic">
                                        at {formData.siteAddress || '________________'}
                                    </p>
                                </div>

                                <div className="text-center mb-12">
                                    <p className="text-[14pt] text-gray-600 mb-6 italic">Submitted to</p>
                                    <h2 className="text-[18pt] font-bold text-[#29299a] uppercase tracking-wide">
                                        M/s {formData.client || '________________'}
                                    </h2>
                                    <p className="text-[#29299a] text-[12pt] mt-2">{formData.clientAddress || '________________'}</p>
                                </div>

                                {formData.clientLogo && (
                                    <div className="mb-12">
                                        <img src={formData.clientLogo} alt="Client Logo" className="max-w-[4cm] max-h-[4cm] object-contain" />
                                    </div>
                                )}

                                {/* Report Details Table */}
                                <div className="w-full max-w-[15cm] mx-auto mb-12">
                                    <table className="w-full border-collapse text-[11pt]">
                                        <tbody>
                                            <tr>
                                                <td className="py-2 pr-4 font-bold w-1/3">Report ID:</td>
                                                <td className="py-2 pl-4 border-l border-black/20">{formData.reportId || '________________'}</td>
                                            </tr>
                                            <tr>
                                                <td className="py-2 pr-4 font-bold">Site ID:</td>
                                                <td className="py-2 pl-4 border-l border-black/20">{formData.siteId || '________________'}</td>
                                            </tr>
                                            <tr>
                                                <td className="py-2 pr-4 font-bold">Date:</td>
                                                <td className="py-2 pl-4 border-l border-black/20">{formData.surveyDate || '________________'}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>

                                {/* Footer Section */}
                                <div className="w-full mt-auto mb-4 text-center">
                                    <div className="flex flex-col items-center">
                                        <p className="text-[12pt] font-bold text-gray-800 mb-4">Report Prepared By</p>
                                        <img src={logo} alt="EDGE2 Logo" className="w-[20mm] object-contain mb-3" />
                                        <h3 className="text-[14pt] font-extrabold text-[#29299a] uppercase">Edge2 Engineering Solutions India Pvt. Ltd.</h3>
                                        <p className="text-[8pt] text-gray-700 mt-2 font-medium">
                                            Geo-technical Investigation • Material Testing • Structural Health & Stability <br />
                                            NDT • Restoration & Rehabilitation • PMC & QA
                                        </p>
                                        <div className="flex space-x-4 text-[8pt] text-gray-500 mt-2">
                                            <span>www.edge2.in</span>
                                            <span>|</span>
                                            <span>📞 +91 98809 73810</span>
                                            <span>|</span>
                                            <span>info@edge2.in</span>
                                        </div>
                                    </div>

                                    <div className="mt-8 pt-4 border-t border-black/30 text-[8pt] text-left grid grid-cols-2 gap-4">
                                        <div>
                                            <p className="text-[#800000] font-bold uppercase mb-1">Registered Office & Lab:</p>
                                            <p className="leading-tight text-gray-600">
                                                Shivaganga Arcade, B35/130, 6th Cross, 6th Block, <br />
                                                Vishweshwaraiah Layout, Ullal Upanagar, Bengaluru - 560056
                                            </p>
                                        </div>
                                        <div className="flex items-center justify-end font-bold italic text-blue-900 border-l border-black/20 pl-4">
                                            ISO 9001:2015 Certified <br /> (Certificate No: IN12701A)
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        {/* Page 2: Introduction & Scope */}
                        <div className="a4-page shadow-2xl print:shadow-none bg-white relative overflow-hidden print:break-before-page"
                            style={{ width: '210mm', height: '297mm', padding: '10mm', minHeight: '297mm', boxSizing: 'border-box' }}>
                            <div className="border-[0.5pt] border-black h-[277mm] p-12">
                                <h1 className="text-center font-bold text-[14pt] mb-10 uppercase text-blue-900">Technical Report for Geo-technical Investigation</h1>

                                <section className="mb-8">
                                    <h2 className="text-[12pt] font-bold mb-4 flex items-center">
                                        <span className="bg-blue-900 text-white px-2 mr-3">1.0</span> INTRODUCTION
                                    </h2>
                                    <div className="text-[11pt] text-justify leading-relaxed">
                                        <p className="mb-4">
                                            <strong>M/s {formData.client || '________________'}</strong>, Proposes to Construct
                                            {formData.projectType || '________________'} situated at {formData.siteAddress || '________________'}.
                                        </p>
                                        <p className="mb-4 text-justify">
                                            <strong>M/s EDGE2 Engineering Solutions India Pvt. Ltd.</strong> have assigned the Geo-Technical Investigation work to be carried out at the above said project site locations with a view to furnish the detailed Geo-Technical Information of the nature and sub-soil strata for Detailed Foundation Designs.
                                        </p>
                                    </div>
                                </section>

                                <section className="mb-8">
                                    <h2 className="text-[12pt] font-bold mb-4 flex items-center">
                                        <span className="bg-blue-900 text-white px-2 mr-3">2.0</span> PROJECT SITE LOCATION
                                    </h2>
                                    <div className="border border-gray-300 rounded p-1 bg-gray-50 h-[8cm] flex items-center justify-center italic text-gray-400">
                                        Site Location Image / Visualization Placeholder
                                    </div>
                                </section>

                                <section className="mb-8">
                                    <h2 className="text-[12pt] font-bold mb-4 flex items-center">
                                        <span className="bg-blue-900 text-white px-2 mr-3">3.0</span> OBJECTIVES AND SCOPE OF WORK
                                    </h2>
                                    <ul className="list-disc ml-8 text-[11pt] space-y-2">
                                        <li>To ascertain the sub-soil strata at each site</li>
                                        <li>To study standing Ground Water Level</li>
                                        <li>To study the physical and engineering properties of soil strata</li>
                                        <li>To evaluate allowable safe bearing capacity of soil</li>
                                        <li>To recommend type and depth of foundation</li>
                                    </ul>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <style>{`
                @media screen {
                    .a4-page {
                        margin: 0 auto;
                        box-sizing: border-box;
                    }
                }
                @media print {
                    body * {
                        visibility: hidden !important;
                    }
                    .fixed, .fixed * {
                        visibility: visible !important;
                    }
                    .fixed {
                        position: absolute !important;
                        left: 0 !important;
                        top: 0 !important;
                        width: 100% !important;
                        height: auto !important;
                        padding: 0 !important;
                        margin: 0 !important;
                        overflow: visible !important;
                        background: white !important;
                    }
                    .bg-black\\/60 {
                        background: white !important;
                    }
                    .bg-gray-100 {
                        background: white !important;
                    }
                    .shadow-2xl {
                        box-shadow: none !important;
                    }
                    .rounded-lg {
                        border-radius: 0 !important;
                    }
                    .flex-1 {
                        overflow: visible !important;
                    }
                    .a4-page {
                        margin: 0 !important;
                        padding: 10mm !important;
                        width: 210mm !important;
                        height: 297mm !important;
                        box-sizing: border-box !important;
                        page-break-after: always !important;
                    }
                    .print\\:hidden {
                        display: none !important;
                    }
                }
            `}</style>
        </div>
    );
};

export default ReportPreview;
