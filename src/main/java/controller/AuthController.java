package controller;
import entity.User;
import service.AuthService;
import java.util.Scanner;

public class AuthController {
    private AuthService authService = new AuthService();
    private Scanner scanner = new Scanner(System.in);

    public User login() {
        System.out.println("\n--- LOGIN ---");
        try {
            System.out.print("Login: ");
            String u = scanner.next();
            System.out.print("Password: ");
            String p = scanner.next();
            return authService.login(u, p);
        } catch (Exception e) {
            System.out.println("Enter ERROR");
            scanner.nextLine();
            return null;
        }
    }
    public void register() {
        System.out.println("\n--- REGISTRATION ---");
        try {
            System.out.print("Login: ");
            String u = scanner.next();
            System.out.print("Password: ");
            String p = scanner.next();
            System.out.print("Role (ADMIN/ANALYST/USER): ");
            String r = scanner.next().toUpperCase();
            System.out.println(authService.register(u, p, r));
        } catch (Exception e) {
            System.out.println("Enter ERROR");
            scanner.nextLine();
        }
    }
}