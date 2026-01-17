package controller;
import repository.*;
import entity.*;
import java.util.Scanner;

public class PublicController {
    private PlayerRepository playerRepo = new PlayerRepository();
    private TeamRepository teamRepo = new TeamRepository();
    private MatchRepository matchRepo = new MatchRepository();
    private StatRepository statRepo = new StatRepository();

    private Scanner scanner = new Scanner(System.in);

    public void viewRoster() {
        System.out.println("\n--- PLAYERS ---");
        playerRepo.findAll().forEach(System.out::println);
    }
    public void viewTeams() {
        System.out.println("\n--- TEAMS ---");
        teamRepo.findAll().forEach(System.out::println);
    }
    public void viewMatches() {
        System.out.println("\n--- MATCHES ---");
        matchRepo.findAll().forEach(System.out::println);
    }
    public void findTeamById() {
        System.out.println("\n--- FIND TEAM ---");
        try {
            System.out.print("Enter Team ID: ");
            int id = scanner.nextInt();

            Team team = teamRepo.findById(id);
            if (team != null) {
                System.out.println("Found: " + team);
            } else {
                System.out.println("Team not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number.");
        }
    }

    public void findPlayerById() {
        System.out.println("\n--- FIND PLAYER ---");
        try {
            System.out.print("Enter Player ID: ");
            int id = scanner.nextInt();

            Player player = playerRepo.findById(id);
            if (player != null) {
                System.out.println("Found: " + player);
            } else {
                System.out.println("Player not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number.");
        }
    }

    public void findMatchById() {
        System.out.println("\n--- FIND MATCH ---");
        try {
            System.out.print("Enter Match ID: ");
            int id = scanner.nextInt();

            Match match = matchRepo.findById(id);
            if (match != null) {
                System.out.println("Found: " + match);
            } else {
                System.out.println("Match not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number.");
        }
    }

    public void findStatById() {
        System.out.println("\n--- FIND STAT RECORD ---");
        try {
            System.out.print("Enter Stat Record ID: ");
            int id = scanner.nextInt();

            PlayerStat stat = statRepo.findById(id);
            if (stat != null) {
                System.out.println("Found Record: " + stat);
            } else {
                System.out.println("Stat record not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number.");
        }
    }
}
