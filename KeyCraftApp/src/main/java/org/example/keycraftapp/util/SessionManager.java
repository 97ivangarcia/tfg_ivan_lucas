package org.example.keycraftapp.util;

import org.example.keycraftapp.entities.User;

public class SessionManager {
    private static User currentUser;
    public static User getCurrentUser() { return currentUser; }
    public static void setCurrentUser(User u) { currentUser = u; }
}
