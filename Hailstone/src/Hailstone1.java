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
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
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
        out.print(n + ", ");
        int n2 = n;
        while (n != 1) {
            n2 = n;
            if (n % 2 == 0) {
                n2 /= 2;
                out.print(n2 + ", ");
                n = n2;
            } else {
                n2 = (3 * n + 1);
                out.print(n2 + ", ");
                n = n2;
            }
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
        while (!FormatChecker.canParseInt(posInt)) {
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
