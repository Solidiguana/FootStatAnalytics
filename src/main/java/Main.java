import controller.*;
import entity.User;
import repository. *;
import repository.interfaces.*;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IUserRepository userRepo = new UserRepository();
        ITeamRepository teamRepo = new TeamRepository();
        IPlayerRepository playerRepo = new PlayerRepository();
        IMatchRepository matchRepo = new MatchRepository();
        IStatRepository statRepo = new StatRepository();

        AuthService authService = new AuthService(userRepo);
        AdminService adminService = new AdminService(teamRepo, playerRepo, matchRepo);
        PublicService publicService = new PublicService(playerRepo, teamRepo, matchRepo, statRepo);
        StatService statService = new StatService(statRepo, playerRepo);

        AuthController authController = new AuthController(authService, scanner);
        AdminController adminController = new AdminController(adminService, scanner);
        PublicController publicController = new PublicController(publicService, scanner);
        StatController statController = new StatController(statService, scanner);

        User currentUser = null;

        System.out.println("==========================================");
        System.out.println("          FOOTSTAT ANALYTICS V3.0         ");
        System.out.println("==========================================");

        while (currentUser == null) {
            System.out.println("\n1. Login\n2. Registration\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                currentUser = authController.login();
            }
            else if (choice == 2) authController.register();
            else if (choice == 0){
                System.out.println("Bye-bye");
                return;
            }
            else {
                System.out.println("Invalid choice");
            }
        }

        System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");

        while (true) {
            System.out.println("\n========= MAIN MENU =========");
            System.out.println("1. View all players");
            System.out.println("2. View all teams");
            System.out.println("3. View all mathces");
            System.out.println("4. Search specific data by ID");
            System.out.println("5. Filter Players by Exact Team Name");
            System.out.println("6. View All Statistics");
            System.out.println("11. Show Teams by League");
            System.out.println("12. Search players by position");
            System.out.println("13. Show top scorers");
            System.out.println("14. Compare two players");

            if (SecurityService.hasAccess(currentUser, "ADMIN", "ANALYST")) {
                System.out.println("7. Input Stats");
            }
            if (SecurityService.hasAccess(currentUser, "ADMIN")) {
                System.out.println("8. ADD Team");
                System.out.println("9. ADD Player");
                System.out.println("10. CREATE Match");
            }
            System.out.println("0. EXIT");
            System.out.print("> ");
            try {

                int menu = scanner.nextInt();

                switch (menu) {
                    case 1:
                        publicController.viewRoster();
                        break;
                    case 2:
                        publicController.viewTeams();
                        break;
                    case 3:
                        publicController.viewMatches();
                        break;

                    case 4:
                        System.out.println("\n--- SEARCH MENU ---");
                        System.out.println("1. Find Team by ID");
                        System.out.println("2. Find Player by ID");
                        System.out.println("3. Find Match by ID");
                        System.out.println("4. Find Stat Record by ID");
                        System.out.print("Search > ");
                        try {
                            int searchMenu = scanner.nextInt();
                            switch (searchMenu) {
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                                case 1:
                                    publicController.findTeamById();
                                    break;
                                case 2:
                                    publicController.findPlayerById();
                                    break;
                                case 3:
                                    publicController.findMatchById();
                                    break;
                                case 4:
                                    publicController.findStatById();
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("Input error");
                        }
                        break;

                    case 5:
                        publicController.searchPlayersByTeam();
                        break;
                    case 6:
                        publicController.viewAllStats();
                        break;
                    case 7:
                        statController.inputStats(currentUser);
                        break;

                    case 8:
                        adminController.createTeam(currentUser);
                        break;
                    case 9:
                        adminController.createPlayer(currentUser);
                        break;
                    case 10:
                        adminController.createMatch(currentUser);
                        break;

                    case 11:
                        publicController.showTeamsByLeague();
                        break;
                    case 12:
                        publicController.searchPlayersByPosition();
                        break;
                    case 13:
                        publicController.showTopScorers();
                        break;
                    case 14:
                        publicController.compareTwoPlayers();
                        break;

                    case 0:
                        System.out.println("Bye-bye!");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}