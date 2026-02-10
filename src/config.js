/**
 * Application Configuration
 * Centralized configuration for Cognito authentication and other app settings
 */

const region = "us-east-1";
const userPoolId = `${region}_pu5wLDAOu`;
const clientId = "60fbnkjist75g6oj50lpkk65p0";
const identityPoolId = "us-east-1:6b4965b8-d36b-4893-b81a-f24a7c99750b";
const cognitoDomainPrefix = "us-east-1pu5wldaou";
const domain = `https://${cognitoDomainPrefix}.auth.${region}.amazoncognito.com`;

// Cognito Configuration
export const cognitoConfig = {
    // User Pool Configuration
    region,
    userPoolId,
    identityPoolId,
    // Cognito Domain (for logout and hosted UI)

    // OIDC Configuration for react-oidc-context
    oidc: {
        authority: `https://cognito-idp.${region}.amazonaws.com/${userPoolId}`,
        client_id: clientId,
        redirect_uri: typeof window !== 'undefined' ? window.location.origin : "http://localhost:3000",
        response_type: "code",
        scope: "phone openid profile email",
    },

    // Logout Configuration
    getLogoutUrl: () => {
        const logoutUri = typeof window !== 'undefined' ? window.location.origin : "http://localhost:3000";
        const encodedLogoutUri = encodeURIComponent(logoutUri);
        return `${domain}/logout?client_id=${clientId}&logout_uri=${encodedLogoutUri}`;
    },
};

export default cognitoConfig;
