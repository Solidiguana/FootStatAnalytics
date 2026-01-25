import controller.*;
import entity.User;
import repository.UserRepository;
import repository.interfaces.IUserRepository;
import service.AuthService;
import service.SecurityService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IUserRepository userRepo = new UserRepository();
        AuthService authService = new AuthService(userRepo);
        AuthController authController = new AuthController(authService);
        AdminController adminController = new AdminController();
        PublicController publicController = new PublicController();
        StatController statController = new StatController();

        User currentUser = null;
        System.out.println("FOOTSTAT ANALYTICS V1.0");

        while (currentUser == null) {
            System.out.println("\n1. Login\n2. Registration\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            if (choice == 1) currentUser = authController.login();
            else if (choice == 2) authController.register();
            else {
                System.out.println("Bye-bye");
                return;
            }
        }

        System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");

        while (true) {
            System.out.println("\n--- MENU ---\n ---");
            System.out.println("1. View all players");
            System.out.println("2. View all teams");
            System.out.println("3. View all mathces");
            System.out.println("4. Search specific data by ID");

            if (SecurityService.hasAccess(currentUser, "ADMIN", "ANALYST")) {
                System.out.println("5. Input Stats");
            }
            if (SecurityService.hasAccess(currentUser, "ADMIN")) {
                System.out.println("6. ADD Team");
                System.out.println("7. ADD Player");
                System.out.println("8. CREATE Match");
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
                        statController.inputStats(currentUser);
                        break;

                    case 6:
                        adminController.createTeam(currentUser);
                        break;
                    case 7:
                        adminController.createPlayer(currentUser);
                        break;
                    case 8:
                        adminController.createMatch(currentUser);
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