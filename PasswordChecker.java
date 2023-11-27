import java.io.Console;

public class PasswordChecker {
    static Console console = System.console();

    public static String validatePassword(String password) {
        while (true) {
            if (password.length() < 8) {
                password = getPassword("Your password must contain 8 characters or above\nEnter a new password:");
                continue;
            }

            if (!containsSpecialCharacter(password)) {
                password = getPassword("Your password must contain a special character\nEnter a new password:");
                continue;
            }

            if (!containsDigit(password)) {
                password = getPassword("Your password must contain at least one digit\nEnter a new password:");
                continue;
            }

            if (!containsUppercaseLetter(password)) {
                password = getPassword("Your password must contain at least one uppercase letter\nEnter a new password:");
                continue;
            }

            if (!containsLowercaseLetter(password)) {
                password = getPassword("Your password must contain at least one lowercase letter\nEnter a new password:");
                continue;
            }

            break;
        }
        return password;
    }

    private static String getPassword(String message) {
        return new String(console.readPassword(message));
    }

    private static boolean containsSpecialCharacter(String password) {
        String specialCharacters = "!@#$%^&*";
        for (char specialChar : specialCharacters.toCharArray()) {
            if (password.contains(String.valueOf(specialChar))) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsUppercaseLetter(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsLowercaseLetter(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        PasswordChecker.validatePassword("uodsbvuibsduir");
    }
}
