package controller;

import entity.Match;
import entity.Player;
import entity.PlayerStat;
import entity.Team;
import service.PublicService;
import java.util.List;
import java.util.Scanner;

public class PublicController {
    private final PublicService publicService;
    private final Scanner scanner;

    public PublicController(PublicService publicService, Scanner scanner) {
        this.publicService = publicService;
        this.scanner = scanner;
    }

    public void viewRoster() {
        System.out.println("\n--- PLAYERS ---");
        publicService.getAllPlayers().forEach(System.out::println);
    }
    public void viewTeams() {
        System.out.println("\n--- TEAMS ---");
        publicService.getAllTeams().forEach(System.out::println);
    }
    public void viewMatches() {
        System.out.println("\n--- MATCHES ---");
        publicService.getAllMatches().forEach(System.out::println);
    }
    public void findTeamById() {
        System.out.println("\n--- FIND TEAM ---");
        try {
            System.out.print("Enter Team ID: ");
            int id = scanner.nextInt();

            Team team = publicService.getTeamById(id);
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

            Player player = publicService.getPlayerById(id);
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

            Match match = publicService.getMatchById(id);
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

            PlayerStat stat = publicService.getStatById(id);
            if (stat != null) {
                System.out.println("Found Record: " + stat);
            } else {
                System.out.println("Stat record not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number.");
        }
    }
    public void searchPlayersByTeam() {
        System.out.println("\n--- SEARCH BY TEAM NAME ---");
        try {
            System.out.print("Enter team name: ");
            String teamName = scanner.next();

            var results = publicService.getPlayersByTeamFiltered(teamName);
            if (results!=null) {
                System.out.println(teamName+ " roster: ");
                results.forEach(System.out::println);
            } else {
                System.out.println("No players found.");
            }
        }  catch (NumberFormatException e) {
            System.out.println("Error: team name must be a string.");
        }
    }
    public void viewAllStats() {
        System.out.println("\n--- ALL PLAYER STATISTICS ---");
        publicService.getAllStats().forEach(System.out::println);
    }
}
