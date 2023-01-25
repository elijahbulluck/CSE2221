import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * A program that asks the user for a constant μ along with 4 other numbers and
 * uses the Jager formula to approximate μ to the nearest hundredth of one
 * percent.
 *
 * @author Elijah Bulluck
 *
 * @date 15/09/2021
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double n = 0.0;
        out.print("Enter a positive real value: ");
        String posDouble = in.nextLine();
        //create loop so program won't crash if user enters letters
        while (!FormatChecker.canParseDouble(posDouble)) {
            out.print("Enter a positive real value: ");
            posDouble = in.nextLine();
            FormatChecker.canParseDouble(posDouble);
        }
        n = Double.parseDouble(posDouble);
        //make a loop so the user cannot enter a value less than or equal to 0
        while (n <= 0.0) {
            out.print("Enter a positive real value: ");
            posDouble = in.nextLine();
            FormatChecker.canParseDouble(posDouble);
            //in case the user enters letters after entering a negative value,
            //the loop from before is copied down here so the program won't crash
            while (!FormatChecker.canParseDouble(posDouble)) {
                out.print("Enter a positive real value: ");
                posDouble = in.nextLine();
                FormatChecker.canParseDouble(posDouble);
            }
            n = Double.parseDouble(posDouble);
        }
        return n;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double r = 0.0;
        out.print("Enter another positive real value: ");
        String posDouble = in.nextLine();
        //create loop so program won't crash if user enters letters
        while (!FormatChecker.canParseDouble(posDouble)) {
            out.print("Enter another positive real value: ");
            posDouble = in.nextLine();
            FormatChecker.canParseDouble(posDouble);
        }
        r = Double.parseDouble(posDouble);
        //make a loop so the user cannot enter a value less than or equal to 1
        while (r <= 1.0) {
            out.print("Enter another positive real value: ");
            posDouble = in.nextLine();
            FormatChecker.canParseDouble(posDouble);
            //in case the user enters letters after entering a negative value,
            //the loop from before is copied down here so the program won't crash
            while (!FormatChecker.canParseDouble(posDouble)) {
                out.print("Enter another positive real value: ");
                posDouble = in.nextLine();
                FormatChecker.canParseDouble(posDouble);
            }
            r = Double.parseDouble(posDouble);
        }
        return r;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        double[] powers = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4, 0,
                1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        //declare values for the output (best estimate and the numbers that were
        //used to get it
        double best = 0.0;
        double powW = 0.0;
        double powX = 0.0;
        double powY = 0.0;
        double powZ = 0.0;
        //get values from user
        double actual = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        int i = 0;
        //create multiple loops that iterate through the array of powers
        while (i < powers.length) {
            double findW = Math.pow(w, powers[i]);
            int j = 0;
            //nest all other loops in this one so it will iterate through all of
            //the powers
            while (j < powers.length) {
                double findX = Math.pow(x, powers[j]);
                int k = 0;
                while (k < powers.length) {
                    double findY = Math.pow(y, powers[k]);
                    int l = 0;
                    while (l < powers.length) {
                        double findZ = Math.pow(z, powers[l]);
                        double estimate = findW * findX * findY * findZ;
                        //save values if the turn of the loops are
                        //closer to the actual number
                        if (Math.abs(actual - estimate) < Math
                                .abs(actual - best)) {
                            best = estimate;
                            //save the powers at the best index
                            //so they can be output
                            powW = powers[i];
                            powX = powers[j];
                            powY = powers[k];
                            powZ = powers[l];

                        }
                        //advance the loop for every number
                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        double error = (Math.abs(actual - best) / actual) * 100;
        //round estimate and relative error to the nearest hundredth using the method on
        // the components website
        out.print("Best estimate: ");
        out.println(best, 2, false);
        out.print("Relative Error: ");
        out.print(error, 2, false);
        out.println("%");
        out.println("Formula written out: " + w + "^" + powW + " " + x + "^"
                + powX + " " + y + "^" + powY + " " + z + "^" + powZ);
        in.close();
        out.close();
    }

}
