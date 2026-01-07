import React from 'react';
import { HashRouter as Router, Routes, Route } from 'react-router-dom';
import { Helmet, HelmetProvider } from 'react-helmet-async';
import { Toaster } from '@/components/ui/toaster';
import { ProductsProvider } from '@/contexts/ProductsContext';


import HomePage from '@/pages/HomePage';
import ProductDetailPage from '@/pages/ProductDetailPage';


import AdminPage from '@/pages/AdminPage';
import NewReportPage from '@/pages/NewReportPage';

import FloatingButtons from '@/components/FloatingButtons';

function App() {
  return (
    <HelmetProvider>
      <ProductsProvider>
        <Router>
          <Helmet>
            <link rel="preconnect" href="https://fonts.googleapis.com" />
            <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous" />
            <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet" />
          </Helmet>
          <div className="min-h-screen bg-[#F5F1ED]">
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/product/:id" element={<ProductDetailPage />} />

              <Route path="/admin" element={<AdminPage />} />
              <Route path="/new-report" element={<NewReportPage />} />
            </Routes>
            <FloatingButtons />
            <Toaster />
          </div>
        </Router>
      </ProductsProvider>

    </HelmetProvider >
  );
}

export default App;
