package controller;

import entity.User;
import service.AdminService;
import service.SecurityService;
import java.util.Scanner;

public class AdminController {
    private final AdminService adminService;
    private final Scanner scanner;

    public AdminController(AdminService adminService, Scanner scanner) {
        this.adminService = adminService;
        this.scanner = scanner;
    }

    public void createTeam(User user) {
        if (!SecurityService.hasAccess(user, "ADMIN")) {
            System.out.println("ACCESS DENIED: ADMIN ONLY.");
            return;
        }

        try {
            System.out.print("TEAM NAME: ");
            String name = scanner.next();

            System.out.print("CITY: ");
            String city = scanner.next();

            System.out.print("LEAGUE ID: ");
            int lid = scanner.nextInt();

            System.out.println(adminService.createTeam(name, city, lid));
        } catch (Exception e) {
            System.out.println("Input Error (Check League ID)");
            scanner.nextLine();
        }
    }

    public void createPlayer(User user) {
        if (!SecurityService.hasAccess(user, "ADMIN")) {
            System.out.println("ACCESS DENIED: ADMIN ONLY.");
            return;
        }

        try {
            System.out.print("NAME: ");
            String name = scanner.next();

            System.out.print("POSITION: ");
            String pos = scanner.next();

            System.out.print("TEAM ID: ");
            int tid = scanner.nextInt();

            System.out.println(adminService.createPlayer(name, pos, tid));
        } catch (Exception e) {
            System.out.println("Input Error (Check Team ID)");
            scanner.nextLine();
        }
    }

    public void createMatch(User user) {
        if (!SecurityService.hasAccess(user, "ADMIN")) {
            System.out.println("ACCESS DENIED: ADMIN ONLY.");
            return;
        }

        try {
            System.out.print("HOME ID: ");
            int h = scanner.nextInt();

            System.out.print("GUEST ID: ");
            int a = scanner.nextInt();

            System.out.print("DATE in the from of (yyyy-mm-dd): ");
            String d = scanner.next();

            System.out.println(adminService.createMatch(h, a, d));
        } catch (Exception e) {
            System.out.println("Input Error (Check IDs)");
            scanner.nextLine();
        }
    }
}