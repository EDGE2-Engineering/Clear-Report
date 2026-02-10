
import React, { useState, useEffect } from 'react';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { useAuth } from 'react-oidc-context';
import { User, Mail, Shield, Info } from 'lucide-react';
import { Alert, AlertDescription } from '@/components/ui/alert.jsx';

const AdminUserManager = () => {
    const auth = useAuth();
    const [userInfo, setUserInfo] = useState({
        username: '',
        email: '',
        fullName: '',
        role: 'standard'
    });

    useEffect(() => {
        if (auth.isAuthenticated && auth.user?.profile) {
            const profile = auth.user.profile;
            setUserInfo({
                username: profile.email || profile.preferred_username || '',
                email: profile.email || '',
                fullName: profile['custom:full_name'] || profile['custom:name'] || profile.name || profile.email || '',
                role: profile['custom:role'] || profile.role || 'standard'
            });
        }
    }, [auth.isAuthenticated, auth.user]);

    const getRoleBadgeColor = (role) => {
        switch (role) {
            case 'super_admin':
                return 'bg-red-100 text-red-700';
            case 'admin':
                return 'bg-purple-100 text-purple-700';
            default:
                return 'bg-gray-100 text-gray-700';
        }
    };

    return (
        <div className="space-y-6 animate-in fade-in duration-500">
            {/* Info Alert */}
            <Alert>
                <Info className="h-4 w-4" />
                <AlertDescription>
                    User management is handled through AWS Cognito. To create, modify, or delete users, please use the{' '}
                    <a
                        href="https://console.aws.amazon.com/cognito/"
                        target="_blank"
                        rel="noopener noreferrer"
                        className="font-medium text-primary hover:underline"
                    >
                        AWS Cognito Console
                    </a>.
                </AlertDescription>
            </Alert>

            {/* Current User Profile */}
            <Card>
                <CardHeader>
                    <CardTitle className="flex items-center gap-2">
                        <User className="w-5 h-5 text-primary" />
                        Your Profile
                    </CardTitle>
                    <CardDescription>Your account information from AWS Cognito</CardDescription>
                </CardHeader>
                <CardContent className="space-y-6">
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                        {/* Full Name */}
                        <div className="space-y-2">
                            <label className="text-sm font-medium text-gray-700 flex items-center gap-2">
                                <User className="w-4 h-4 text-gray-500" />
                                Full Name
                            </label>
                            <div className="px-4 py-3 bg-gray-50 rounded-md border border-gray-200">
                                <p className="text-gray-900">{userInfo.fullName || 'Not set'}</p>
                            </div>
                        </div>

                        {/* Email */}
                        <div className="space-y-2">
                            <label className="text-sm font-medium text-gray-700 flex items-center gap-2">
                                <Mail className="w-4 h-4 text-gray-500" />
                                Email
                            </label>
                            <div className="px-4 py-3 bg-gray-50 rounded-md border border-gray-200">
                                <p className="text-gray-900">{userInfo.email || 'Not set'}</p>
                            </div>
                        </div>

                        {/* Username */}
                        <div className="space-y-2">
                            <label className="text-sm font-medium text-gray-700 flex items-center gap-2">
                                <User className="w-4 h-4 text-gray-500" />
                                Username
                            </label>
                            <div className="px-4 py-3 bg-gray-50 rounded-md border border-gray-200">
                                <p className="text-gray-900">{userInfo.username || 'Not set'}</p>
                            </div>
                        </div>

                        {/* Role */}
                        <div className="space-y-2">
                            <label className="text-sm font-medium text-gray-700 flex items-center gap-2">
                                <Shield className="w-4 h-4 text-gray-500" />
                                Role
                            </label>
                            <div className="px-4 py-3 bg-gray-50 rounded-md border border-gray-200">
                                <span className={`px-3 py-1 rounded-full text-xs font-semibold ${getRoleBadgeColor(userInfo.role)}`}>
                                    {userInfo.role}
                                </span>
                            </div>
                        </div>
                    </div>
                </CardContent>
            </Card>
        </div>
    );
};

export default AdminUserManager;
