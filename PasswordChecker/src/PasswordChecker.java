import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program that allows the user to create a password specifying certain
 * requirements.
 *
 * @author Elijah Bulluck
 *
 */
public final class PasswordChecker {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private PasswordChecker() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        int passReq = 0;
        boolean checkPass = false;
        if (s.length() >= 8) {
            checkPass = true;
        }
        boolean upperCase = containsUpperCaseLetter(s);
        boolean lowerCase = containsLowerCaseLetter(s);
        boolean digits = containsDigit(s);
        if (upperCase) {
            passReq++;
        }
        if (lowerCase) {
            passReq++;
        }
        if (digits) {
            passReq++;
        }
        if (checkPass && passReq >= 2) {
            out.println("Password Accepted!");
        }
        if (checkPass || passReq < 2) {
            out.println("Password Rejected!");
        }
        if (checkPass) {
            out.println("Your password must be at least 8 characters long.");
        }
        if (upperCase && passReq < 2) {
            out.println(
                    "Your password must have an uppercase letter. (e.g., A, B, C, ...)");
        }
        if (lowerCase == false && passReq < 2) {
            out.println(
                    "Your password must have an lowercase letter. (e.g., a, b, c, ...)");
        }
        if (digits == false && passReq < 2) {
            out.println(
                    "Your password must have a number. (e.g., 1, 2, 3, ...)");
        }

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean val = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                val = true;
            }
        }
        return val;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean val1 = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                val1 = true;
            }
        }
        return val1;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param s
     *            the String to check
     * @return true if s contains a digit, false otherwise
     */
    private static boolean containsDigit(String s) {
        boolean val2 = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                val2 = true;
            }
        }
        return val2;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.print("Enter a password: ");
        String password = in.nextLine();
        checkPassword(password, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
