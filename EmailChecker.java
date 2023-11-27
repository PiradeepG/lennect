import java.util.Scanner;

public class EmailChecker {
    private static Scanner scanner = new Scanner(System.in);

    public static String validateEmailFormat(String email) {
        while (true) {
            if (!containsAtSymbol(email)) {
                email = getEmail("Your email must contain the '@' symbol\nEnter a new email:");
                continue;
            }

            if (!containsLetter(email)) {
                email = getEmail("Your email must contain at least one letter\nEnter a new email:");
                continue;
            }

            if (!containsDigit(email)) {
                email = getEmail("Your email must contain at least one digit\nEnter a new email:");
                continue;
            }

            if (!endsWithEmailExtension(email)) {
                email = getEmail("Your email must end with '.com'\nEnter a new email:");
                continue;
            }

            if (email.length() < 5 || email.length() > 50) {
                email = getEmail("Your email must be between 5 and 50 characters\nEnter a new email:");
                continue;
            }

            break;
        }
        return email;
    }

    private static String getEmail(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static boolean containsAtSymbol(String email) {
        return email.contains("@");
    }

    private static boolean containsLetter(String email) {
        for (char c : email.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDigit(String email) {
        for (char c : email.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean endsWithEmailExtension(String email) {
        return email.toLowerCase().endsWith(".com");
    }

    public static void main(String[] args) {
        String validatedEmail = validateEmailFormat("hi");
        System.out.println("Validated email: " + validatedEmail);

        scanner.close();
    }
}
