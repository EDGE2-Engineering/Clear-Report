
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from '@/App';
import '@/index.css';
import { AuthProvider } from "react-oidc-context";
import { cognitoConfig } from '@/config';

ReactDOM.createRoot(document.getElementById('root')).render(
  <AuthProvider {...cognitoConfig.oidc}>
    <App />
  </AuthProvider>
);
