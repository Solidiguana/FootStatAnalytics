package service;

import entity.User;

public class SecurityService {
    public static boolean hasAccess(User user, String... allowedRoles) {
        if (user == null) return false;
        for (String role : allowedRoles) {
            if (user.getRole().equalsIgnoreCase(role)) return true;
        }
        return false;
    }
}
