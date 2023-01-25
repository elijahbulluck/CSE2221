import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Elijah Bulluck
 *
 */
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int max = 0;
        int sum = 1;
        out.print(n + ", ");
        while (n != 2) {
            if (n > max) {
                max = n;
            }
            if (n % 2 == 0) {
                n = n / 2;
            }
            if (n % 2 != 0) {
                n = (3 * n) + 1;
            }
            out.print(n + ", ");
            sum = sum + 1;
        }
        sum = sum + 1;
        if (n == 2) {
            out.println(1);
            out.println("Length: " + sum);
            out.println("Max: " + max);
        }

    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        int n = 0;
        out.print("Enter a positive integer: ");
        String posInt = in.nextLine();
        while (FormatChecker.canParseInt(posInt) == false) {
            out.print("Enter a positive integer: ");
            posInt = in.nextLine();
            FormatChecker.canParseInt(posInt);
        }
        n = Integer.parseInt(posInt);
        while (n <= 0) {
            out.print("Enter a positive integer: ");
            posInt = in.nextLine();
            FormatChecker.canParseInt(posInt);
            n = Integer.parseInt(posInt);
        }
        generateSeries(n, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
