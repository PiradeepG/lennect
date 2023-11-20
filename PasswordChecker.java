import java.io.Console;

public class PasswordChecker {
    static Console console = System.console();

    public static String validatePassword(String password) {
        String special = "!@#$%^&*";
        while (true) {
            if (password.length() < 8) {
                password = new String(console.readPassword("Your password must contain 8 characters or above\nEnter a new password:\n "));
                continue;
            }

            boolean specialChecker = false;
            for (int i = 0; i < special.length(); i++) {
                if (password.contains(String.valueOf(special.charAt(i)))) {
                    specialChecker = true;
                    break;
                }
            }

            boolean digitChecker = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    digitChecker = true;
                    break;
                }
            }

            boolean uppercaseChecker = false;
            boolean lowercaseChecker = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    uppercaseChecker = true;
                } else if (Character.isLowerCase(password.charAt(i))) {
                    lowercaseChecker = true;
                }
                if (uppercaseChecker && lowercaseChecker) {
                    break;
                }
            }

            if (!specialChecker) {
                password = new String(console.readPassword("Your password must contain a special character\nEnter a new password:\n "));
                continue;
            }

            if (!digitChecker) {
                password = new String(console.readPassword("Your password must contain at least one digit\nEnter a new password:\n "));
                continue;
            }

            if (!uppercaseChecker) {
                password = new String(console.readPassword("Your password must contain at least one uppercase letter\nEnter a new password:\n "));
                continue;
            }

            if (!lowercaseChecker) {
                password = new String(console.readPassword("Your password must contain at least one lowercase letter\nEnter a new password:\n "));
                continue;
            }

            break;
        }
        return password;
    }
}
