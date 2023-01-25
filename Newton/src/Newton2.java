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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double guess = x;
        double epsilon = 0.0000001;
        while (Math.abs((guess * guess) - x) / x > (epsilon * epsilon)) {
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
        double n = 0.0;
        String cont = "y";
        //program will loop until the user enters a value other than y at the end
        while (cont.equals("y")) {
            out.print("Enter a positive double: ");
            n = in.nextDouble();
            //n cannot be greater than or equal to zero.
            //create a while loop that forces the user to enter a value
            //greater than zero
            //removed = sign, now n can be 0.0
            while (n < 0.0) {
                out.print("Enter a positive double: ");
                n = in.nextDouble();
            }
            out.println("The square root of " + n + " is " + sqrt(n));
            out.print("Would you like to calculate another square root?: ");
            cont = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
