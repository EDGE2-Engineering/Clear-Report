import { supabase } from './customSupabaseClient';

/**
 * Custom auth helper using the 'app_users' table.
 */
const AUTH_KEY = 'edge2_mtr_session';

export const auth = {
    /**
     * Signs in a user using username and password.
     */
    async signIn(username, password) {
        try {
            const { data, error } = await supabase
                .from('app_users')
                .select('id, username, full_name, role, is_active')
                .eq('username', username)
                .eq('password', password)
                .eq('is_active', true)
                .single();

            if (error) {
                if (error.code === 'PGRST116') {
                    throw new Error('Invalid username or password');
                }
                throw error;
            }

            if (!data) {
                throw new Error('Invalid username or password');
            }

            const user = data;
            const session = {
                user,
                expiresAt: new Date(Date.now() + 1000 * 60 * 60 * 24 * 7).toISOString(), // 7 days
            };

            localStorage.setItem(AUTH_KEY, JSON.stringify(session));
            return { user, error: null };
        } catch (error) {
            console.error('Sign in error:', error);
            return { user: null, error };
        }
    },

    /**
     * Signs out the current user.
     */
    signOut() {
        localStorage.removeItem(AUTH_KEY);
        // Force a reload or update state as needed
        // Use pathname to stay within the application's base directory (important for subpaths/GitHub Pages)
        window.location.href = window.location.pathname;
    },

    /**
     * Retrieves the current session.
     */
    getSession() {
        const sessionStr = localStorage.getItem(AUTH_KEY);
        if (!sessionStr) return null;

        try {
            const session = JSON.parse(sessionStr);
            // Check for expiration
            if (new Date(session.expiresAt) < new Date()) {
                this.signOut();
                return null;
            }
            return session;
        } catch (e) {
            this.signOut();
            return null;
        }
    },

    /**
     * Updates the user's password.
     */
    async updatePassword(newPassword) {
        const session = this.getSession();
        if (!session) return { error: new Error('No active session') };

        try {
            const { data, error } = await supabase
                .from('app_users')
                .update({
                    password: newPassword,
                    updated_at: new Date().toISOString()
                })
                .eq('id', session.user.id);

            if (error) throw error;
            return { success: true, error: null };
        } catch (error) {
            console.error('Update password error:', error);
            return { success: false, error };
        }
    },

    /**
     * Simple check if user is logged in.
     */
    isAuthenticated() {
        return !!this.getSession();
    },

    /**
     * Fetches all application users.
     */
    async getUsers() {
        try {
            const { data, error } = await supabase
                .from('app_users')
                .select('id, username, full_name, role, is_active, created_at')
                .order('created_at', { ascending: false });

            if (error) throw error;
            return { users: data, error: null };
        } catch (error) {
            console.error('Error fetching users:', error);
            return { users: [], error };
        }
    },

    /**
     * Creates a new user.
     */
    async createUser(userData) {
        try {
            const { data, error } = await supabase
                .from('app_users')
                .insert([userData])
                .select()
                .single();

            if (error) throw error;
            return { user: data, error: null };
        } catch (error) {
            console.error('Error creating user:', error);
            return { user: null, error };
        }
    },

    /**
     * Toggles the active status of a user.
     */
    async updateUserStatus(userId, isActive) {
        try {
            const { error } = await supabase
                .from('app_users')
                .update({ is_active: isActive, updated_at: new Date().toISOString() })
                .eq('id', userId);

            if (error) throw error;
            return { success: true, error: null };
        } catch (error) {
            console.error('Error updating user status:', error);
            return { success: false, error };
        }
    }
};

export default auth;
