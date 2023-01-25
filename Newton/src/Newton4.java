import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program that estimates the square root of an entered double.
 *
 * @author Elijah Bulluck
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     * @param ep
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double ep) {
        double guess = x;
        while (Math.abs((guess * guess) - x) / x > (ep * ep)) {
            guess = (guess + (x / guess)) / 2.0;
        }
        return guess;
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
        out.print("Enter a positive double: ");
        double n = in.nextDouble();
        //program will loop until the user enters a negative value
        while (n >= 0.0) {
            out.print("Enter a value for epsilon: ");
            double ep = in.nextDouble();
            out.println("The square root of " + n + " is " + sqrt(n, ep));
            out.print("Enter another positive double: ");
            n = in.nextDouble();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
