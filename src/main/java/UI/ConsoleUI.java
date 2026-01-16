package UI;

public class ConsoleUI {
    public static void success(String text) {
        System.out.println(ConsoleColors.GREEN + text + ConsoleColors.RESET);
    }

    public static void error(String text) {
        System.out.println(ConsoleColors.RED + text + ConsoleColors.RESET);
    }

    public static void info(String text) {
        System.out.println(ConsoleColors.CYAN + text + ConsoleColors.RESET);
    }
}
