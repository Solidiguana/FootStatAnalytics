package service;

import entity.User;
import repository.UserRepository;

public class AuthService {
    private UserRepository userRepo = new UserRepository();

    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        System.out.println("Invalid username or password");
        return null;
    }

    public String register(String username, String password, String role) {
        if (userRepo.findByUsername(username) != null) return "Login already exists";
        User newUser = new User(username, password, role);
        return userRepo.save(newUser) ? "Succeed" : "DB error";
    }
}
