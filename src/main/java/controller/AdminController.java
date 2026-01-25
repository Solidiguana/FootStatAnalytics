package controller;
import entity.*;
import repository.*;
import service.SecurityService;
import java.sql.Date;
import java.util.Scanner;

public class AdminController {
    private TeamRepository teamRepo = new TeamRepository();
    private PlayerRepository playerRepo = new PlayerRepository();
    private MatchRepository matchRepo = new MatchRepository();
    private Scanner scanner = new Scanner(System.in);



    public void createTeam(User user) {
        if (!SecurityService.hasAccess(user, "ADMIN")){
            System.out.print("NOT ALLOWED");
            return;
        }
        System.out.print("TEAM NAME: ");
        String name = scanner.next();
        System.out.print("CITY: ");
        String city = scanner.next();
        if (teamRepo.save(new Team(name, city))) System.out.println("Team CREATED");
    }

    public void createPlayer(User user) {
        if (!SecurityService.hasAccess(user, "ADMIN")){
            System.out.print("NOT ALLOWED");
            return;
        }
        System.out.print("NAME: ");
        String name = scanner.next();
        System.out.print("POSITION: ");
        String pos = scanner.next();
        System.out.print("TEAM ID: ");
        int tid = scanner.nextInt();
        if (playerRepo.save(new Player(name, pos, tid))) System.out.println("Player CREATED");
    }

    public void createMatch(User user) {
        if (!SecurityService.hasAccess(user, "ADMIN")){
            System.out.print("NOT ALLOWED");
            return;
        }
        try {
            System.out.print("HOME ID: ");
            int h = scanner.nextInt();
            System.out.print("GUEST ID: ");
            int a = scanner.nextInt();
            System.out.print("DATE in form of (2025-01-13): ");
            String d = scanner.next();
            if (matchRepo.save(new Match(h, a, Date.valueOf(d)))) System.out.println("Match CREATED");
        } catch (Exception e) { System.out.println("Enter ERROR"); }
    }
}