package controller;
import service.StatService;
import entity.User;
import service.SecurityService;
import java.util.Scanner;


public class StatController {
    private StatService service = new StatService();
    private Scanner scanner = new Scanner(System.in);

    public void inputStats(User user) {
        if (!SecurityService.hasAccess(user, "ADMIN", "ANALYST")) {
            System.out.println("NOT ALLOWED: ONLY FOR ANALYST AND ADMINS.");
            return;
        }
        System.out.println("\n--- STATS INPUT ---");
        try {
            System.out.print("MATCH ID: ");
            int mid = scanner.nextInt();
            System.out.print("PLAYER ID: ");
            int pid = scanner.nextInt();
            System.out.print("GOALS: ");
            int g = scanner.nextInt();
            System.out.print("ASSISTS: ");
            int a = scanner.nextInt();
            System.out.print("MINUTES PLAYED: ");
            int m = scanner.nextInt();
            System.out.println(service.processStats(pid, mid, g, a, m));
        } catch (Exception e) {
            System.out.println("ERROR: enter only numbers.");
            scanner.nextLine();
        }
    }
}
