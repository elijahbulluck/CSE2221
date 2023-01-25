import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Hailstone Revisited
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
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber n2 = new NaturalNumber1L();
        n2.copyFrom(n);
        out.print(n2 + " ");
        int length = 1;

        while (n2.toInt() > 1) {
            if (n2.divideBy10() == 0) {
                n2 = n2.divide(new NaturalNumber1L(2));
                out.print(n2 + " ");
                length++;
            } else {
                n2.multiply(new NaturalNumber1L(3));
                n2.add(new NaturalNumber1L(1));
                out.print(n2 + " ");
                length++;
            }
        }
        out.println("Length: " + length);
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
        NaturalNumber n = new NaturalNumber1L();
        out.print("Enter a positive integer: ");
        String posInt = in.nextLine();
        while (!n.canSetFromString(posInt)) {
            out.print("Enter a positive integer: ");
            posInt = in.nextLine();
        }
        n.setFromString(posInt);
        generateSeries(n, out);
        out.print("Original number: " + n);
        in.close();
        out.close();
    }

}
