
import React, { useState } from 'react';
import { Helmet } from 'react-helmet-async';
import Navbar from '@/components/Navbar';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { AlertCircle, Save } from 'lucide-react';
import { useToast } from '@/components/ui/use-toast';

const NewReportPage = () => {
    const { toast } = useToast();
    const [formData, setFormData] = useState({
        siteId: '',
        siteName: '',
        towerType: '',
        sbcValue1: '',
        sbcValue2: '',
        sbcValue3: '',
        sbcValue4: '',
        groundWaterTable: '',
        surveyDate: '',
        reportCreatedOn: new Date().toISOString().split('T')[0]
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form Submitted:', formData);

        // Simulating API call/submission
        toast({
            title: "Report Created",
            description: "The new report has been successfully generated.",
            className: "bg-green-50 border-green-200 text-green-900",
        });
    };

    return (
        <div className="min-h-screen bg-[#F5F1ED] flex flex-col">
            <Helmet>
                <title>New Report | EDGE2 MTR</title>
            </Helmet>

            <Navbar />

            <main className="flex-grow container mx-auto px-4 py-8">
                <div className="max-w-3xl mx-auto">
                    <div className="mb-8">
                        <h1 className="text-3xl font-bold text-gray-900">Create New Report</h1>
                        <p className="text-gray-500 mt-2">Enter the site details below to generate a new automated test report.</p>
                    </div>

                    <Card className="shadow-lg border-gray-200">
                        <CardHeader className="bg-gray-50/50 border-b border-gray-100">
                            <CardTitle className="text-xl text-primary">Report Details</CardTitle>
                            <CardDescription>All fields are required for accurate report generation.</CardDescription>
                        </CardHeader>
                        <CardContent className="p-6">
                            <form onSubmit={handleSubmit} className="space-y-6">

                                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                                    <div className="space-y-2">
                                        <Label htmlFor="siteId">Site ID</Label>
                                        <Input
                                            id="siteId"
                                            name="siteId"
                                            placeholder="e.g. EBG371"
                                            value={formData.siteId}
                                            onChange={handleChange}
                                            required
                                        />
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="siteName">Site Name</Label>
                                        <Input
                                            id="siteName"
                                            name="siteName"
                                            placeholder="e.g. Century Breeze"
                                            value={formData.siteName}
                                            onChange={handleChange}
                                            required
                                        />
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="towerType">Tower Type</Label>
                                        <Input
                                            id="towerType"
                                            name="towerType"
                                            placeholder="e.g. GBT30"
                                            value={formData.towerType}
                                            onChange={handleChange}
                                            required
                                        />
                                    </div>

                                    <div className="space-y-2">
                                        <Label htmlFor="groundWaterTable">Ground Water Table</Label>
                                        <Input
                                            id="groundWaterTable"
                                            name="groundWaterTable"
                                            placeholder="e.g. Not found"
                                            value={formData.groundWaterTable}
                                            onChange={handleChange}
                                            required
                                        />
                                    </div>
                                </div>

                                <div className="border-t border-gray-100 pt-6">
                                    <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center">
                                        <AlertCircle className="w-4 h-4 mr-2 text-primary" />
                                        SBC Values
                                    </h3>
                                    <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                                        <div className="space-y-2">
                                            <Label htmlFor="sbcValue1">SBC Value 1</Label>
                                            <Input
                                                id="sbcValue1"
                                                name="sbcValue1"
                                                placeholder="e.g. 10.89t/m2 at 1.5m"
                                                value={formData.sbcValue1}
                                                onChange={handleChange}
                                                required
                                            />
                                        </div>
                                        <div className="space-y-2">
                                            <Label htmlFor="sbcValue2">SBC Value 2</Label>
                                            <Input
                                                id="sbcValue2"
                                                name="sbcValue2"
                                                placeholder="e.g. 12.07t/m2 at 2.0m"
                                                value={formData.sbcValue2}
                                                onChange={handleChange}
                                                required
                                            />
                                        </div>
                                        <div className="space-y-2">
                                            <Label htmlFor="sbcValue3">SBC Value 3</Label>
                                            <Input
                                                id="sbcValue3"
                                                name="sbcValue3"
                                                placeholder="e.g. 13.67t/m2 at 2.5m"
                                                value={formData.sbcValue3}
                                                onChange={handleChange}
                                                required
                                            />
                                        </div>
                                        <div className="space-y-2">
                                            <Label htmlFor="sbcValue4">SBC Value 4</Label>
                                            <Input
                                                id="sbcValue4"
                                                name="sbcValue4"
                                                placeholder="e.g. 15.97t/m2 at 3.0m"
                                                value={formData.sbcValue4}
                                                onChange={handleChange}
                                                required
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div className="border-t border-gray-100 pt-6">
                                    <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                                        <div className="space-y-2">
                                            <Label htmlFor="surveyDate">Survey Date</Label>
                                            <Input
                                                id="surveyDate"
                                                name="surveyDate"
                                                type="date"
                                                value={formData.surveyDate}
                                                onChange={handleChange}
                                                required
                                            />
                                        </div>
                                        <div className="space-y-2">
                                            <Label htmlFor="reportCreatedOn">Report Created On</Label>
                                            <Input
                                                id="reportCreatedOn"
                                                name="reportCreatedOn"
                                                type="date"
                                                value={formData.reportCreatedOn}
                                                onChange={handleChange}
                                                required
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div className="pt-6 flex justify-end">
                                    <Button type="submit" size="lg" className="bg-primary hover:bg-primary-dark text-white min-w-[150px]">
                                        <Save className="w-4 h-4 mr-2" /> Generate Report
                                    </Button>
                                </div>

                            </form>
                        </CardContent>
                    </Card>
                </div>
            </main>

            <Footer />
        </div>
    );
};

export default NewReportPage;
